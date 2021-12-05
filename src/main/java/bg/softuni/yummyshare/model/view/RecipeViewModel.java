package bg.softuni.yummyshare.model.view;

public class RecipeViewModel {
    private String name;
    private String products;
    private String preparation;
    private Integer portions;
    private String pictureUrl;
    private String author;

    public String getName() {
        return name;
    }

    public RecipeViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeViewModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public RecipeViewModel setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public Integer getPortions() {
        return portions;
    }

    public RecipeViewModel setPortions(Integer portions) {
        this.portions = portions;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RecipeViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public RecipeViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
