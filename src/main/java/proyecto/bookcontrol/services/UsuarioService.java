package proyecto.bookcontrol.services;

import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Usuario anadir(Usuario usuario);
    Usuario obtenerPorId(Long id) throws NotFoundException;
    Usuario editar(Usuario usuario) throws NotFoundException;
    void borrar(Long id) throws NotFoundException;
    List<Usuario> obtenerTodos();
    /*Ya lo implementare para el paginado (esto es para una tabla enorme)*/
    Page<Usuario> obtenerTodosPaginado(Pageable pageable);
}
