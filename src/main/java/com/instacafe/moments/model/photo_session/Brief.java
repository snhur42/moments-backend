package com.instacafe.moments.model.photo_session;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
}
