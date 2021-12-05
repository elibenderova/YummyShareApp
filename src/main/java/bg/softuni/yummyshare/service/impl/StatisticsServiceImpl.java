package bg.softuni.yummyshare.service.impl;

import bg.softuni.yummyshare.model.view.StatsViewModel;
import bg.softuni.yummyshare.service.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private int anonymousRequests;
    private int authRequests;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authRequests++;
        } else {
            anonymousRequests++;
        }
    }

    @Override
    public StatsViewModel getStatistics() {
        return new StatsViewModel(authRequests, anonymousRequests);
    }
}
