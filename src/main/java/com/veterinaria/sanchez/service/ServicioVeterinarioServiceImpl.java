
package com.veterinaria.sanchez.service;

import com.veterinaria.sanchez.domain.Servicio;
import com.veterinaria.sanchez.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ServicioVeterinarioServiceImpl
        implements ServicioVeterinarioService {

    private final ServicioRepository repositorio;

    public ServicioVeterinarioServiceImpl(
            ServicioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Servicio> listarServicios() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Servicio> buscarServicio(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public void guardarServicio(Servicio servicio) {
        repositorio.save(servicio);
    }

    @Override
    public void eliminarServicio(Integer id) {
        repositorio.deleteById(id);
    }
}
