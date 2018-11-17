package com.afaf.agendafrontal.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.afaf.agendafrontal.data.Contacto;

@Service
public class ContactoRestClient {
	
	
	public List<Contacto> consultaTodos() {
		List<Contacto> listaContactos = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String url = "http://localhost:8080/agendaproductora/contactos";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Contacto[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Contacto[].class); 
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			listaContactos = new ArrayList<Contacto>();
			for(Contacto contacto : response.getBody()) {
				listaContactos.add(contacto);
			}
		}
		return listaContactos;
	}
	
	public Contacto consultaPorId(Long id) {
		Contacto contacto = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String url = "http://localhost:8080/agendaproductora/contactos/"+id;
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Contacto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Contacto.class); 
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			contacto = response.getBody();
		}
		return contacto;
	}
	
    public int guardaContacto(Contacto contacto) {
		int resultado = 0;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
		String url = "http://localhost:8080/agendaproductora/contactos/nuevo";
		HttpEntity<Contacto> requestEntity = new HttpEntity<Contacto>(contacto, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class); 
		if(response.getStatusCode().equals(HttpStatus.CREATED)) {
			resultado = 1;
		}
        return resultado;
    }
 
    public int modificaContacto(Contacto contacto, Long id) {
		int resultado = 0;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
		String url = "http://localhost:8080/agendaproductora/contactos/modificar/"+id;
		HttpEntity<Contacto> requestEntity = new HttpEntity<Contacto>(contacto, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class); 
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			resultado = 1;
		}
        return resultado;
    }
 
    public int borraContacto(Long id) {
		int resultado = 0;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
		String url = "http://localhost:8080/agendaproductora/contactos/borrar/"+id;
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class); 
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			resultado = 1;
		}
        return resultado;
    }
	
	
}
