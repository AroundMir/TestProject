package service;

import DAO.ReviewDAO;

import com.sun.org.apache.regexp.internal.RE;
import entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Transactional
    public Review save(Review review){
        return reviewDAO.save(review);
    }

    @Transactional
    public void delete(Integer id){
        reviewDAO.delete(id);
    }


    public Review find(Integer id){
        return reviewDAO.findOne(id);
    }

    public List<Review> findSeveral(List<Integer> integers){

         List<Review> reviews = reviewDAO.findByIds(integers);
         return reviews;
    }

    public List<Review> showAll(){
        return  reviewDAO.findAll();
    }

    public boolean checkOnExist(Integer id){
        return reviewDAO.exists(id);
    }


}
