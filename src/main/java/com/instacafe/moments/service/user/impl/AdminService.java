package com.instacafe.moments.service.user.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instacafe.moments.dto.request.CurrentBriefQuestionsRequestDTO;
import com.instacafe.moments.model.photo_session.brief.CurrentBriefQuestions;
import com.instacafe.moments.repository.photo_session.CurrentBriefQuestionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
@Transactional
@Qualifier("adminService")
public class AdminService {
    private final CurrentBriefQuestionsRepository currentBriefQuestionsRepository;

    @Autowired
    public AdminService(CurrentBriefQuestionsRepository currentBriefQuestionsRepository) {
        this.currentBriefQuestionsRepository = currentBriefQuestionsRepository;
    }

    public CurrentBriefQuestions getBriefQuestion() {
        return this.currentBriefQuestionsRepository.findAll().get(0);
    }

    public CurrentBriefQuestions updateBriefQuestion(CurrentBriefQuestionsRequestDTO currentBriefQuestionsRequestDTO) {
        CurrentBriefQuestions currentBriefQuestions = this.currentBriefQuestionsRepository.findAll().get(0);

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            String[] strings = objectMapper.readValue(currentBriefQuestionsRequestDTO.getQuestions(), String[].class);
            ArrayList<String> questions = new ArrayList<>(Arrays.asList(strings));
            currentBriefQuestions.setQuestions(questions);
        } catch (JsonProcessingException err) {
            log.error("Cant deserialize questions");
        }

        return this.currentBriefQuestionsRepository.save(currentBriefQuestions);
    }

    public CurrentBriefQuestions createBriefQuestion() {
        return this.currentBriefQuestionsRepository.save(new CurrentBriefQuestions());
    }
}