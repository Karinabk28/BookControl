package proyecto.bookcontrol.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.LibroRepository;

@Service
public class LibroServiceImplBD implements LibroService {

    @Autowired
    LibroRepository libroRepository;

    public Libro anadir(Libro libro) {
        try {
            return libroRepository.save(libro);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) throws NotFoundException {
        return libroRepository.findById(id).orElseThrow();
    }

    public Libro editar(Libro libro) throws NotFoundException {
        try {
            return libroRepository.save(libro);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void borrar(Long id) throws NotFoundException {
        libroRepository.deleteById(id);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }


    public Libro obtenerLibroDestacado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerLibroDestacado'");
    }


    public Page<Libro> obtenerTodosPaginado(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    
}