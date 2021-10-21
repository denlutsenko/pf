package ua.com.petfood.pf.service;

import java.util.Map;

import ua.com.petfood.pf.model.dto.QuestionnaireDTO;
import ua.com.petfood.pf.model.dto.response.recommendedbox.RecommendedBoxResponse;

public interface QuestionnaireService {

    Map<String, Object> getAllCatalogsForQuestionnaire();

    RecommendedBoxResponse calculateRecommendedBoxes(QuestionnaireDTO questDto);
}
