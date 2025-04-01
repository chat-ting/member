package com.chatting.member.repository.event;


import com.chatting.member.global.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByPublishedOrderByIdAsc(boolean b);
}
