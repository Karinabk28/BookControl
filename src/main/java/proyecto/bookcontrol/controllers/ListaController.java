package proyecto.bookcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.bookcontrol.domain.*;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.UsuarioRepository;
import proyecto.bookcontrol.services.LibroService;
import proyecto.bookcontrol.services.ListasService;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/listas")
public class ListaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ListasService listasService;
    @Autowired
    private LibroService libroService;

    @GetMapping("")
    public String showLista(Authentication auth, Model model) {
        Usuario usuario = usuarioRepository.findByNombre(auth.getName());
        model.addAttribute("favoritos", listasService.obtenerLibrosPorCategoria(usuario, "favoritos"));
        model.addAttribute("quieroLeer", listasService.obtenerLibrosPorCategoria(usuario, "QUIERO_LEER"));
        model.addAttribute("leyendo", listasService.obtenerLibrosPorCategoria(usuario, "LEYENDO"));
        model.addAttribute("leido", listasService.obtenerLibrosPorCategoria(usuario, "LEIDO"));
        return "public/listas/listaView";
    }

    @GetMapping("/{categoria}")
    public String viewCategory(@PathVariable String categoria, Authentication auth, Model model) {
        Usuario usuario = usuarioRepository.findByNombre(auth.getName());
        List<Libro> libros = listasService.obtenerLibrosPorCategoria(usuario, categoria);

        model.addAttribute("titulo", categoria);
        model.addAttribute("libros", libros);

        return "public/listas/librosPorCategoria";
    }

    @PostMapping("/eliminar")
    public String eliminarDeLista(@RequestParam("libroId") Long libroId, @RequestParam("tipo") String tipo,
            HttpServletRequest request, Authentication auth) throws NotFoundException {

        listasService.quitarRelacion(usuarioRepository.findByNombre(auth.getName()), libroService.obtenerPorId(libroId),
                tipo.equals("favoritos"));

        return "redirect:" + request.getHeader("Referer");
    }

}