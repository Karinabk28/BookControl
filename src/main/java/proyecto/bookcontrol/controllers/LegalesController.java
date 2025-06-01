package proyecto.bookcontrol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LegalesController {

    @GetMapping("/aviso-legal")
    public String avisoLegal() {
        return "footer/avisoLegal";
    }

    @GetMapping("/accesibilidad")
    public String accesibilidad() {
        return "footer/accesibilidad";
    }

    @GetMapping("/privacidad")
    public String privacidad() {
        return "footer/privacidad";
    }
}
