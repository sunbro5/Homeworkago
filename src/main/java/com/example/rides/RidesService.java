package com.example.rides;

import com.example.comment.CommentService;
import com.example.comment.model.Comment;
import com.example.external.rides.RidesExternalService;
import com.example.external.rides.model.Ride;
import com.example.external.user.UserExternalService;
import com.example.external.user.model.User;
import com.example.rides.model.RideDetailResponse;
import com.example.rides.model.RideResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RidesService {

    private final RidesExternalService ridesExternalService;
    private final UserExternalService userExternalService;

    private final CommentService commentService;
    private final RideMapper rideMapper;

    public List<RideResponse> getRides(int offset, int limit) {
        List<Ride> rides = ridesExternalService.getRides(offset, limit);
        List<Integer> driverIds = rides.stream()
                .map(Ride::driverId)
                .toList();

        Map<Integer, String> driverNames = loadUserNamesAsync(driverIds);

        return rides.stream()
                .map(ride -> rideMapper.toRideResponse(ride, driverNames.get(ride.driverId())))
                .toList();
    }

    public Map<Integer, String> loadUserNamesAsync(List<Integer> userIds) {
        List<CompletableFuture<Optional<User>>> driversFuture = userIds.stream()
                .map(userExternalService::getUserDetail)
                .toList();

        log.debug("Future all !!");
        CompletableFuture.allOf(driversFuture.toArray(new CompletableFuture[0])).join();
        log.debug("Future all get !!");

        return driversFuture.stream()
                .map(this::getUserFromFuture)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableMap(User::id, this::getUserName, (s, s2) -> s));
    }

    private String getUserName(User user) {
        return String.format("%s %s", user.firstName(), user.lastName());
    }

    private Optional<User> getUserFromFuture(CompletableFuture<Optional<User>> user) {
        return getFromFuture(user, Optional.empty());
    }
    private <T> T getFromFuture(CompletableFuture<T> user, T defaultValue) {
        try {
            return user.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Did not process external call", e);
            Thread.currentThread().interrupt();
            return defaultValue;
        }
    }


    public Optional<RideDetailResponse> getRideDetail(String id) {
        return ridesExternalService.getRideDetail(id)
                .map(this::fillRideDetailResponse);
    }

    private RideDetailResponse fillRideDetailResponse(Ride ride){
        CompletableFuture<Optional<User>> driverFuture = userExternalService.getUserDetail(ride.driverId());
        CompletableFuture<Optional<User>> passengerFuture = userExternalService.getUserDetail(ride.passengerId());
        CompletableFuture<List<Comment>> commentsFuture = commentService.getCommentForRideId(ride.id());

        CompletableFuture.allOf(driverFuture, passengerFuture, commentsFuture).join();

        String driverName = getUserFromFuture(driverFuture)
                .map(this::getUserName)
                .orElse(null);

        String passengerName = getUserFromFuture(passengerFuture)
                .map(this::getUserName)
                .orElse(null);

        List<Comment> comments = getFromFuture(commentsFuture, List.of());

        return rideMapper.toRideDetailResponse(ride, driverName, passengerName, comments);
    }
}
