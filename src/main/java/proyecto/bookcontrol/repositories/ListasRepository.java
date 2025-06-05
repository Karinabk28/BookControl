package proyecto.bookcontrol.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.EstadoLectura;
import proyecto.bookcontrol.domain.Listas;
import proyecto.bookcontrol.domain.Usuario;


public interface ListasRepository extends JpaRepository<Listas, Long> {
    List<Listas> findByUsuario(Usuario usuario);
    List<Listas> findByUsuarioAndEstadoLectura(Usuario usuario, EstadoLectura estadoLectura);
    List<Listas> findByUsuarioAndFavoritoTrue(Usuario usuario); 
    Optional<Listas> findByUsuarioAndLibro(Usuario usuario, Libro libro);
}
