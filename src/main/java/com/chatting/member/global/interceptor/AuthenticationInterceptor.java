package com.chatting.member.global.interceptor;

import com.chatting.member.global.jwt.CustomPrincipal;
import com.chatting.member.global.jwt.TokenType;
import com.chatting.member.global.jwt.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtTokenService jwtTokenService;

    @Override//컨트롤러 로직을 수행하기 전에 수행되는 부분
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(isExcludedMethod(request)){
            log.info("인증 인터셉터 생략하는 요청임");
            return true;
        }
        log.info("인증 인터셉터 시작");
        try {
            String jwt = jwtTokenService.getJwtFromRequest(request, TokenType.ACCESS);
            jwtTokenService.validateToken(jwt, TokenType.ACCESS, response);
            Long memberId = jwtTokenService.getUserIdFromJwt(jwt);//jwt에서 사용자 id 추출
            log.info("memberId = {}", memberId);
            CustomPrincipal principal = new CustomPrincipal(); //사용자의 주요 정보와 권한
            principal.setMemberId(memberId);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(principal, null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));//TODO 역할 구현
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            // 인증 세부 정보 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // SecurityContext에 인증 정보 저장
            log.info("인증인터셉터성공");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error("인증 인터셉터 실패");
            response.sendRedirect("/");
            return false;
        }
    }

    private boolean isExcludedMethod(HttpServletRequest request) {
        log.info("인증 인터셉터 제외되는 요청인지 확인");
        String uri = request.getRequestURI();
        String method = request.getMethod();
        return (uri.matches("/test(/.*)?") && method.equalsIgnoreCase("POST"))
                ||(uri.matches("/") && method.equalsIgnoreCase("GET"));
    }


}
