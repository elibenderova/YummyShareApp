package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.PictureEntity;
import bg.softuni.yummyshare.repository.PictureRepository;
import bg.softuni.yummyshare.service.CloudinaryImage;
import bg.softuni.yummyshare.service.CloudinaryService;
import bg.softuni.yummyshare.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public PictureEntity createPicture(MultipartFile multipartFile) throws IOException {
        CloudinaryImage uploaded = cloudinaryService.upload(multipartFile);

        PictureEntity picture = new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl());

        return pictureRepository.save(picture);
    }

    @Transactional
    @Override
    public void deletePictureByPublicId(String publicId) {
        if (cloudinaryService.delete(publicId)){
            pictureRepository.deletePictureByPublicId(publicId);
        }
    }
}