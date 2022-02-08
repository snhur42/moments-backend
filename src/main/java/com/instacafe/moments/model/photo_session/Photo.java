package com.instacafe.moments.model.photo_session;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Photo")
@Table(name = "photo")
public class Photo extends AbstractEntity {
    @Column(name = "path", nullable = false, columnDefinition = "TEXT")
    private String path;
}
