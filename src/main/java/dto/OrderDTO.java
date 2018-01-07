package dto;

import entity.Book;
import entity.Order;
import entity.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import service.OrderService;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class OrderDTO {

        private Person person;

        private List<Book> books;

        private Integer id;

        public Order toEntity() {

            Order order = new Order();
            order.setPerson(this.person);
            order.setBooks(this.books);
            order.setId(this.id != null ? this.id : null);
            return order;
        }
    }

