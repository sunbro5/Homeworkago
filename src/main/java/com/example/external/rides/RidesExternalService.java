package com.example.external.rides;

import com.example.external.rides.model.Ride;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RidesExternalService {

    private static final String RIDES_URL = "/rides?offset={offset}&limit={limit}";

    @Value("${mock_api.url:https://0mj0y.mocklab.io}")
    private String apiUrl;

    private final RestTemplate mockApiRest;

    public List<Ride> getRides(int offset, int limit){
        Map<String, Integer> parameters = Map.of(
                "offset", offset ,
                "limit", limit
        );

        Optional<Ride[]> responses = Optional.ofNullable(
                mockApiRest.getForEntity(apiUrl + RIDES_URL, Ride[].class, parameters).getBody());

        return responses.map(Arrays::asList).orElseGet(List::of);

    }


}
