package proyecto.bookcontrol.controllers;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.services.LibroService;
import proyecto.bookcontrol.services.ReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class PublicLibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private ReviewService reviewService;
  


    @GetMapping("")
    public String listarLibros(Model model) {
        model.addAttribute("listaLibro", libroService.obtenerTodos());
        return "public/libro/libroListView";
    }

    @GetMapping("/{id}")
    public String detalleLibro(@PathVariable Long id, Model model) throws NotFoundException {
        Libro libro = libroService.obtenerPorId(id);
        model.addAttribute("libro", libro);
        model.addAttribute("reviews", libro.getReviews());
        return "public/libro/libroDetailView";
    }

    @GetMapping("/buscar")
    public String buscarLibro(@RequestParam String titulo, Model model) {
        List<Libro> resultados = libroService.buscarPorTitulo(titulo);
        if (resultados.isEmpty()) {
            model.addAttribute("mensaje", "Libro no encontrado");
        } else {
            model.addAttribute("librosEncontrados", resultados);
        }
        return "public/home";
    }

    @PostMapping("/{id}/valorar")
    public String valorarLibro(@PathVariable Long id, @RequestParam int valor,
            @RequestParam(required = false) String comentario, Authentication auth) throws NotFoundException {
        reviewService.valorar(id, auth.getName(), valor, comentario);
        return "redirect:/libros/" + id;
    }
  
 
   
}
