
package com.veterinaria.sanchez.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactoController {

    @GetMapping("/contacto")
    public String mostrarContacto() {
        return "contacto";
    }

    @PostMapping("/contacto")
    public String enviarConsulta(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String mascota,
            @RequestParam String mensaje,
            RedirectAttributes mensajes) {

        mensajes.addFlashAttribute("exito",
                "Gracias " + nombre + ", recibimos tu consulta sobre "
                + mascota + ".");

        return "redirect:/contacto";
    }
}
