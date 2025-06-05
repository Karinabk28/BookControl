package proyecto.bookcontrol.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErroresController implements ErrorController {

    @GetMapping("/error/403")
    public String error403() {
        return "error/error403";
    }

   
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int codigo = Integer.parseInt(status.toString());
            if (codigo == 404) return "error/error404";
        }

        return "error/error"; 
    }
}
