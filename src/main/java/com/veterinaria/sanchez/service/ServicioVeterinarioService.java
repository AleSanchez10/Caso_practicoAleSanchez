
package com.veterinaria.sanchez.service;

import com.veterinaria.sanchez.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioVeterinarioService {

    List<Servicio> listarServicios();

    Optional<Servicio> buscarServicio(Integer id);

    void guardarServicio(Servicio servicio);

    void eliminarServicio(Integer id);
}
