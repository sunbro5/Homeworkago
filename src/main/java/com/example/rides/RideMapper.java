package com.example.rides;

import com.example.external.rides.model.Ride;
import com.example.comment.model.Comment;
import com.example.rides.model.RideDetailResponse;
import com.example.rides.model.RideResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RideMapper {

    RideResponse toRideResponse(Ride ride, String driverName);

    RideDetailResponse toRideDetailResponse(Ride ride, String driverName, String passengerName, List<Comment> comments);

}
