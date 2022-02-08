package com.instacafe.moments.model.photo_session.brief;

import com.instacafe.moments.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CurrentBriefQuestions")
@Table(name = "current_brief_questions")
public class CurrentBriefQuestions extends AbstractEntity {
    @ElementCollection
    private List<String> questions;
}
