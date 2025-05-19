package proyecto.bookcontrol.services;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.exceptions.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

public interface LibroService {
    Libro anadir(Libro libro);
    Libro obtenerPorId(Long id) throws NotFoundException;
    Libro editar(Libro libro) throws NotFoundException;
    void borrar(Long id) throws NotFoundException;
    List<Libro> obtenerTodos();
    List<Libro> buscarPorTitulo(String titulo);
    Libro obtenerLibroDestacado();
    Page<Libro> obtenerTodosPaginado(Pageable pageable);

}
