package web.clases.iem;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import web.clases.iem.controller.Controlador;
import web.clases.iem.dto.Coche;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {WebAppTestApplication.class, AnnotationConfigContextLoader.class})
@ComponentScan(basePackages ="web.clases.iem")
class WebAppTestApplicationTests {
	
	@Value("${tarifa}")
	private Float tarifa;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Controlador controlador;
	
	ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
	
	@BeforeEach
	void ejecutarAntesDeCadaTest() {
		controlador.setCoches(new ArrayList<>());
	}
	
	@Test
	void obtenerVehiculosNoContentTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/coches")
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void obtenerVehiculosTest() throws Exception {
		Coche coche = new Coche();
		coche.setId("666");
		coche.setMatricula("1111111");
		controlador.getCoches().add(coche);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/coches")
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content()
				.string(CoreMatchers.containsString("\"id\":\"666\"")))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void entradaCocheTest() throws Exception {
		Coche cocheEntrada = new Coche();
		cocheEntrada.setMatricula("111111");
		cocheEntrada.setFechaEntrada(LocalDateTime.now());
		
		// String jsonRequest = objectMapper.writeValueAsString(cocheEntrada);
		// System.out.println("json: " + jsonRequest);

		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/coches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cocheEntrada))
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
			)
			.andExpect(MockMvcResultMatchers.status().is(201))
			.andDo(MockMvcResultHandlers.print());
		
		Assertions.assertFalse(controlador.getCoches().isEmpty());
		Assertions.assertTrue(controlador.getCoches().get(0).getMatricula().equalsIgnoreCase("111111"));
	}
	
	@Test
	void calcularCobroTest() throws Exception {
		Coche coche = new Coche();
		coche.setId("666");
		coche.setMatricula("111111");
		coche.setFechaEntrada(LocalDateTime.now().minusSeconds(100));
		controlador.getCoches().add(coche);
		
		Float respuesta = 100 * tarifa;
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/coches/666/calculo")
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
			)
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.content()
					.string(CoreMatchers.containsString(respuesta.toString())))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void salidaCocheTest() throws Exception {
		Coche coche = new Coche();
		coche.setId("666");
		coche.setMatricula("111111");
		controlador.getCoches().add(coche);
		
		
		mockMvc.perform(MockMvcRequestBuilders
				.patch("/coches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(coche))
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
			)
			.andExpect(MockMvcResultMatchers.status().is(204))
			.andDo(MockMvcResultHandlers.print());
		
		Assertions.assertFalse(controlador.getCoches().isEmpty());
		Assertions.assertNotNull(controlador.getCoches().get(0).getFechaSalida());
	}

}