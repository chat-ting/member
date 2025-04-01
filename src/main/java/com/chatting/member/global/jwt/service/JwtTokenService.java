package com.chatting.member.global.jwt.service;

import com.chatting.member.global.exception.BusinessException;
import com.chatting.member.global.exception.code.CommonErrorCode;
import com.chatting.member.global.jwt.TokenType;
import com.chatting.member.global.jwt.dto.TokensResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Slf4j
@Service
public class JwtTokenService {
    @Value("${jwt.access-token-expiration-time}")
    private int ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.refresh-token-expiration-time}")
    private int REFRESH_TOKEN_EXPIRATION_TIME ;
    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    //=========================토큰 생성 관련 시작=========================
    public String generateAccessToken(Long memberId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(TokenType.ACCESS.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    public String generateRefreshToken(Long memberId) {
        Date now = new Date();
        Date expiryDate = new Date(new Date().getTime() + REFRESH_TOKEN_EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(TokenType.REFRESH.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("memberId", memberId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    //=========================토큰 생성 관련 종료=========================


    //=========================토큰 검증 관련 시작=========================
    public boolean validateToken(String token,TokenType needTokenType, HttpServletResponse response) {
        log.info("validate in");
        try {
            log.info("get claims start");
            Jws<Claims> claims = getClaims(token);
            log.info("check token type start");
            String currentTokenType = claims.getBody().getSubject();
            if(!currentTokenType.equals(needTokenType.name())){
                log.info("잘못된 토큰타입 입니다");
                throw new BusinessException(CommonErrorCode.INVALID_TOKEN_TYPE,needTokenType);
            }
            return !isTokenExpired(claims.getBody());
        }catch (ExpiredJwtException e){
            log.info("만료된 토큰입니다");
            throw new BusinessException(CommonErrorCode.TOKEN_EXPIRED);
            // return false;

        }catch (Exception e) {
            log.info("jwt 검증중 에러");
            // 여기서는 모든 예외를 잡아 false를 반환합니다.
            // 실제 사용시에는 예외 종류에 따라 다른 처리가 필요할 수 있습니다.
            e.printStackTrace();
            throw e;
            // return false;
        }
    }

    public String getJwtFromRequest(HttpServletRequest request, TokenType tokenType){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        log.info("bearerToken = {}",bearerToken);
        if(bearerToken !=null && bearerToken.startsWith(tokenType.name())){
            return bearerToken.substring(tokenType.name().length()+1);
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            log.info("cookie name = {}, cookie value = {}",cookie.getName(),cookie.getValue());
        }
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(tokenType.name())){
                    return cookie.getValue();
                }
            }
        }

        throw new BusinessException(CommonErrorCode.NOT_VALID_TOKEN);
    }

    // 토큰의 만료 시간 확인
    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = getClaims(token).getBody();

        // 여기서 "memberId"는 토큰 생성 시에 정의한 커스텀 클레임의 이름입니다.
        // 토큰에 저장된 memberId를 Long 타입으로 반환합니다.
        return Long.parseLong(claims.get("memberId").toString());
    }
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public Jws<Claims> getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token);
    }


    public TokensResponseDto regenerateTokensByRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String jwt = getJwtFromRequest(request,TokenType.REFRESH);
        validateToken(jwt,TokenType.REFRESH, response);
        Long memberId = getUserIdFromJwt(jwt);
        String accessToken = generateAccessToken(memberId);
        String refreshToken = generateRefreshToken(memberId);
        return TokensResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

    }

    public void setTokensOnCookie(HttpServletResponse response, String accessToken, String refreshToken){
        // JWT 토큰을 쿠키에 설정
        Cookie accessTokenCookie = new Cookie(TokenType.ACCESS.name(), accessToken);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(ACCESS_TOKEN_EXPIRATION_TIME);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true); // HTTPS 환경이면 필수
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie(TokenType.REFRESH.name(), refreshToken);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(REFRESH_TOKEN_EXPIRATION_TIME);
        // 만약 refresh token도 보안상 HttpOnly, Secure 설정이 필요하면 추가할 수 있음
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        response.addCookie(refreshTokenCookie);
    }
}
