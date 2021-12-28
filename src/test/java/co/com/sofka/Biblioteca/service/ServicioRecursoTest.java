package co.com.sofka.Biblioteca.service;

import co.com.sofka.Biblioteca.dtos.RecursoDTO;
import co.com.sofka.Biblioteca.model.Recurso;
import co.com.sofka.Biblioteca.repositorios.RepositorioRecurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class ServicioRecursoTest {

    @Autowired
    private ServicioRecurso servicioRecurso;

    @Autowired
    private ServicioBiblioteca servicioBiblioteca;

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Test
    @DisplayName("Test save Success")
    public void crearRecurso(){
        var recurso1 = new Recurso();
        recurso1.setId("1234");
        recurso1.setNombreRecurso("El poder de los habitos");
        recurso1.setDisponible(true);
        recurso1.setTipoRecursoId("1234");
        recurso1.setCategoriaId("1");
        recurso1.setFecha(LocalDate.now());

        var recurso2 = new RecursoDTO();
        recurso2.setId("12345");
        recurso2.setNombreRecurso("Nunca pares");
        recurso2.setDisponible(false);
        recurso2.setCategoriaId("1");
        recurso2.setTipoRecursoId("1234");
        recurso2.setFecha(LocalDate.now());

        Mockito.when(repositorioRecurso.save(any())).thenReturn(recurso1);

        var resultado = servicioRecurso.crear(recurso2);
        Assertions.assertEquals(recurso1.getTipoRecursoId(), resultado.getTipoRecursoId());
        Assertions.assertEquals(recurso1.getNombreRecurso(), resultado.getNombreRecurso());

        Mockito.verify(repositorioRecurso).save(any());
    }

    @Test
    @DisplayName("Test findAll Success")
    public void obtenerRecursos(){
        var recurso1 = new Recurso();
        recurso1.setId("1234");
        recurso1.setNombreRecurso("El poder de los habitos");
        recurso1.setDisponible(true);
        recurso1.setTipoRecursoId("1234");
        recurso1.setCategoriaId("1");
        recurso1.setFecha(LocalDate.now());

        var recurso2 = new Recurso();
        recurso2.setId("12345");
        recurso2.setNombreRecurso("Nunca pares");
        recurso2.setDisponible(false);
        recurso2.setCategoriaId("1");
        recurso2.setTipoRecursoId("1234");
        recurso2.setFecha(LocalDate.now());

        var lista = new ArrayList<Recurso>();
        lista.add(recurso1);
        lista.add(recurso2);
        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);

        var resultado = servicioRecurso.listarRecursos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(recurso1.getId(), resultado.get(0).getId());
        Assertions.assertEquals(recurso2.getId(), resultado.get(1).getId());

        Mockito.verify(repositorioRecurso).findAll();
    }

    @Test
    @DisplayName("Test findById Success")
    public void buscarRecurso(){

        var recurso2 = new Recurso();
        recurso2.setId("12345");
        recurso2.setNombreRecurso("Nunca pares");
        recurso2.setDisponible(false);
        recurso2.setCategoriaId("1");
        recurso2.setTipoRecursoId("1234");
        recurso2.setFecha(LocalDate.now());


        Mockito.when(repositorioRecurso.findById(any())).thenReturn(java.util.Optional.of(recurso2));
        var resultado = servicioBiblioteca.consultarRecurso("12345");
        Assertions.assertEquals(recurso2.getDisponible(), resultado.isDisponible());

        Mockito.verify(repositorioRecurso).findById(any());
    }

}