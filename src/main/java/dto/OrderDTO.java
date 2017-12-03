package dto;

import entity.Book;
import entity.Person;
import service.OrderService;

import java.util.List;

public class OrderDTO {

    public class Order {

        private Person person;

        private List<Book> books;

        public Order(){
        }

        public Order(Person person, List<Book> books){
            this.person = person;
            this.books = books;
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
}
