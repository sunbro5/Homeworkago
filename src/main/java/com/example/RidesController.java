package com.example;

import com.example.external.rides.RidesExternalService;
import com.example.external.rides.model.Ride;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rides")
public class RidesController {

    private final RidesExternalService ridesExternalService; //TODO add service with async

    @GetMapping()
    public List<Ride> test(@RequestParam int offset, @RequestParam int limit){
        return ridesExternalService.getRides(offset, limit); // TODO RideResponse
    }
}
