package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.ArticleEntity;
import bg.softuni.yummyshare.model.entity.PictureEntity;
import bg.softuni.yummyshare.model.entity.RecipeEntity;
import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.view.ArticleViewModel;
import bg.softuni.yummyshare.model.view.RecipeViewModel;
import bg.softuni.yummyshare.repository.PictureRepository;
import bg.softuni.yummyshare.repository.RecipeRepository;
import bg.softuni.yummyshare.repository.UserRepository;
import bg.softuni.yummyshare.service.CloudinaryService;
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
public class RecipeServiceImplTest {
    private UserEntity testUser1, testUser2;
    private PictureEntity testPicture1, testPicture2;
    private RecipeEntity testRecipeEntity1, testRecipeEntity2;

    private RecipeServiceImpl testService;

    @Mock
    RecipeRepository mockRecipeRepository;

    @Mock
    UserRepository mockUserRepository;

    @Mock
    PictureRepository mockPictureRepository;

    @Mock
    CloudinaryService mockCloudinaryService;

    @BeforeEach
    public void testInitRecipe() {
        testUser1 = new UserEntity();
        testUser1.setUsername("user 1");

        testPicture1 = new PictureEntity();
        testPicture1.setUrl("pic1.jpg");

        testRecipeEntity1 = new RecipeEntity();
        testRecipeEntity1.setPortions(1);
        testRecipeEntity1.setName("recipe 1");
        testRecipeEntity1.setProducts("product1");
        testRecipeEntity1.setPreparation("preparation 1");
        testRecipeEntity1.setAuthor(testUser1);
        testRecipeEntity1.setPicture(testPicture1);

        testUser2 = new UserEntity();
        testUser2.setUsername("user 2");

        testPicture2 = new PictureEntity();
        testPicture2.setUrl("pic2.jpg");

        testRecipeEntity2 = new RecipeEntity();
        testRecipeEntity2.setPortions(2);
        testRecipeEntity2.setName("recipe 2");
        testRecipeEntity2.setProducts("product1, product2");
        testRecipeEntity2.setPreparation("preparation 2");
        testRecipeEntity2.setAuthor(testUser2);
        testRecipeEntity2.setPicture(testPicture2);

        testService = new RecipeServiceImpl(mockRecipeRepository, mockUserRepository, mockPictureRepository, mockCloudinaryService, new ModelMapper());
    }

    @Test
    public void testGetAllRecipes() {
        when(mockRecipeRepository.findAll()).thenReturn(List.of(testRecipeEntity1, testRecipeEntity2));
        List<RecipeViewModel> allModels = testService.getAllRecipes();

        Assertions.assertEquals(2, allModels.size());

        RecipeViewModel model1 = allModels.get(0);
        RecipeViewModel model2 = allModels.get(1);

        Assertions.assertEquals(testRecipeEntity1.getName(), model1.getName());
        Assertions.assertEquals(testRecipeEntity1.getProducts(), model1.getProducts());
        Assertions.assertEquals(testRecipeEntity1.getPreparation(), model1.getPreparation());
        Assertions.assertEquals(testRecipeEntity1.getPortions(), model1.getPortions());
        Assertions.assertEquals(testPicture1.getUrl(), model1.getPictureUrl());
        Assertions.assertEquals(testUser1.getUsername(), model1.getAuthor());

        Assertions.assertEquals(testRecipeEntity2.getName(), model2.getName());
        Assertions.assertEquals(testRecipeEntity2.getProducts(), model2.getProducts());
        Assertions.assertEquals(testRecipeEntity2.getPreparation(), model2.getPreparation());
        Assertions.assertEquals(testRecipeEntity2.getPortions(), model2.getPortions());
        Assertions.assertEquals(testPicture2.getUrl(), model2.getPictureUrl());
        Assertions.assertEquals(testUser2.getUsername(), model2.getAuthor());
    }
}
