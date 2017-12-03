package service;

import DAO.BookDAO;
import DAO.OrderDAO;
import dto.OrderDTO;
import entity.Book;
import entity.Order;
import entity.Person;
import input.Console;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
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
    public Order save(OrderDTO order) {
        return orderDAO.save(order);
    }

    public List<Order> findPersonOrders(Integer personId){
        List<Order> allOrders = orderDAO.findAll();
            return allOrders.stream().filter(s -> s.getPerson().getId() == personId).collect(Collectors.toList());
    }

     public List<Order> showAll(){
         return orderDAO.findAll();
    }

     public boolean checkOrderId(Integer id) {
        return orderDAO.exists(id);
    }
}
