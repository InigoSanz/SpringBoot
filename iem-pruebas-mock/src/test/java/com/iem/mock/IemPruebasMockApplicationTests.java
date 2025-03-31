package com.iem.mock;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.iem.mock.controller.ControladorRest;
import com.iem.mock.dto.PenaCarcelesRequest;
import com.iem.mock.dto.PenaCarcelesResponse;
import com.iem.mock.entity.CarcelEntity;
import com.iem.mock.entity.PenaEntity;
import com.iem.mock.entity.TipoPena;
import com.iem.mock.entity.TipoSeguridadCarcel;
import com.iem.mock.repository.CarcelRepository;
import com.iem.mock.repository.PenaRepository;

@SuppressWarnings("unchecked")
class ClasesIemMockApplicationTests {
    private static final Long PENA_ID = 11L;
    private static final Long PENA_2_ID = 22L;
    private static final Long CARCEL_ID = 33L;

    @InjectMocks
    ControladorRest controlador;

    @Mock
    CarcelRepository carcelRepository;

    @Mock
    PenaRepository penaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSinEntrada() {
        PenaCarcelesRequest entrada = null;
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(400)));
    }

    @Test
    void testSinPenas() {
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(20);
        entrada.setEsMujer(true);
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(400)));

        entrada.setPenas(List.of());
        response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(400)));

        entrada.setPenas(List.of(PENA_ID));
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of());
        response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(400)));
    }

    @Test
    void testSinCarceles() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(10);
        penaEntity.setTipo(TipoPena.NO_VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of());
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(30);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204)));
    }

    @Test
    void testPenaNoViolentaMujerAdulta() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(5);
        penaEntity.setTipo(TipoPena.NO_VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(true);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.BAJA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(30);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(5, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaNoViolentaMujerMenor() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(3);
        penaEntity.setTipo(TipoPena.NO_VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(true);
        carcelEntity.setParaMujeres(true);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.BAJA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(16);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(3, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaNoViolentaHombreAdulto() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(7);
        penaEntity.setTipo(TipoPena.NO_VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.BAJA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(25);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(7, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaNoViolentaHombreMenor() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(4);
        penaEntity.setTipo(TipoPena.NO_VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(true);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.BAJA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(17);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(4, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaViolentaMujerAdulta() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(8);
        penaEntity.setTipo(TipoPena.VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(true);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MEDIA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(30);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(8, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaViolentaMujerMenor() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(6);
        penaEntity.setTipo(TipoPena.VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(true);
        carcelEntity.setParaMujeres(true);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MEDIA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(16);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(6, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaViolentaHombreAdulto() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(9);
        penaEntity.setTipo(TipoPena.VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MEDIA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(35);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(9, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaViolentaHombreMenor() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(7);
        penaEntity.setTipo(TipoPena.VIOLENTA);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(true);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MEDIA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(17);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(7, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaSangreMujerAdulta() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(10);
        penaEntity.setTipo(TipoPena.SANGRE);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(true);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MAXIMA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(30);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(10, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaSangreMujerMenor() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(5);
        penaEntity.setTipo(TipoPena.SANGRE);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(true);
        carcelEntity.setParaMujeres(true);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MAXIMA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(16);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(5, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaSangreHombreAdulto() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(12);
        penaEntity.setTipo(TipoPena.SANGRE);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MAXIMA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(40);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(12, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testPenaSangreHombreMenor() {
        PenaEntity penaEntity = new PenaEntity();
        penaEntity.setId(PENA_ID);
        penaEntity.setDias(7);
        penaEntity.setTipo(TipoPena.SANGRE);
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(penaEntity));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(true);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.MAXIMA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(15);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(7, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }

    @Test
    void testSumaPenas() {
        PenaEntity pena1 = new PenaEntity();
        pena1.setId(PENA_ID);
        pena1.setDias(5);
        pena1.setTipo(TipoPena.NO_VIOLENTA);
        
        PenaEntity pena2 = new PenaEntity();
        pena2.setId(PENA_2_ID);
        pena2.setDias(10);
        pena2.setTipo(TipoPena.NO_VIOLENTA);
        
        Mockito.when(penaRepository.findAllById(Mockito.any())).thenReturn(List.of(pena1, pena2));
        
        CarcelEntity carcelEntity = new CarcelEntity();
        carcelEntity.setId(CARCEL_ID);
        carcelEntity.setParaMenores(false);
        carcelEntity.setParaMujeres(false);
        carcelEntity.setTipoSeguridad(TipoSeguridadCarcel.BAJA);
        Mockito.when(carcelRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(carcelEntity));
        
        PenaCarcelesRequest entrada = new PenaCarcelesRequest();
        entrada.setEdad(30);
        entrada.setEsMujer(false);
        entrada.setPenas(List.of(PENA_ID, PENA_2_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(15, response.getBody().getPenaTotal());
        Assertions.assertTrue(response.getBody().getCarceles().contains(CARCEL_ID));
    }
}