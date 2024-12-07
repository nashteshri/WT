package in.kitcoek.libraryapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Student")
public class Student {
    @Id
    private int prn;
    private String name;
    private String department;
    private String password;
    @OneToMany()
    private List<Book> issuedBooks = new ArrayList<>();
}
