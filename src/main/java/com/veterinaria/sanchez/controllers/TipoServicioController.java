
package com.veterinaria.sanchez.controllers;

import com.veterinaria.sanchez.domain.TipoServicio;
import com.veterinaria.sanchez.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService servicioTipos;

    public TipoServicioController(TipoServicioService servicioTipos) {
        this.servicioTipos = servicioTipos;
    }

    @GetMapping
    public String mostrarTipos(Model modelo) {
        modelo.addAttribute("tipos", servicioTipos.listarTipos());
        return "tipos";
    }

    @GetMapping("/nuevo")
    public String nuevoTipo(Model modelo) {
        modelo.addAttribute("tipoServicio", new TipoServicio());
        return "formulario-tipo";
    }

    @GetMapping("/editar/{id}")
    public String editarTipo(@PathVariable Integer id, Model modelo,
                             RedirectAttributes mensajes) {

        var tipo = servicioTipos.buscarTipo(id);

        if (tipo.isEmpty()) {
            mensajes.addFlashAttribute("error", "Tipo de servicio no encontrado");
            return "redirect:/tipos";
        }

        modelo.addAttribute("tipoServicio", tipo.get());
        return "formulario-tipo";
    }

    @PostMapping("/guardar")
    public String guardarTipo(@ModelAttribute TipoServicio tipoServicio,
                              RedirectAttributes mensajes) {

        servicioTipos.guardarTipo(tipoServicio);
        mensajes.addFlashAttribute("exito", "Tipo de servicio guardado correctamente");

        return "redirect:/tipos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTipo(@PathVariable Integer id,
                               RedirectAttributes mensajes) {

        try {
            servicioTipos.eliminarTipo(id);
            mensajes.addFlashAttribute("exito", "Tipo de servicio eliminado");
        } catch (IllegalStateException | IllegalArgumentException error) {
            mensajes.addFlashAttribute("error", error.getMessage());
        }

        return "redirect:/tipos";
    }
}
