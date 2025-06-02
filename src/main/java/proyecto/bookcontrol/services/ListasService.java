package proyecto.bookcontrol.services;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.EstadoLectura;
import proyecto.bookcontrol.domain.Listas;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListasService {
    void borrar(Long id) throws NotFoundException;
    Listas asignarLista(Usuario usuario, Libro libro, EstadoLectura estadoLectura, boolean favorito);
    List<Libro> obtenerLibrosPorCategoria(Usuario usuario, String categoria);
    void quitarRelacion(Usuario usuario, Libro libro, boolean isFavorito);
    Page<Listas> obtenerTodosPaginado(Pageable pageable);
}