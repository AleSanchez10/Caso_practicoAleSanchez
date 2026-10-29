
package com.veterinaria.sanchez.service;

import com.veterinaria.sanchez.domain.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> listarTipos();

    Optional<TipoServicio> buscarTipo(Integer id);

    void guardarTipo(TipoServicio tipo);

    void eliminarTipo(Integer id);
}
