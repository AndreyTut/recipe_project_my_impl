package my.tut.study.recipe.services;

import lombok.extern.slf4j.Slf4j;
import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
        log.debug("Storing recipe image");
        Recipe recipe = recipeRepository.findById(recipeId).get();

        try {
            byte[] fileBytes = new byte[0];
            fileBytes = multipartFile.getBytes();
            Byte imageFileContent[] = new Byte[fileBytes.length];
            int i = 0;
            for (byte b : fileBytes) {
                imageFileContent[i++] = b;
            }
            recipe.setImage(imageFileContent);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
