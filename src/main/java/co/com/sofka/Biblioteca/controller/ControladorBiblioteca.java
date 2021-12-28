package co.com.sofka.Biblioteca.controller;

import co.com.sofka.Biblioteca.dtos.RespuestaCategoriaDTO;
import co.com.sofka.Biblioteca.dtos.RespuestaRecursoDTO;
import co.com.sofka.Biblioteca.dtos.RespuestaTipoRecursoDTO;
import co.com.sofka.Biblioteca.service.ServicioBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca")
public class ControladorBiblioteca {
    @Autowired
    ServicioBiblioteca servicioBiblioteca;

    @GetMapping("/consultar/{id}")
    public ResponseEntity<RespuestaRecursoDTO> consultarRecurso(@PathVariable("id") String id) {
        RespuestaRecursoDTO respuesta = servicioBiblioteca.consultarRecurso(id);
        if (respuesta != null) {
            return new ResponseEntity(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity(respuesta, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<RespuestaRecursoDTO> prestarRecurso(@PathVariable("id") String id) {
        RespuestaRecursoDTO respuesta = servicioBiblioteca.prestarRecurso(id);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @PutMapping("/devolver/{id}")
    public ResponseEntity<RespuestaRecursoDTO> devolverRecurso(@PathVariable("id") String id) {
        RespuestaRecursoDTO respuesta = servicioBiblioteca.devolverRecurso(id);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/listaporcategoria/{id}")
    public ResponseEntity<RespuestaCategoriaDTO> listarPorCategoria(@PathVariable("id") String id) {
        var repuesta = servicioBiblioteca.consultarPorCategoria(id);
        if (repuesta.getRecursosCategoria().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repuesta, HttpStatus.OK);
    }


    @GetMapping("/listatiporecurso/{id}")
    public ResponseEntity<RespuestaTipoRecursoDTO> listarPorTipoRecurso(@PathVariable("id") String id) {
        var respuesta = servicioBiblioteca.consultarPorTipoRecurso(id);
        if (respuesta.getListTipoRecurso().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }


}
