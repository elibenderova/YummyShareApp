package bg.softuni.yummyshare.web;

import bg.softuni.yummyshare.model.binding.ArticleAddBindingModel;
import bg.softuni.yummyshare.model.service.ArticleAddServiceModel;
import bg.softuni.yummyshare.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public ArticleAddBindingModel articleAddBindingModel() {
        return new ArticleAddBindingModel();
    }

    @GetMapping("/all")
    public String allArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());

        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle() {
        return "add-article";
    }

    @PostMapping("/add")
    public String confirmAddArticle(@Valid ArticleAddBindingModel articleAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("articleAddBindingModel", articleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.articleAddBindingModel", bindingResult);

            return "redirect:add";
        }

        ArticleAddServiceModel articleAddServiceModel = modelMapper.map(articleAddBindingModel, ArticleAddServiceModel.class);
        articleAddServiceModel.setAuthor(principal.getUsername());

        articleService.addArticle(articleAddServiceModel, principal);
        return "redirect:all";
    }
}

