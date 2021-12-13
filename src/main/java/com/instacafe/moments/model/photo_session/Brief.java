package com.instacafe.moments.model.photo_session;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Brief")
@Table(name = "brief")
public class Brief extends AbstractEntity {
    @Column(name = "is_completed_by_client", columnDefinition = "BOOLEAN")
    private boolean isCompletedByClient;

    @Column(name = "client_id", columnDefinition = "TEXT")
    private UUID clientId;

    @OneToOne(mappedBy = "brief")
    private PhotoSession photoSession;

    @ElementCollection
    private List<String> questions;

    @ElementCollection
    private List<String> answers;
}
