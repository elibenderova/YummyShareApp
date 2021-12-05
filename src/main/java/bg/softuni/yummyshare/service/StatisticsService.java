package bg.softuni.yummyshare.service;

import bg.softuni.yummyshare.model.view.StatsViewModel;

public interface StatisticsService {
    void onRequest();
    StatsViewModel getStatistics();
}
