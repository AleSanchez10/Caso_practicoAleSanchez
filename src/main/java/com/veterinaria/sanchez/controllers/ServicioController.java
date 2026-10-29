
package com.veterinaria.sanchez.controllers;

import com.veterinaria.sanchez.domain.Servicio;
import com.veterinaria.sanchez.service.ServicioVeterinarioService;
import com.veterinaria.sanchez.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioVeterinarioService servicioVeterinario;
    private final TipoServicioService tiposServicio;

    public ServicioController(
            ServicioVeterinarioService servicioVeterinario,
            TipoServicioService tiposServicio) {

        this.servicioVeterinario = servicioVeterinario;
        this.tiposServicio = tiposServicio;
    }

    @GetMapping
    public String mostrarServicios(Model modelo) {
        modelo.addAttribute("servicios",
                servicioVeterinario.listarServicios());
        return "servicios";
    }

    @GetMapping("/nuevo")
    public String nuevoServicio(Model modelo) {
        modelo.addAttribute("servicio", new Servicio());
        modelo.addAttribute("tipos", tiposServicio.listarTipos());
        return "formulario-servicio";
    }

    @GetMapping("/editar/{id}")
    public String editarServicio(@PathVariable Integer id,
                                 Model modelo,
                                 RedirectAttributes mensajes) {

        var servicio = servicioVeterinario.buscarServicio(id);

        if (servicio.isEmpty()) {
            mensajes.addFlashAttribute("error",
                    "Servicio no encontrado");
            return "redirect:/servicios";
        }

        modelo.addAttribute("servicio", servicio.get());
        modelo.addAttribute("tipos", tiposServicio.listarTipos());

        return "formulario-servicio";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio,
                                  RedirectAttributes mensajes) {

        servicioVeterinario.guardarServicio(servicio);

        mensajes.addFlashAttribute("exito",
                "Servicio guardado correctamente");

        return "redirect:/servicios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Integer id,
                                   RedirectAttributes mensajes) {

        try {
            servicioVeterinario.eliminarServicio(id);
            mensajes.addFlashAttribute("exito",
                    "Servicio eliminado correctamente");

        } catch (Exception error) {
            mensajes.addFlashAttribute("error",
                    "No se pudo eliminar el servicio. Puede tener citas asociadas.");
        }

        return "redirect:/servicios";
    }
}
