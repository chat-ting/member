package com.chatting.member.global.entity.member;

import com.chatting.member.controller.dto.MemberOut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email", nullable = false, unique = true)
    private String email;

    @Column(name = "member_nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name="member_role", nullable = false)
    private String role;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberOAuth> memberOAuths = new ArrayList<>();



    // Getter, Setter
    public MemberOut toDto(){
        return MemberOut.builder()
                .memberId(this.id)
                .memberEmail(this.email)
                .memberNickname(this.nickname)
                .build();
    }


}