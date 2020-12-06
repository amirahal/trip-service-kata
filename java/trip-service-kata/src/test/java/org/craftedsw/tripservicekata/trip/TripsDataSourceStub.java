package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

import java.util.List;

public class TripsDataSourceStub implements TripsDataSource {
    @Override
    public List<Trip> findTripsByUser(User user) {
        return user.trips();
    }
}
