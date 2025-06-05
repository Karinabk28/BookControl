package proyecto.bookcontrol.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookcontrol.domain.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {
       Libro findByTitulo(String titulo);
       List<Libro> findByTituloContainingIgnoreCase(String titulo);
       boolean existsByTituloAndAutor(String titulo, String autor);

}
