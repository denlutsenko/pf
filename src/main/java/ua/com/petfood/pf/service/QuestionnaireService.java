package ua.com.petfood.pf.service;

import java.util.List;
import java.util.Map;

import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDTO;

public interface QuestionnaireService {

    Map<String, Object> getAllCatalogsForQuestionnaire();

    Map<String, List<SKUItem>> calculateRecommendedBoxes(QuestionnaireDTO questDto, String token);
}
