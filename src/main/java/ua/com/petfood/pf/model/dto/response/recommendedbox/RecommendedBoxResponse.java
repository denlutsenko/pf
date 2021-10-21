package ua.com.petfood.pf.model.dto.response.recommendedbox;

import java.util.List;

public class RecommendedBoxResponse {

    private List<Box> boxes;

    public RecommendedBoxResponse(){}

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(final List<Box> boxes) {
        this.boxes = boxes;
    }
}
