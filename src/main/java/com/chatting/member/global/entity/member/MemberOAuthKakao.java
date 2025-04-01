package com.chatting.member.global.entity.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member_oauth_kakao")
public class MemberOAuthKakao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_oauth_kakao_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_oauth_id", nullable = false, unique = true)
    private MemberOAuth memberOAuth;

    @Column(name = "kakao_id", nullable = false, unique = true)
    private String kakaoId;

    @Column(name = "kakao_email", nullable = false, unique = true)
    private String kakaoEmail;

    @Column(name = "kakao_nickname")
    private String kakaoNickname;

    // Getter, Setter
}