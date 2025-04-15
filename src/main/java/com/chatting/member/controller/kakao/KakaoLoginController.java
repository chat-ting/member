package com.chatting.member.controller.kakao;


import com.chatting.member.global.jwt.dto.MyJwtDTO;
import com.chatting.member.global.jwt.service.JwtTokenService;
import com.chatting.member.service.business_service.kakao.KakaoLoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/kakao")
public class KakaoLoginController {
    @Value("${jwt.access-token-expiration-time}")
    private int ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.refresh-token-expiration-time}")
    private int REFRESH_TOKEN_EXPIRATION_TIME;
    private final KakaoLoginService kakaoLoginService;
    private final JwtTokenService jwtTokenService;

    //카카오로그인 페이지
    @GetMapping("/login")
    public String getKakaoLoginPage(Model model) {
        kakaoLoginService.setLoginUrlEnvironment(model);
        return "kakaoLoginForm";
    }
    //카카오로그아웃 페이지
    @GetMapping("/logout")
    public String getKakaoLogoutPage(Model model) {
        kakaoLoginService.setLogoutUrlEnvironment(model);
        return "kakaoLogoutForm";
    }

    //인가코드 받아서 카카오토큰 요청 -> 카카오토큰으로 유저 정보 가져오기
    @GetMapping("/callback")
    public String getUserInfoByReceivedAuthorizationCode(@RequestParam Map<String, String> params, HttpServletResponse response) throws JsonProcessingException {
        MyJwtDTO userInfoByReceivedAuthorizationCode = kakaoLoginService.getUserInfoByReceivedAuthorizationCode(params);
        log.info("getUserInfoByReceivedAuthorizationCode success");
        String accessToken = userInfoByReceivedAuthorizationCode.getAccessToken();
        String refreshToken = userInfoByReceivedAuthorizationCode.getRefreshToken();

        //JWT 토큰을 쿠키에 설정
        jwtTokenService.setTokensOnCookie(response,accessToken,refreshToken);
        return "redirect:/";
    }
}
