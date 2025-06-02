package proyecto.bookcontrol.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ErroresController implements ErrorController {

    @GetMapping("/error/403")
    public String error403() {
        return "error/error403";
    }

   
   
}
