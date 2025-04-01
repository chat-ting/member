package com.chatting.member.global.entity.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // parent_event_id는 Long으로 변경
    @Column(name = "parent_event_id", nullable = true) // nullable을 true로 설정하여 parent가 없을 때도 처리 가능
    private Long parentEventId;

    @Column(name = "event_data", nullable = false)
    private String eventData;

    @Column(name = "published", nullable = false)
    private Boolean published;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "try_count", nullable = false)
    private Long tryCount;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "published_at")
    private ZonedDateTime publishedAt;

    // 게시가 완료되었을 때 published를 true로 변경하는 메서드
    public void done() {
        this.published = true;
    }
}
