
package com.veterinaria.sanchez.service;

import com.veterinaria.sanchez.domain.Cita;
import com.veterinaria.sanchez.repository.CitaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repositorio;

    public CitaServiceImpl(CitaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Cita> listarCitas() {
        return repositorio.findAll();
    }
}
