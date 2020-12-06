package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.AuthenticationUser;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private static final ArrayList<Trip> EMPTY_TRIP = new ArrayList<>();
	private AuthenticationUser authenticationUser;
	private TripsDataSource tripsDataSource;

	public TripService(AuthenticationUser authenticationUser, TripsDataSource tripsDataSource) {
		this.authenticationUser = authenticationUser;
		this.tripsDataSource = tripsDataSource;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		if (loggedUser == null)
			throw new UserNotLoggedInException();

		return user.isFriendWith(loggedUser)
			? findTripsByUser(user)
			: EMPTY_TRIP;

	}

	private List<Trip> findTripsByUser(User user) {
		return tripsDataSource.findTripsByUser(user);
	}

	private User getLoggedUser() {
		return authenticationUser.getLoggedUser();
	}

}
