package controller;

import DAO.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;
import service.OrderService;

@RestController
public class MainController {


    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(path = "test1", method = RequestMethod.GET)
    public String test1() {
        System.out.println("Tets1");
        bookService.testService();
        return "Test1";
    }

    @RequestMapping(path = "test2", method = RequestMethod.GET)
    public String test2() {
        System.out.println("Tets2");
        orderService.orderTest();
        return "Test2";
    }


}
