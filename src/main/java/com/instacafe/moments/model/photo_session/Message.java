package com.instacafe.moments.model.photo_session;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Message")
@Table(name = "message")
public class Message extends AbstractEntity {
    @Column(name = "sender_id", nullable = false, columnDefinition = "TEXT")
    private UUID senderId;
    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;
    @Column(name = "is_read_by_Manager", columnDefinition = "BOOLEAN")
    private boolean isReadByManager;
}
