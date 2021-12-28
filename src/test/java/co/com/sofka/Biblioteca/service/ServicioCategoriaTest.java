package co.com.sofka.Biblioteca.service;

import co.com.sofka.Biblioteca.dtos.CategoriaDTO;
import co.com.sofka.Biblioteca.model.Categoria;
import co.com.sofka.Biblioteca.repositorios.RepositorioCategoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioCategoriaTest {

    @Autowired
    private ServicioCategoria servicioCategoria;

    @Autowired
    private ServicioBiblioteca servicioBiblioteca;

    @MockBean
    private RepositorioCategoria repositorioCategoria;

    @Test
    @DisplayName("Test save Success")
    public void crearCategoria(){
        var categoria1 = new Categoria();
        categoria1.setCategoriaId("1");
        categoria1.setNombreCategoria("CIENCIAS");

        var categoria2 = new CategoriaDTO();
        categoria2.setCategoriaId("2");
        categoria2.setNombreCategoria("HISTORIA");

        Mockito.when(repositorioCategoria.save(any())).thenReturn(categoria1);

        var resultado = servicioCategoria.crearCategoria(categoria2);
        Assertions.assertEquals(categoria1.getCategoriaId(),resultado.getCategoriaId());
        Assertions.assertEquals(categoria1.getNombreCategoria(),resultado.getNombreCategoria());
        Mockito.verify(repositorioCategoria).save(any());
    }


    @Test
    @DisplayName("Test findById Success")
    public void buscarCategoria(){

        var categoria = new Categoria();
        categoria.setCategoriaId("1");
        categoria.setNombreCategoria("CIENCIAS 2");

        Mockito.when(repositorioCategoria.findById(any())).thenReturn(java.util.Optional.of(categoria));
        var resultado = servicioBiblioteca.consultarPorCategoria("1");
        Assertions.assertEquals(categoria.getNombreCategoria(), resultado.getCategoria());
        Mockito.verify(repositorioCategoria).findById(any());
    }
}