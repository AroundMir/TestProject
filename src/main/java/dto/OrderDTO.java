package dto;

import entity.Book;
import entity.Order;
import entity.Person;
import service.OrderService;

import java.util.List;

public class OrderDTO {

        private Integer id;

        private Person person;

        private List<Book> books;

        public OrderDTO(){
        }


        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }

        public Person getPerson() {

            return this.person;
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPerson(Person person) {
            this.person = person;
        }

        public Order toEntity() {

            Order order = new Order();
            order.setPerson(this.person);
            order.setBooks(this.books);

            return order;
        }
    }

