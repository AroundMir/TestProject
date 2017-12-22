package controller;

import dto.ReviewDTO;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ReviewService;

@Controller
@RequestMapping(path = "review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST)
    public Review create(@RequestBody ReviewDTO reviewDTO){
       return reviewService.save(reviewDTO.toEntity());
    }

    @RequestMapping(path = "/{review}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable(name = "review", required = true) Integer id,
                                 @RequestBody ReviewDTO reviewDTO){
        if(!reviewService.checkOnExist(id)){
            ResponseEntity.badRequest().body("id not found");
        }
        Review review = reviewDTO.toEntity();
        review.setId(id);
        return ResponseEntity.ok(reviewService.save(review));
    }


    @RequestMapping(path = "/{review}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(name = "review", required = true) Integer id){
        try{
            reviewService.delete(id);
           return   ResponseEntity.ok().build();
        } catch (Throwable ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

        @RequestMapping(path = "/{review}",  method = RequestMethod.GET)
         public Review get(@PathVariable(name = "review", required =  true) Integer id){
            return reviewService.find(id);
        }



}
