package in.kitcoek.libraryapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Librarian")
public class Librarian {
    @Id
    private int id;
    private String name;
    private String address;
    private String password;
    private int salary;
}
