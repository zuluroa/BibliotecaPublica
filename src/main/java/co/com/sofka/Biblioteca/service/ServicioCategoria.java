package co.com.sofka.Biblioteca.service;

import co.com.sofka.Biblioteca.dtos.CategoriaDTO;
import co.com.sofka.Biblioteca.mapper.CategoriaMapper;
import co.com.sofka.Biblioteca.model.Categoria;
import co.com.sofka.Biblioteca.repositorios.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCategoria {

    @Autowired
    RepositorioCategoria repositorioCategoria;

    CategoriaMapper mapperCategoria = new CategoriaMapper();

    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        var categoria = repositorioCategoria.save(mapperCategoria.fromDTO(categoriaDTO));
        return mapperCategoria.fromModel(categoria);
    }

    public CategoriaDTO modificarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapperCategoria.fromDTO(categoriaDTO);
        repositorioCategoria.findById(categoria.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return mapperCategoria.fromModel(repositorioCategoria.save(categoria));
    }

    public void eliminarCategoria(String id) {
        repositorioCategoria.deleteById(id);
    }

}
