package proyecto.bookcontrol.controllers;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.services.LibroService;
import proyecto.bookcontrol.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LibroService libroService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/register";
    }

    @PostMapping("/registro")
    public String register(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/auth/login?registro=exitoso";
    }

    @GetMapping("/default")
    public String redireccionarSegunRol(Authentication authentication) {
        String authorities = authentication.getAuthorities().toString();
        if (authorities.contains("ROLE_ADMIN")) {
            return "redirect:/admin/home";
        } else if (authorities.contains("ROLE_USUARIO")) {
            return "redirect:/home";
        }
        return "redirect:/login?error=rol_desconocido";
    }

    @GetMapping("/home")
    public String userHome(Model model) {
        Libro libroDestacado = libroService.obtenerLibroDestacado();
        model.addAttribute("libroDestacado", libroDestacado);
        return "public/home";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/libro/libroView";
    }
}
