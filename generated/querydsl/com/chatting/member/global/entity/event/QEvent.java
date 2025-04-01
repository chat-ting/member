package com.chatting.member.global.entity.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -1699944211L;

    public static final QEvent event = new QEvent("event");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final StringPath eventData = createString("eventData");

    public final StringPath eventType = createString("eventType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentEventId = createNumber("parentEventId", Long.class);

    public final BooleanPath published = createBoolean("published");

    public final DateTimePath<java.time.ZonedDateTime> publishedAt = createDateTime("publishedAt", java.time.ZonedDateTime.class);

    public final NumberPath<Long> tryCount = createNumber("tryCount", Long.class);

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

