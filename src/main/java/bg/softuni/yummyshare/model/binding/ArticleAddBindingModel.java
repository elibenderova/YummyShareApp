package bg.softuni.yummyshare.model.binding;

import javax.validation.constraints.Size;

public class ArticleAddBindingModel {
    @Size(min = 5, message = "Title length must be more than 5 characters. Please try again!")
    private String title;
    @Size(min = 15, message = "Content length must be more than 15 characters. Please try again!")
    private String text;

    public ArticleAddBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public ArticleAddBindingModel setText(String text) {
        this.text = text;
        return this;
    }
}
