package proyecto.bookcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.Review;

import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.LibroRepository;
import proyecto.bookcontrol.repositories.ReviewRepository;
import proyecto.bookcontrol.repositories.UsuarioRepository;

import java.util.List;

@Service
public class ReviewServiceImplBD implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void borrar(Long id) throws NotFoundException {
        reviewRepository.deleteById(id);
    }

    public List<Review> obtenerTodas() {
        return reviewRepository.findAll();
    }

    public Review obtenerPorId(Long id) throws NotFoundException {
        return reviewRepository.findById(id).orElseThrow();
    }
    
    public void valorar(Long idLibro, String nombreUsuario, Integer valor, String comentario) throws NotFoundException {
    
    }
    
    private void actualizarValoracionMedia(Libro libro) {
 
    }
    
}
