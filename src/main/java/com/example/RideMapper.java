package com.example;

import com.example.external.rides.model.Ride;
import com.example.model.RideResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RideMapper {

    RideResponse from(Ride ride);

}
