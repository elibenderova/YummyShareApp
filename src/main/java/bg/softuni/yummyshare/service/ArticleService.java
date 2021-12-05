package bg.softuni.yummyshare.service;

import bg.softuni.yummyshare.model.service.ArticleAddServiceModel;
import bg.softuni.yummyshare.model.view.ArticleViewModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ArticleService {
    void addArticle(ArticleAddServiceModel articleAddServiceModel, UserDetails principal);
    List<ArticleViewModel> getAllArticles();
}
