package com.chatting.member.global.jwt.controller;

import com.chatting.member.global.jwt.dto.TokensResponseDto;
import com.chatting.member.global.jwt.service.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtTokenService jwtTokenService;
    @GetMapping("/by/refresh")
    public String createAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        log.info("createAccessTokenByRefreshToken in");
        TokensResponseDto tokensResponseDto = jwtTokenService.regenerateTokensByRefreshToken(request, response);
        jwtTokenService.setTokensOnCookie(response, tokensResponseDto.getAccessToken(), tokensResponseDto.getRefreshToken());
        return "redirect:/api/view/keyword";
    }
}
