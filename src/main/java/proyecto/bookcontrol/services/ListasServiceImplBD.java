package proyecto.bookcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.EstadoLectura;
import proyecto.bookcontrol.domain.Listas;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.ListasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListasServiceImplBD implements ListasService {

    @Autowired
    private ListasRepository listasRepository;

    public void borrar(Long id) throws NotFoundException {
        listasRepository.deleteById(id);
    }
 
    public Listas asignarLista(Usuario usuario, Libro libro, EstadoLectura estadoLectura, boolean favorito) {
        Listas relacion = listasRepository.findByUsuarioAndLibro(usuario, libro).orElse(null);
        // Sin este if me sobreescribe (me permite añadir dos veces el mismo)
        if (relacion == null) {
            relacion = new Listas();
            relacion.setUsuario(usuario);
            relacion.setLibro(libro);
        }
        relacion.setEstadoLectura(estadoLectura);
        relacion.setFavorito(favorito);
        return listasRepository.save(relacion);
    }

    public List<Libro> obtenerLibrosPorCategoria(Usuario usuario, String categoria) {
        List<Listas> relaciones;
    
        if ("favoritos".equalsIgnoreCase(categoria)) {
            relaciones = listasRepository.findByUsuarioAndFavoritoTrue(usuario);
        } else {
            EstadoLectura estado = EstadoLectura.valueOf(categoria.replace("-", "_").toUpperCase());
            relaciones = listasRepository.findByUsuarioAndEstadoLectura(usuario, estado);
        }
    
        List<Libro> libros = new ArrayList<>();
        for (int i = 0; i < relaciones.size(); i++) {
            libros.add(relaciones.get(i).getLibro());
        }
        return libros;
    }
    
    public void quitarRelacion(Usuario usuario, Libro libro, boolean isFavorito) {
        Listas relacion = listasRepository.findByUsuarioAndLibro(usuario, libro).orElse(null);
        if (relacion != null) {
            if (isFavorito) {
                relacion.setFavorito(false);  // Quitar de favoritos
            } else {
                relacion.setEstadoLectura(null);  // Quitar el estado de lectura
            }
    
            // Si el libro ya no tiene estado de lectura ni es favorito, eliminar la relación
            if (relacion.getEstadoLectura() == null && !relacion.isFavorito()) {
                listasRepository.delete(relacion);  // Eliminar la relación completamente
            } else {
                listasRepository.save(relacion);  // Si aún tiene estado de lectura o es favorito, guardarla
            }
        }
    }
    

    public Page<Listas> obtenerTodosPaginado(Pageable pageable) {
        return listasRepository.findAll(pageable);
    }

}
