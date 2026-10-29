
package com.veterinaria.sanchez.controllers;

import com.veterinaria.sanchez.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitaController {

    private final CitaService servicioCitas;

    public CitaController(CitaService servicioCitas) {
        this.servicioCitas = servicioCitas;
    }

    @GetMapping("/citas")
    public String mostrarCitas(Model modelo) {

        modelo.addAttribute("citas",
                servicioCitas.listarCitas());

        return "citas";
    }
}
