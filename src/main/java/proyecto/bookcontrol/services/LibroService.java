package proyecto.bookcontrol.services;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.exceptions.NotFoundException;
import java.util.List;

public interface LibroService {
    Libro registrarLibro(Libro libro);
    Libro anadir(Libro libro);
    Libro obtenerPorId(Long id) throws NotFoundException;
    Libro editar(Libro libro) throws NotFoundException;
    void borrar(Long id) throws NotFoundException;
    List<Libro> obtenerTodos();
    List<Libro> buscarPorTitulo(String titulo); //Quiero q la página de inicio tenga un buscador para buscar un libro 
    Libro obtenerLibroDestacado(); //Q el home me muestre un libro aleatorio ¿? 
}
