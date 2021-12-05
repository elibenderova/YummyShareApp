package bg.softuni.yummyshare.model.service;

import org.springframework.web.multipart.MultipartFile;

public class RecipeAddServiceModel {
    private String name;
    private String products;
    private String preparation;
    private Integer portions;
    private MultipartFile picture;
    private String author;

    public String getName() {
        return name;
    }

    public RecipeAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeAddServiceModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public RecipeAddServiceModel setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public Integer getPortions() {
        return portions;
    }

    public RecipeAddServiceModel setPortions(Integer portions) {
        this.portions = portions;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public RecipeAddServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public RecipeAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
