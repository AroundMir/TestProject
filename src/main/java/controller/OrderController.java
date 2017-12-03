package controller;

import dto.BookDTO;
import dto.OrderDTO;
import entity.Book;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;
import service.OrderService;
import service.PersonService;

@RestController
@RequestMapping(path="order")
public class OrderController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO.toEntity());
    }
}
