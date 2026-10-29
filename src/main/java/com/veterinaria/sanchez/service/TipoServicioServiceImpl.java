
package com.veterinaria.sanchez.service;

import com.veterinaria.sanchez.domain.TipoServicio;
import com.veterinaria.sanchez.repository.TipoServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository repositorio;

    public TipoServicioServiceImpl(TipoServicioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<TipoServicio> listarTipos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<TipoServicio> buscarTipo(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public void guardarTipo(TipoServicio tipo) {
        repositorio.save(tipo);
    }

    @Override
    public void eliminarTipo(Integer id) {
        TipoServicio tipo = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El tipo de servicio no existe"));

        if (!tipo.getServicios().isEmpty()) {
            throw new IllegalStateException(
                    "No se puede eliminar un tipo que tiene servicios asociados");
        }

        repositorio.delete(tipo);
    }
}
