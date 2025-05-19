package proyecto.bookcontrol.services;

import proyecto.bookcontrol.domain.Review;
import proyecto.bookcontrol.exceptions.NotFoundException;

import java.util.List;

public interface ReviewService {
    void borrar(Long id) throws NotFoundException;
    List<Review> obtenerTodas();
    Review obtenerPorId(Long id) throws NotFoundException;
    void valorar(Long idLibro, String nombreUsuario, Integer valor, String comentario) throws NotFoundException;
}
