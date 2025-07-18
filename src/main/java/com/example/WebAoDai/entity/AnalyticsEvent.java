package com.example.WebAoDai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "analytics_event")
public class AnalyticsEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "event_time")
    private Date eventTime;

    @Column(name = "source")
    private String source;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
