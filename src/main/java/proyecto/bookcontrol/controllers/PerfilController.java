package proyecto.bookcontrol.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.UsuarioRepository;
import proyecto.bookcontrol.services.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("")
    public String viewProfile(Model model, Authentication auth) {
        Usuario usuario = usuarioRepository.findByNombre(auth.getName());
        model.addAttribute("usuario", usuario);
        return "public/perfil/perfilView";
    }

    @GetMapping("/editar")
    public String editProfile(Model model, Authentication auth) {
        Usuario usuario = usuarioRepository.findByNombre(auth.getName());
        model.addAttribute("usuarioForm", usuario);
        return "public/perfil/editPerfilView";
    }

    @PostMapping("/editar/submit")
    public String editProfileSubmit(@Valid Usuario usuarioForm, BindingResult bindingResult, Authentication auth) {
        if (bindingResult.hasErrors()) {
            return "redirect:/perfil?editado"; 
        }
        Usuario actual = usuarioRepository.findByNombre(auth.getName());
        usuarioForm.setId(actual.getId());
        try {
            usuarioService.editar(usuarioForm);
        } catch (NotFoundException e) {
            return "redirect:/perfil?op=error"; 
        }
    
        return "redirect:/perfil?op=editado";
    }
    
    @GetMapping("/borrar")
    public String confirmDelete() {
        return "public/perfil/confirmarBorrado";
    }

    @PostMapping("/borrar")
    public String deleteProfile(Authentication auth) {
        Usuario usuario = usuarioRepository.findByNombre(auth.getName());
        try {
            usuarioService.borrar(usuario.getId());
        } catch (NotFoundException e) {
            return "redirect:/perfil?error=borrado";
        }
        return "redirect:/logout";
    }
}
