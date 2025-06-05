package proyecto.bookcontrol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByLibro(Libro libro);
}
