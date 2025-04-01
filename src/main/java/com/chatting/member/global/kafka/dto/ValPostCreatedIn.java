package com.chatting.member.global.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ValPostCreatedIn {
    @JsonProperty("postId")
    private Long postId;
    // @JsonProperty("boardNameEng")
    // private String boardNameEng;
    // @JsonProperty("boardNameKor")
    // private String boardNameKor;
    // @JsonProperty("postNo")
    // private String postNo;
    // @JsonProperty("postTitle")
    // private String postTitle;
    // @JsonProperty("postUrl")
    // private String postUrl;
    // @JsonProperty("postWriteDate")
    // private String postWriteDate;
    // @JsonProperty("memberId")
    // private String memberId;


//    @JsonCreator
//    public ValNewPostIn(
//            @JsonProperty("post_id") Long postId,
//            @JsonProperty("board_name_eng") String boardNameEng,
//            @JsonProperty("board_name_kor") String boardNameKor,
//            @JsonProperty("post_no") String postNo,
//            @JsonProperty("post_title") String postTitle,
//            @JsonProperty("post_url") String postUrl,
//            @JsonProperty("post_write_date") String postWriteDate) {
//        this.postId = postId;
//        this.boardNameEng = boardNameEng;
//        this.boardNameKor = boardNameKor;
//        this.postNo = postNo;
//        this.postTitle = postTitle;
//        this.postUrl = postUrl;
//        this.postWriteDate = postWriteDate;
//    }
}
