package in.kitcoek.libraryapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int prn;
    private int isbn;
}
