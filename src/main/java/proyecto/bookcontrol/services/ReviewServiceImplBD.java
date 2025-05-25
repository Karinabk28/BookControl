package proyecto.bookcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.Review;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.LibroRepository;
import proyecto.bookcontrol.repositories.ReviewRepository;
import proyecto.bookcontrol.repositories.UsuarioRepository;

import java.util.List;

@Service
public class ReviewServiceImplBD implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
        if (valor == null || valor < 1 || valor > 5) {
            throw new IllegalArgumentException("La valoración es obligatoria y debe estar entre 1 y 5.");
        }
        
        Libro libro = libroRepository.findById(idLibro).orElseThrow();
        Usuario usuario = usuarioRepository.findByNombre(nombreUsuario);
        Review review = new Review();
        review.setLibro(libro);
        review.setUsuario(usuario);
        review.setValoracion(valor);
        review.setComentario(comentario != null ? comentario.trim() : "");
        reviewRepository.save(review);
        actualizarValoracionMedia(libro);
    }
    
    private void actualizarValoracionMedia(Libro libro) {
        List<Review> reseñas = reviewRepository.findByLibro(libro);
        if (reseñas == null || reseñas.isEmpty()) {
            libro.setValoracionMedia(null);
        } else {
            int suma = 0;
            for (int i = 0; i < reseñas.size(); i++) {
                suma += reseñas.get(i).getValoracion();
            }
            double media = suma / reseñas.size();
            libro.setValoracionMedia(media);
        }
        libroRepository.save(libro);
    }
    
}
