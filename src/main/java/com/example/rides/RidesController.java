package com.example.rides;

import com.example.external.rides.RidesExternalService;
import com.example.external.rides.model.Ride;
import com.example.model.RideResponse;
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

    private final RidesService ridesService; //TODO add service with async

    @GetMapping()
    public List<RideResponse> test(@RequestParam int offset, @RequestParam int limit){
        return ridesService.getRides(offset, limit);
    }
}
