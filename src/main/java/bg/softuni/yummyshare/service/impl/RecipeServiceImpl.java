package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.PictureEntity;
import bg.softuni.yummyshare.model.entity.RecipeEntity;
import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.service.RecipeAddServiceModel;
import bg.softuni.yummyshare.model.view.RecipeViewModel;
import bg.softuni.yummyshare.repository.PictureRepository;
import bg.softuni.yummyshare.repository.RecipeRepository;
import bg.softuni.yummyshare.repository.UserRepository;
import bg.softuni.yummyshare.service.CloudinaryImage;
import bg.softuni.yummyshare.service.CloudinaryService;
import bg.softuni.yummyshare.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository, PictureRepository pictureRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addRecipe(RecipeAddServiceModel recipeAddServiceModel, UserDetails principal) throws IOException {
        MultipartFile image = recipeAddServiceModel.getPicture();

        UserEntity author = userRepository.
                findByUsername(principal.getUsername()).
                orElseThrow(() -> new IllegalStateException("Sorry, but this user does not exist!"));

        RecipeEntity recipe = modelMapper.map(recipeAddServiceModel, RecipeEntity.class);
        recipe.
                setAuthor(author).
                setPortions(recipeAddServiceModel.getPortions()).
                setProducts(recipeAddServiceModel.getProducts()).
                setPreparation(recipeAddServiceModel.getPreparation()).
                setName(recipeAddServiceModel.getName()).
                setPicture(getPicture(image));

        recipeRepository.save(recipe);
    }

    private PictureEntity getPicture(MultipartFile image) throws IOException {
        CloudinaryImage uploaded = cloudinaryService.upload(image);

        return pictureRepository.save(new PictureEntity()
                    .setUrl(uploaded.getUrl())
                    .setPublicId(uploaded.getPublicId()));
    }

    @Override
    public List<RecipeViewModel> getAllRecipes() {
        return recipeRepository.
                findAll().
                stream().
                map(rr -> {
                    RecipeViewModel rvm = modelMapper.map(rr, RecipeViewModel.class);
                    rvm.
                            setName(rr.getName()).
                            setPreparation(rr.getPreparation()).
                            setPortions(rr.getPortions()).
                            setAuthor(rr.getAuthor().getUsername());

                    return rvm;
                }).
                collect(Collectors.toList());
    }

//    @Override
//    public RecipeViewModel findById(Long id) {
//        return recipeRepository.findById(id)
//                .map(recipe -> {
//                    RecipeViewModel rvm = modelMapper
//                            .map(recipe, RecipeViewModel.class);
//
//                    rvm.
//                            setAuthor(recipe.getAuthor().getUsername()).
//                            setPreparation(recipe.getPreparation()).
//                            setPortions(recipe.getPortions()).
//                            setName(recipe.getName()).
//                            setProducts(recipe.getProducts());
//                    return rvm;
//                }).orElseThrow(IllegalArgumentException::new);
//    }
}
