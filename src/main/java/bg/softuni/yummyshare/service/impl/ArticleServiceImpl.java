package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.ArticleEntity;
import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.service.ArticleAddServiceModel;
import bg.softuni.yummyshare.model.view.ArticleViewModel;
import bg.softuni.yummyshare.repository.ArticleRepository;
import bg.softuni.yummyshare.repository.UserRepository;
import bg.softuni.yummyshare.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addArticle(ArticleAddServiceModel articleAddServiceModel, UserDetails principal) {
        ArticleEntity article = modelMapper.map(articleAddServiceModel, ArticleEntity.class);

        UserEntity author = userRepository.
                findByUsername(principal.getUsername()).
                orElseThrow();

        article.
                setAuthor(author).
                setTitle(articleAddServiceModel.getTitle()).
                setText(articleAddServiceModel.getText());

        articleRepository.save(article);
    }

    @Override
    public List<ArticleViewModel> getAllArticles() {
        return articleRepository.
                findAll().
                stream().
                map(ar -> {
                    ArticleViewModel avm = modelMapper.map(ar, ArticleViewModel.class);
                    avm.
                            setAuthor(ar.getAuthor().getUsername()).
                            setTitle(ar.getTitle()).
                            setText(ar.getText());

                    return avm;
                }).
                collect(Collectors.toList());
    }
}
