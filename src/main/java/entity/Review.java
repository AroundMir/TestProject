package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"review\"")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private String review;
}
