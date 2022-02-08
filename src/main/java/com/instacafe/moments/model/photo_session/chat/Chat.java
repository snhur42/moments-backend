package com.instacafe.moments.model.photo_session.chat;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Chat")
@Table(name = "chat")
public class Chat extends AbstractEntity {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "chat_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "chat_id_fk"
            )
    )
    private List<Message> message;
}
