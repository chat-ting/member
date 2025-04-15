CREATE TABLE "member" (
                          member_id bigserial NOT NULL,
                          member_email varchar(255) NOT NULL,
                          member_nickname varchar(255) NOT NULL,
                          member_role varchar(255) NOT NULL,
                          CONSTRAINT member_member_email_key UNIQUE (member_email),
                          CONSTRAINT member_pkey PRIMARY KEY (member_id)
);

CREATE TABLE member_oauth (
                              member_oauth_id bigserial NOT NULL,
                              member_id int8 NOT NULL,
                              oauth_host varchar(255) NOT NULL,
                              CONSTRAINT member_oauth_pkey PRIMARY KEY (member_oauth_id)
);

CREATE TABLE member_oauth_kakao (
                                    member_oauth_kakao_id bigserial NOT NULL,
                                    member_oauth_id int8 NOT NULL,
                                    kakao_email varchar(255) NOT NULL,
                                    kakao_id varchar(255) NOT NULL,
                                    kakao_nickname varchar(255),
                                    CONSTRAINT member_oauth_kakao_kakao_email_key UNIQUE (kakao_email),
                                    CONSTRAINT member_oauth_kakao_kakao_id_key UNIQUE (kakao_id),
                                    CONSTRAINT member_oauth_kakao_pkey PRIMARY KEY (member_oauth_kakao_id)
);