package bg.softuni.yummyshare.service;

import bg.softuni.yummyshare.model.service.LogServiceModel;

import java.util.List;

public interface LogService {
    void logClear();
    List<LogServiceModel> findAll();

    void createLog(String action, Long recipeId);
}
