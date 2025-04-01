package com.chatting.member.global.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberOAuth is a Querydsl query type for MemberOAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberOAuth extends EntityPathBase<MemberOAuth> {

    private static final long serialVersionUID = -2139674078L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberOAuth memberOAuth = new QMemberOAuth("memberOAuth");

    public final StringPath host = createString("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QMemberOAuthKakao memberOAuthKakao;

    public QMemberOAuth(String variable) {
        this(MemberOAuth.class, forVariable(variable), INITS);
    }

    public QMemberOAuth(Path<? extends MemberOAuth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberOAuth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberOAuth(PathMetadata metadata, PathInits inits) {
        this(MemberOAuth.class, metadata, inits);
    }

    public QMemberOAuth(Class<? extends MemberOAuth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.memberOAuthKakao = inits.isInitialized("memberOAuthKakao") ? new QMemberOAuthKakao(forProperty("memberOAuthKakao"), inits.get("memberOAuthKakao")) : null;
    }

}

