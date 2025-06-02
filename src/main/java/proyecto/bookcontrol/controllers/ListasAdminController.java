package proyecto.bookcontrol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import proyecto.bookcontrol.Params;
import proyecto.bookcontrol.domain.Listas;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.services.ListasService;

@Controller
@RequestMapping("/admin/listas")
public class ListasAdminController {

    @Autowired
    private ListasService listasService;

    @GetMapping({ "/", "" })
    public String showLista(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) Integer op, Model model) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Listas> listas = listasService.obtenerTodosPaginado(pageable);
        model.addAttribute("listaListas", listas.getContent());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", listas.getTotalPages());

        if (op != null) {
            model.addAttribute("msg", Params.LISTA[op]);
        }

        return "admin/lista/listaView";
    }

    @GetMapping("/borrar/{id}")
    public String borrarLista(@PathVariable Long id) {
        try {
            listasService.borrar(id);
            return "redirect:/admin/listas?op=1";
        } catch (NotFoundException e) {
            return "redirect:/admin/listas?op=2";
        }
    }
}
