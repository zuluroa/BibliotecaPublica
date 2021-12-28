package co.com.sofka.Biblioteca.controller;

import co.com.sofka.Biblioteca.dtos.TipoRecursoDTO;
import co.com.sofka.Biblioteca.service.ServicioTipoRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiporecurso")
public class ControladorTipoRecurso {

    @Autowired
    ServicioTipoRecurso servicioTipoRecurso;

    @PostMapping("/crear")
    public ResponseEntity<TipoRecursoDTO> crearTipoRecurso(@RequestBody TipoRecursoDTO tipoRecursoDTO) {
        var respuesta = servicioTipoRecurso.crearTipoRecurso(tipoRecursoDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificar")
    public ResponseEntity<TipoRecursoDTO> modificarTipoRecurso(@RequestBody TipoRecursoDTO tipoRecursoDTO) {
        if (tipoRecursoDTO.getTipoRecursoId() != null) {
            return new ResponseEntity<>(servicioTipoRecurso.modificarTipoRecurso(tipoRecursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarTipoRecurso(@PathVariable("id") String id) {
        try {
            servicioTipoRecurso.eliminarTipoRecurso(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
