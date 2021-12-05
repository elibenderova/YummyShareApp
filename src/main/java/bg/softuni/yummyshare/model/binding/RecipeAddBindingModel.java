package bg.softuni.yummyshare.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class RecipeAddBindingModel {
    @NotEmpty(message = "The title is required. Please try again!")
    private String name;
    @NotEmpty(message = "The products are required. Please try again!")
    private String products;
    @Size(min = 5, message = "Title length must be more than 5 characters. Please try again!")
    private String preparation;
    @Positive(message = "The Portions must be positive number. Please try again!")
    private Integer portions;
    private MultipartFile picture;

    public RecipeAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public RecipeAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeAddBindingModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public RecipeAddBindingModel setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public Integer getPortions() {
        return portions;
    }

    public RecipeAddBindingModel setPortions(Integer portions) {
        this.portions = portions;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public RecipeAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
