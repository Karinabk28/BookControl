package proyecto.bookcontrol.services;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;
import java.util.List;


public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Usuario anadir(Usuario usuario);
      /*Métodos que necesitare más adeltante para el CRUD */
    Usuario obtenerPorId(Long id) throws NotFoundException;
    Usuario editar(Usuario usuario) throws NotFoundException;
    void borrar(Long id) throws NotFoundException;
    List<Usuario> obtenerTodos();
}
