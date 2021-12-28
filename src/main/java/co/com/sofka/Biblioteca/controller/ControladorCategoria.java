package co.com.sofka.Biblioteca.controller;

import co.com.sofka.Biblioteca.dtos.CategoriaDTO;
import co.com.sofka.Biblioteca.service.ServicioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class ControladorCategoria {
    @Autowired
    ServicioCategoria servicioCategoria;

    //Controlador de Categoria

    @PostMapping("/crear")
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        var respuesta = servicioCategoria.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificar")
    public ResponseEntity<CategoriaDTO> modificarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getCategoriaId() != null) {
            return new ResponseEntity<>(servicioCategoria.modificarCategoria(categoriaDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarCategoria(@PathVariable("id") String id) {
        try {
            servicioCategoria.eliminarCategoria(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
