package proyecto.bookcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.repositories.LibroRepository;

@RestController
@RequestMapping("/api/libros")
public class LibroApiController {

    @Autowired
    private LibroRepository libroRepository;

    @PostMapping
    public ResponseEntity<?> guardarLibro(@RequestBody Libro libro) {
        if (!libroRepository.existsByTituloAndAutor(libro.getTitulo(), libro.getAutor())) {
            libroRepository.save(libro);
            return ResponseEntity.ok("Libro guardado");
        }
        return ResponseEntity.ok("Libro duplicado ignorado");
    }

}
