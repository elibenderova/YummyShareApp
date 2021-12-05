package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.entity.LogEntity;
import bg.softuni.yummyshare.model.entity.RecipeEntity;
import bg.softuni.yummyshare.model.entity.UserEntity;
import bg.softuni.yummyshare.model.service.LogServiceModel;
import bg.softuni.yummyshare.repository.LogRepository;
import bg.softuni.yummyshare.service.LogService;
import bg.softuni.yummyshare.service.RecipeService;
import bg.softuni.yummyshare.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final RecipeService recipeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, RecipeService recipeService, UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.recipeService = recipeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void logClear() {
        logRepository.deleteAll();
    }

    @Override
    public List<LogServiceModel> findAll() {
        return logRepository.findAll().stream()
                .map(logEntity -> this.modelMapper.map(logEntity, LogServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createLog(String action, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LogEntity log = new LogEntity();
        log.
                setAction(action).
                setDateTime(LocalDateTime.now()).
                setUser(this.modelMapper.map(this.userService.findByUsername(authentication.getName()), UserEntity.class));

        logRepository.save(log);
    }
}
