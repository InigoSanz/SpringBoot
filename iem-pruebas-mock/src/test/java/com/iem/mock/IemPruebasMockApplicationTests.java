package com.iem.mock;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.iem.mock.controller.ControladorRest;
import com.iem.mock.dto.PenaCarcelesRequest;
import com.iem.mock.dto.PenaCarcelesResponse;
import com.iem.mock.repository.CarcelRepository;
import com.iem.mock.repository.PenaRepository;

@SpringBootTest

class ClasesIemMocksApplicationTests {
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
        
        entrada.setEdad(1);
        entrada.setEsMujer(true);
        entrada.setPenas(List.of(PENA_ID));
        
        ResponseEntity<PenaCarcelesResponse> response = controlador.calcularPenaCarceles(entrada);
        
        Assertions.assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(400)));
    }
    
    @Test
    void testSinCarceles() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaNoViolentaMujerAdulta() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaNoViolentaMujerMenor() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaNoViolentaHombreAdulto() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaNoViolentaHombreMenor() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaViolentaMujerAdulta() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaViolentaMujerMenor() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaViolentaHombreAdulto() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaViolentaHombreMenor() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaSangreMujerAdulta() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaSangreMujerMenor() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaSangreHombreAdulto() {
        // Implementación pendiente
    }
    
    @Test
    void testPenaSangreHombreMenor() {
        // Implementación pendiente
    }
    
    @Test
    void testSumaPenas() {
        // Implementación pendiente
    }
}
