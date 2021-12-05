package bg.softuni.yummyshare.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private UserEntity author;

    public ArticleEntity() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public ArticleEntity setText(String text) {
        this.text = text;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ArticleEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
