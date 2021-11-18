package ua.com.petfood.pf.model.dto;

import lombok.Data;
import ua.com.petfood.pf.model.AnimalCategory;
@Data
public class AnimalDTO {
    private String animalCategoryName;
    private String name;
    private String subCategoryName;
    private Integer age;
    private String size;

}
