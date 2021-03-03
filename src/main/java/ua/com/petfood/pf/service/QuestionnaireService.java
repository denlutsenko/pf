package ua.com.petfood.pf.service;

import java.util.Map;

import ua.com.petfood.pf.model.dto.QuestionnaireDTO;

public interface QuestionnaireService {
    Map<String, Object> getAllCatalogsForQuestionnaire();

    Map<String, Object> calculateRecommendedBoxes(QuestionnaireDTO questDto, String token);
}
