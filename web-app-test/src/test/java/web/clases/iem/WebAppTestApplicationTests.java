package web.clases.iem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {WebAppTestApplication.class, AnnotationConfigContextLoader.class})
@ComponentScan(basePackages ="web.clases.iem")
class WebAppTestApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void obtenerVehiculosTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/coches")
				.accept(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andDo(MockMvcResultHandlers.print());
	}

}