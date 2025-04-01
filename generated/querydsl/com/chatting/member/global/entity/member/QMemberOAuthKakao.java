package com.chatting.member.global.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberOAuthKakao is a Querydsl query type for MemberOAuthKakao
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberOAuthKakao extends EntityPathBase<MemberOAuthKakao> {

    private static final long serialVersionUID = 465990593L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberOAuthKakao memberOAuthKakao = new QMemberOAuthKakao("memberOAuthKakao");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kakaoEmail = createString("kakaoEmail");

    public final StringPath kakaoId = createString("kakaoId");

    public final StringPath kakaoNickname = createString("kakaoNickname");

    public final QMemberOAuth memberOAuth;

    public QMemberOAuthKakao(String variable) {
        this(MemberOAuthKakao.class, forVariable(variable), INITS);
    }

    public QMemberOAuthKakao(Path<? extends MemberOAuthKakao> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberOAuthKakao(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberOAuthKakao(PathMetadata metadata, PathInits inits) {
        this(MemberOAuthKakao.class, metadata, inits);
    }

    public QMemberOAuthKakao(Class<? extends MemberOAuthKakao> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberOAuth = inits.isInitialized("memberOAuth") ? new QMemberOAuth(forProperty("memberOAuth"), inits.get("memberOAuth")) : null;
    }

}

