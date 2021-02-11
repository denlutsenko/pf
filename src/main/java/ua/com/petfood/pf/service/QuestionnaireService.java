package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.QuestionnaireDto;

import java.util.List;
import java.util.Map;

public interface QuestionnaireService {
    Map<String,Object> getAllCatalogsForQuestionnaire();
    Map<String, List<SKUItem>> calculateRecommendedBoxes(QuestionnaireDto questDto);
}
