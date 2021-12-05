package bg.softuni.yummyshare.scheduler;

import bg.softuni.yummyshare.service.LogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogClearScheduler {
    private final LogService logService;

    public LogClearScheduler(LogService logService) {
        this.logService = logService;
    }

    //every friday at midnight
    @Scheduled(cron = "0 0 0 ? * FRI *")
    public void logClear() {
        logService.logClear();
    }
}
