package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.ArticleEntity;
import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.view.ArticleViewModel;
import bg.softuni.yummyshare.repository.ArticleRepository;
import bg.softuni.yummyshare.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {
    private UserEntity testUser1, testUser2;
    private ArticleEntity testArticleEntity1, testArticleEntity2;

    private ArticleServiceImpl testService;

    @Mock
    ArticleRepository mockArticleRepository;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void testInitArticle() {
        testUser1 = new UserEntity();
        testUser1.setUsername("user 1");

        testArticleEntity1 = new ArticleEntity();
        testArticleEntity1.setTitle("article 1");
        testArticleEntity1.setText("text 1");
        testArticleEntity1.setAuthor(testUser1);

        testUser2 = new UserEntity();
        testUser2.setUsername("user 2");

        testArticleEntity2 = new ArticleEntity();
        testArticleEntity2.setTitle("article 2");
        testArticleEntity2.setText("text 2");
        testArticleEntity2.setAuthor(testUser2);

        testService = new ArticleServiceImpl(mockArticleRepository, mockUserRepository, new ModelMapper());
    }

    @Test
    public void testGetAllArticles() {
        when(mockArticleRepository.findAll()).thenReturn(List.of(testArticleEntity1, testArticleEntity2));
        List<ArticleViewModel> allModels = testService.getAllArticles();

        Assertions.assertEquals(2, allModels.size());

        ArticleViewModel model1 = allModels.get(0);
        ArticleViewModel model2 = allModels.get(1);

        Assertions.assertEquals(testArticleEntity1.getTitle(), model1.getTitle());
        Assertions.assertEquals(testArticleEntity1.getText(), model1.getText());
        Assertions.assertEquals(testUser1.getUsername(), model1.getAuthor());

        Assertions.assertEquals(testArticleEntity2.getTitle(), model2.getTitle());
        Assertions.assertEquals(testArticleEntity2.getText(), model2.getText());
        Assertions.assertEquals(testUser2.getUsername(), model2.getAuthor());
    }
}
