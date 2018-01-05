package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "review")
public class Review implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@ManyToOne(cascade ={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

}
