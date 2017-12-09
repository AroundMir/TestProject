package service;

import DAO.OrderDAO;
import entity.Order;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;


    @Transactional
    public void delete(Integer id){
        orderDAO.delete(id);
    }

    @Transactional
    public Order save(Order order) {
        return orderDAO.save(order);
    }

    public List<Order> findPersonOrders(Integer personId){
        List<Order> allOrders = orderDAO.findAll();
            return allOrders.stream().filter(s -> s.getPerson().getId() == personId).collect(Collectors.toList());
    }

     public Order getById(Integer id){
        return orderDAO.findOne(id);
     }

     public List<Order> showAll(){
         return orderDAO.findAll();
    }

     public boolean checkOrderId(Integer id) {
        return orderDAO.exists(id);
    }
}
