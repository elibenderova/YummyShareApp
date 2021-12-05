package bg.softuni.yummyshare.service;

import bg.softuni.yummyshare.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {
    PictureEntity createPicture(MultipartFile multipartFile) throws IOException;
    void deletePictureByPublicId(String pictureId);
}
