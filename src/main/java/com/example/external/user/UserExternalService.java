package com.example.external.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@RequiredArgsConstructor
public class UserExternalService {

    @Value("${mock_api.url:https://0mj0y.mocklab.io}")
    private String apiUrl;

    //private final RestTemplate mockApiRest;


}
