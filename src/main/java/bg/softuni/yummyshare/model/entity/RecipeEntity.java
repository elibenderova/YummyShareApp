package bg.softuni.yummyshare.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class RecipeEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column
    private String products;
    @Column(columnDefinition = "TEXT")
    private String preparation;
    @Column
    private Integer portions;
    @OneToOne
    private PictureEntity picture;
    @ManyToOne
    private UserEntity author;

    public RecipeEntity() {
    }

    public String getName() {
        return name;
    }

    public RecipeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeEntity setProducts(String products) {
        this.products = products;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public RecipeEntity setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public Integer getPortions() {
        return portions;
    }

    public RecipeEntity setPortions(Integer calories) {
        this.portions = calories;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public RecipeEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RecipeEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
