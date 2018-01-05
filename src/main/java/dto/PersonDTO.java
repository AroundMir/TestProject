package dto;
import entity.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@NoArgsConstructor
public class PersonDTO {

    private String name;
    private String surname;

    private int id;

    public Person toEntity() {
        Person person = new Person();
        person.setName(this.name);
        person.setSurname(this.surname);
        return person;
    }
}
