package proyecto.bookcontrol.controllers;

import proyecto.bookcontrol.exceptions.NotFoundException;
import proyecto.bookcontrol.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping({ "/", "" })
    public String showReviews(Model model) {
        model.addAttribute("listaReview", reviewService.obtenerTodas());
        return "admin/review/reviewView";
    }

    @GetMapping("/borrar/{id}")
    public String deleteReview(@PathVariable Long id) {
        try {
            reviewService.borrar(id);
        } catch (NotFoundException e) {
            return "redirect:/admin/reviews?op=6";
        }
        return "redirect:/admin/reviews?op=6";
    }
}
