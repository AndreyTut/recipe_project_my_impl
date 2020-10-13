package my.tut.study.recipe.controllers;

import my.tut.study.recipe.services.ImageService;
import my.tut.study.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public String getImageForm(@PathVariable(name = "id") String recipeId, Model model) {
        Long id = Long.valueOf(recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable(name = "id") String recipeId, @RequestParam MultipartFile imagefile) {
        Long id = Long.valueOf(recipeId);
        imageService.saveImageFile(id, imagefile);
        return "redirect:/recipe/" + id + "/show";
    }
}
