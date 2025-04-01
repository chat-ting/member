package com.chatting.member.global.util;

import com.chatting.member.global.util.dto.KakaoTokenResDTO;
import com.chatting.member.controller.dto.KakaoUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebClientService {
    private final WebClient webClient;

    public Mono<KakaoTokenResDTO> requestKakaoToken(String authorizationCode, String clientId, String clientSecret, String redirectUri){

        //application/x-www-form-urlencoded 컨텐츠 타입으로 요청을 보내려면, 요청 본문(body)을 URL 인코딩된 형식으로 직접 작성
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type","authorization_code");
        formData.add("client_id",clientId);
        formData.add("client_secret",clientSecret);
        formData.add("code",authorizationCode);
        formData.add("redirect_uri",redirectUri);
        log.info("kakoTokenReq = {}",formData);

        return webClient
                .post()
                .uri("https://kauth.kakao.com/oauth/token")
                .header(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=utf-8")
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(KakaoTokenResDTO.class);
    }


    //카카오 회원 정보 요청
    public Mono<KakaoUser> requestKakaoUserInfo(String kakaoAccessToken) {


        return webClient
                .post()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoAccessToken) // Authorization 헤더 설정
                .header(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoUser.class);

    }
}
