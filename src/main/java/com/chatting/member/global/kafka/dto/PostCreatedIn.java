package com.chatting.member.global.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class PostCreatedIn {
    @JsonProperty("value")
    private ValPostCreatedIn value;
    @JsonProperty("eventId")
    private Long eventId;
    @JsonProperty("parentEventId")
    private Long parentEventId;
    @JsonProperty("eventType")
    private String eventType;
}
