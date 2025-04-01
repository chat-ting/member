package com.chatting.member.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class KakaoUser {

    private String id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Data
    public static class KakaoAccount {
        private String email;
        private Profile profile;

        @Data
        public static class Profile {

            private String nickname;

            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;
        }

    }
}
