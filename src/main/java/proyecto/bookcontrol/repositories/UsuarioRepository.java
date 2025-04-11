package proyecto.bookcontrol.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookcontrol.domain.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
       Usuario findByNombre(String nombre);
       Usuario findByEmail(String email);
}
