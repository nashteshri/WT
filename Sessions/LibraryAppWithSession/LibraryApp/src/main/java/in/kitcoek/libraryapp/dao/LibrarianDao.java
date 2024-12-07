package in.kitcoek.libraryapp.dao;

import in.kitcoek.libraryapp.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianDao extends JpaRepository<Librarian, Integer> {
}
