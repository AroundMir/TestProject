package entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(name = "\"order\"")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, targetEntity = Book.class)
    @JoinTable(name = "order_book",
            joinColumns = {
                    @JoinColumn(name = "order_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id", nullable = false)})
    private List<Book> books;
}
