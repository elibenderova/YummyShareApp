package bg.softuni.yummyshare.repository;

import bg.softuni.yummyshare.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
    void deletePictureByPublicId(String publicId);
}
