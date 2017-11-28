package service;

import DAO.BookDAO;
import DAO.OrderDAO;
import entity.Book;
import entity.Order;
import entity.Person;
import input.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public Order orderTest(){
        Order order = orderDAO.findOne(1);
        String a = "1";
        return order;
    }

    // Вопрос как лучше создать коллекция Книга и количество. Потом
    // сравняв это с тем что есть

    public boolean deleteOrder(Integer orderId){
        if(checkOrderId(orderId)){
            orderDAO.delete(orderId);
            return true;
        }
        return false;
    }


    public Order createOrder(List<Book> books, Person person) {

        Order order = new Order(person, books);
        return orderDAO.save(order);

    }

    public List<Order> PersonOrders(List<Person> persons,
                                        PersonService personService, Integer personId){

        List<Order> allOrders = orderDAO.findAll();

        if (personService.checkPersonId(personId)) {
            return allOrders.stream().filter(s -> s.getPerson().getId() == personId).collect(Collectors.toList());
        }
        return null;
    }

     public List<Order> AllOrders(){

        List<Order> orders = orderDAO.findAll();
        return orders;
    }

    public boolean checkOrderId(Integer id) {
        List<Order> orders = orderDAO.findAll();
        return orders.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).size() > 0;
    }
}
