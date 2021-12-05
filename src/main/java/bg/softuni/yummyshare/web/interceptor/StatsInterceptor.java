package bg.softuni.yummyshare.web.interceptor;

import bg.softuni.yummyshare.service.StatisticsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StatsInterceptor implements HandlerInterceptor {
    private final StatisticsService statisticsService;

    public StatsInterceptor(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        statisticsService.onRequest();
        return true;
    }
}
