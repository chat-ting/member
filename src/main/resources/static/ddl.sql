 -- 회원 정보를 저장하는 테이블
CREATE TABLE member
(
    member_id       BIGSERIAL,                    -- 고유 회원 ID
    member_email    VARCHAR(255) UNIQUE NOT NULL, -- 고유 이메일 주소
    member_nickname VARCHAR(255) UNIQUE NOT NULL, -- 고유 닉네임
    telegram_uuid UUID UNIQUE,
    telegram_uuid_created_at timestamp,
    PRIMARY KEY (member_id)                       -- 기본 키 설정
);

-- OAuth 회원 정보를 저장하는 테이블
CREATE TABLE member_oauth
(
    member_oauth_id BIGSERIAL,             -- ID
    member_id       BIGINT       NOT NULL, -- 회원 ID
    oauth_host      VARCHAR(255) NOT NULL, -- OAuth 호스트 정보
    PRIMARY KEY (member_oauth_id)          -- 기본 키 설정
);

-- 카카오 회원 정보를 저장하는 테이블
CREATE TABLE member_oauth_kakao
(
    member_oauth_kakao_id BIGSERIAL,                    -- ID
    kakao_id              VARCHAR(255) UNIQUE NOT NULL, -- 고유 카카오 ID
    kakao_email           VARCHAR(255) UNIQUE NOT NULL, -- 고유 카카오 이메일
    kakao_nickname        VARCHAR(255),                 -- 카카오 닉네임
    PRIMARY KEY (member_oauth_kakao_id)                 -- 기본 키 설정
);
-- 키워드
CREATE TABLE keyword
(
    keyword_id BIGSERIAL,    -- ID
    keyword    VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (keyword_id) -- 기본 키 설정
);

-- 키워드 맴버 매핑
CREATE TABLE map_member_keyword
(
    member_id  BIGINT NOT NULL,         -- 고유 멤버 ID
    keyword_id BIGINT NOT NULL,         -- keyword ID
    PRIMARY KEY (member_id, keyword_id) -- 기본 키 설정
);


-- 외래 키 설정
ALTER TABLE member_oauth
    ADD CONSTRAINT fk_member_oauth_member
        FOREIGN KEY (member_id)
            REFERENCES member (member_id);

ALTER TABLE member_oauth_kakao
    ADD CONSTRAINT fk_member_oauth_kakao_member_oauth
        FOREIGN KEY (member_oauth_id)
            REFERENCES member_oauth (member_oauth_id);


ALTER TABLE map_member_keyword
    ADD CONSTRAINT fk_map_member_keyword_member
        foreign key (member_id)
            references member (member_id);

ALTER TABLE map_member_keyword
    ADD CONSTRAINT fk_map_member_keyword_keyword
        foreign key (keyword_id)
            references keyword (keyword_id);

 CREATE INDEX idx_member_telegram_uuid ON member(telegram_uuid);