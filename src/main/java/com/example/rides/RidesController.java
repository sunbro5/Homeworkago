package com.example.rides;

import com.example.comment.CommentService;
import com.example.comment.model.CommentAddRequest;
import com.example.rides.model.RideDetailResponse;
import com.example.rides.model.RideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rides")
public class RidesController {

    private final RidesService ridesService;
    private final CommentService commentService;

    @GetMapping()
    public List<RideResponse> rides(@RequestParam int offset, @RequestParam int limit){
        return ridesService.getRides(offset, limit);
    }

    @GetMapping("/{id}")
    public RideDetailResponse rideDetail(@PathVariable String id){
        return ridesService.getRideDetail(id)
                .orElse(null);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/comment")
    public void addCommentForRide(@RequestBody CommentAddRequest commentAddRequest){
        commentService.addComment(commentAddRequest);
    }

}
