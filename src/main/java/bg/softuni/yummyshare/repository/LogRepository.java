package bg.softuni.yummyshare.repository;

import bg.softuni.yummyshare.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogRepository extends JpaRepository<LogEntity, Long> {

}
