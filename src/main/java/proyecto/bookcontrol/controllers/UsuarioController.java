package proyecto.bookcontrol.controllers;

import proyecto.bookcontrol.domain.Usuario;
import proyecto.bookcontrol.services.UsuarioService;
import proyecto.bookcontrol.Params;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    /*Mirar lo de la paginacion */
    @GetMapping({ "/", "" })
    public String showUsuarios(@RequestParam(required = false) Integer op,
            Model model) {
        if (op != null) {
            model.addAttribute("msg", Params.USER[op]);
        }
        model.addAttribute("listaUsuario", usuarioService.obtenerTodos());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Usuario usuario = usuarioRepository.findByNombre(authentication.getName());
            model.addAttribute("idUsuario", usuario.getId());
        }
        return "usuario/usuarioView";
    }

    @GetMapping("/nuevo")
    public String addUsuario(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "/admin/usuario/newUsuarioView";
    }

    @PostMapping("/nuevo/submit")
    public String addUsuarioSubmit(@Valid Usuario usuarioForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/usuarios?op=2";
        }
        usuarioService.anadir(usuarioForm);
        return "redirect:/admin/usuarios?op=1";
    }

    @GetMapping("/editar/{id}")
    public String editUsuario(@PathVariable Long id, Model model) {
        Usuario usuarioForm;
        try {
            usuarioForm = usuarioService.obtenerPorId(id);
        } catch (Exception e) {
            return "redirect:/admin/usuarios?op=3";
        }
        model.addAttribute("usuarioForm", usuarioForm);
        return "/admin/usuario/editUsuarioView";
    }

    @PostMapping("/editar/submit")
    public String editusuarioSubmit(@Valid Usuario usuarioForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/usuarios?op=4";
        }
        try {
            usuarioService.editar(usuarioForm);
        } catch (NotFoundException e) {
            return "redirect:/admin/usuarios?op=3";
        }
        return "redirect:/admin/usuarios?op=5";
    }

    @GetMapping("/borrar/{id}")
    public String deleteusuario(@PathVariable Long id, Model model) throws NotFoundException {
        try {
            usuarioService.borrar(id);
        } catch (NotFoundException e) {
            return "redirect:/admin/usuarios?op=6";
        }
        return "redirect:/admin/usuarios?op=6";
    }

}