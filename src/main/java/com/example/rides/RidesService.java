package com.example.rides;

import com.example.RideMapper;
import com.example.external.rides.RidesExternalService;
import com.example.external.rides.model.Ride;
import com.example.external.user.UserExternalService;
import com.example.model.RideResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RidesService {

    private final RidesExternalService ridesExternalService;
    private final UserExternalService userExternalService;
    private final RideMapper rideMapper;

    public List<RideResponse> getRides(int offset, int limit) {
        CompletableFuture<List<Ride>> rideFuture = ridesExternalService.getRides(offset, limit);
        //TODO userExternalService
        CompletableFuture.allOf(rideFuture).join();
        return parseRideResponse(rideFuture);
    }

    private List<RideResponse> parseRideResponse(CompletableFuture<List<Ride>> listCompletableFuture){
        try {
            return listCompletableFuture.get().stream()
                    .map(rideMapper::from)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Did not process external call", e);
            return List.of();
        }
    }


}
