package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    User loggedUser;
    User user;
    @Before
    public void setUp() {
        loggedUser = new User();
        user = new User();
    }

    @Test
    public void should_return_zero_trip_when_user_is_not_friend_with_logged_user() {
        user.addFriend(new User());
        TripService tripService = new TripService();

        List<Trip> tripsByUser = tripService.getTripsByUser(user);

        Assertions.assertEquals(tripsByUser, Collections.EMPTY_LIST);
    }

    @Test
    public void should_find_user_s_trips_when_user_and_logged_user_are_friends() {
        user.addFriend(loggedUser);
        user.addTrip(new Trip());
        TripService tripService = new TestableTripService();

        List<Trip> tripsByUser = tripService.getTripsByUser(user);

        Assertions.assertEquals(tripsByUser, user.trips());
    }

    @Test
    public void should_throw_error_when_user_is_not_logger_in() {
        loggedUser = null;
        TripService tripService = new TestableTripService();

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(new User());
        });
    }

    class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return user.trips();
        }
    }
}
