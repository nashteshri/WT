package in.kitcoek.libraryapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Book")
public class Book {
    @Id
    private int isbn;
    private String title;
    private String author;
    private int publishYear;
}
