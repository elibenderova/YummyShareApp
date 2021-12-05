package bg.softuni.yummyshare.model.service;

public class ArticleAddServiceModel {
    private String title;
    private String text;
    private String author;

    public ArticleAddServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public ArticleAddServiceModel setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
