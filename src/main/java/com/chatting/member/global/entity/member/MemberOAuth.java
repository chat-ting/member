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
@Table(name = "member_oauth")
public class MemberOAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_oauth_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "oauth_host", nullable = false)
    private String host;

    @OneToOne(mappedBy = "memberOAuth", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberOAuthKakao memberOAuthKakao;

    // Getter, Setter
}