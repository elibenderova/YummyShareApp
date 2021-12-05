package bg.softuni.yummyshare.model.view;

public class StatsViewModel {
    private final int authRequests;
    private final int anonRequests;

    public StatsViewModel(int authRequests, int anonRequests) {
        this.authRequests = authRequests;
        this.anonRequests = anonRequests;
    }

    public int getTotalRequests() {
        return anonRequests + authRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }


    public int getAnonRequests() {
        return anonRequests;
    }
}

