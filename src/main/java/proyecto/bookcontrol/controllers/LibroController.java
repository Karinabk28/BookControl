package proyecto.bookcontrol.controllers;

import proyecto.bookcontrol.domain.Libro;
import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.services.LibroService;
import proyecto.bookcontrol.Params;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping({ "/", "" })
    public String showLibros(@RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) Integer op,
            Model model) {

        if (op != null) {
            model.addAttribute("msg", Params.LIBRO[op]);
        }
        Pageable pageable = PageRequest.of(page, 6);
        Page<Libro> libros = libroService.obtenerTodosPaginado(pageable);

        model.addAttribute("listaLibro", libros.getContent());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", libros.getTotalPages());

        return "admin/libro/libroView";
    }

    @GetMapping("/nuevo")
    public String addLibro(Model model) {
        model.addAttribute("libroForm", new Libro());
        return "admin/libro/newLibroView";
    }

    @PostMapping("/nuevo/submit")
    public String addLibroSubmit(@Valid Libro libroForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/libros?op=2";
        }
        libroService.anadir(libroForm);
        return "redirect:/admin/libros?op=1";
    }

    @GetMapping("/editar/{id}")
    public String editLibro(@PathVariable Long id, Model model) {
        Libro libroForm;
        try {
            libroForm = libroService.obtenerPorId(id);
        } catch (Exception e) {
            return "redirect:/admin/libros?op=3";
        }
        model.addAttribute("libroForm", libroForm);
        return "admin/libro/editLibroView";
    }

    @PostMapping("/editar/submit")
    public String editLibroSubmit(@Valid Libro libroForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/libros?op=4";
        }
        try {
            libroService.editar(libroForm);
        } catch (NotFoundException e) {
            return "redirect:/admin/libros?op=3";
        }
        return "redirect:/admin/libros?op=5";
    }

    @GetMapping("/borrar/{id}")
    public String deleteLibro(@PathVariable Long id, Model model) {
        try {
            libroService.borrar(id);
        } catch (NotFoundException e) {
            return "redirect:/admin/libros?op=6";
        }
        return "redirect:/admin/libros?op=6";
    }

}
