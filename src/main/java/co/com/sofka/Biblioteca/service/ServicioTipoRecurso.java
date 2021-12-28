package co.com.sofka.Biblioteca.service;

import co.com.sofka.Biblioteca.dtos.TipoRecursoDTO;
import co.com.sofka.Biblioteca.mapper.TipoRecursoMapper;
import co.com.sofka.Biblioteca.model.TipoRecurso;
import co.com.sofka.Biblioteca.repositorios.RepositorioTipoRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioTipoRecurso {

    @Autowired
    RepositorioTipoRecurso repositorioTipoRecurso;

    TipoRecursoMapper mapperTipoRecurso = new TipoRecursoMapper();

    public TipoRecursoDTO crearTipoRecurso(TipoRecursoDTO tipoRecursoDTO) {
        var tipoRecurso = repositorioTipoRecurso.save(mapperTipoRecurso.fromDTO(tipoRecursoDTO));
        return mapperTipoRecurso.fromModel(tipoRecurso);
    }

    public TipoRecursoDTO modificarTipoRecurso(TipoRecursoDTO tipoRecursoDTO) {
        TipoRecurso tipoRecurso = mapperTipoRecurso.fromDTO(tipoRecursoDTO);
        repositorioTipoRecurso.findById(tipoRecurso.getTipoRecursoId()).orElseThrow(() -> new RuntimeException("Tipo de recurso no encontrado"));
        return mapperTipoRecurso.fromModel(repositorioTipoRecurso.save(tipoRecurso));
    }

    public void eliminarTipoRecurso(String id) {
        repositorioTipoRecurso.deleteById(id);
    }
}
