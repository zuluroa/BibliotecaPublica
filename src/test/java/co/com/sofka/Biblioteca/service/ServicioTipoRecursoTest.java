package co.com.sofka.Biblioteca.service;

import co.com.sofka.Biblioteca.dtos.TipoRecursoDTO;
import co.com.sofka.Biblioteca.model.TipoRecurso;
import co.com.sofka.Biblioteca.repositorios.RepositorioTipoRecurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioTipoRecursoTest {

    @Autowired
    private ServicioTipoRecurso servicioTipoRecurso;

    @Autowired
    private ServicioBiblioteca servicioBiblioteca;

    @MockBean
    private RepositorioTipoRecurso repositorioTipoRecurso;

    @Test
    @DisplayName("Test save Success")
    public void crearTipoRecurso(){
        var tipoRecurso1 = new TipoRecurso();
        tipoRecurso1.setTipoRecursoId("1");
        tipoRecurso1.setNombreTipoRecurso("libros");

        var tipoRecurso2 = new TipoRecursoDTO();
        tipoRecurso2.setTipoRecursoId("2");
        tipoRecurso2.setNombreTipoRecurso("Revistas");

        Mockito.when(repositorioTipoRecurso.save(any())).thenReturn(tipoRecurso1);

        var resultado = servicioTipoRecurso.crearTipoRecurso(tipoRecurso2);
        Assertions.assertEquals(tipoRecurso1.getTipoRecursoId(), resultado.getTipoRecursoId());
        Assertions.assertEquals(tipoRecurso1.getNombreTipoRecurso(), resultado.getNombreTipoRecurso());
        Mockito.verify(repositorioTipoRecurso).save(any());
    }

    @Test
    @DisplayName("Test findById Success")
    public void buscarTipoRecurso(){

        var tipoRecurso2 = new TipoRecurso();
        tipoRecurso2.setTipoRecursoId("2");
        tipoRecurso2.setNombreTipoRecurso("Revistas");

        Mockito.when(repositorioTipoRecurso.findById(any())).thenReturn(java.util.Optional.of(tipoRecurso2));
        var resultado = servicioBiblioteca.consultarPorTipoRecurso("1");
        Assertions.assertEquals(tipoRecurso2.getNombreTipoRecurso(), resultado.getTipoRecurso());
        Mockito.verify(repositorioTipoRecurso).findById(any());
    }

}