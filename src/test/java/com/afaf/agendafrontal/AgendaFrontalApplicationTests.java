package com.afaf.agendafrontal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.afaf.agendafrontal.controller.ContactoController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgendaFrontalApplicationTests {
	
	@Autowired
	private ContactoController contactoController;

	@Test
	public void contextLoads() {
		assertThat(contactoController).isNotNull();
	}

}
