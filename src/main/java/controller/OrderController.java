package controller;

import dto.BookDTO;
import dto.OrderDTO;
import entity.Book;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.BookService;
import service.OrderService;
import service.PersonService;

public class OrderController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public Book createOrder(@RequestBody OrderDTO orderDTO) {

        orderService.save(orderDTO.toEn)

        return
    }
}
