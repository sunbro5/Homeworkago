package com.example.external.user;

import com.example.external.rides.model.Ride;
import com.example.external.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserExternalService {

    private static final String GET_USERS_DETAIL_URL = "/users/{id}";

    @Value("${mock_api.url:https://0mj0y.mocklab.io}")
    private String apiUrl;

    private final RestTemplate mockApiRest;

    @Async
    public CompletableFuture<Optional<User>> getUserDetail(int id){
        log.debug("Get user detail for id {}", id);
        Optional<User> user = Optional.ofNullable(
                mockApiRest.getForEntity(apiUrl + GET_USERS_DETAIL_URL, User.class, Map.of("id", id)).getBody());
        log.debug("Response user {}", user);
        return CompletableFuture.completedFuture(user);
    }


}
