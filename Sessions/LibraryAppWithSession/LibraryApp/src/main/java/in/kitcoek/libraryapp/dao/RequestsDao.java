package in.kitcoek.libraryapp.dao;

import in.kitcoek.libraryapp.model.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestsDao extends JpaRepository<Requests, Integer> {
    List<Requests> findAllByPrn(int prn);
    Requests findByPrnAndIsbn(int prn, int isbn);
}
