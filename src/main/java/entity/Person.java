package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(name = "person")
public class Person {

	private String name;
	private String surname;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
