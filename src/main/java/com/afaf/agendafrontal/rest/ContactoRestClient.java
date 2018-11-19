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
	
	
	public List<Contacto> consultaTodos() throws Exception {
		try {
			List<Contacto> listaContactos = new ArrayList<Contacto>();
			final RestTemplate restTemplate = new RestTemplate();
			final HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			final String url = "http://localhost:8080/agendaproductora/contactos";
			final HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			final ResponseEntity<Contacto[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Contacto[].class); 
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				listaContactos = new ArrayList<Contacto>();
				for(Contacto contacto : response.getBody()) {
					listaContactos.add(contacto);
				}
			}
			return listaContactos;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public Contacto consultaPorId(Long id) throws Exception {
		try {
			Contacto contacto = new Contacto();
			final RestTemplate restTemplate = new RestTemplate();
			final HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			final String url = "http://localhost:8080/agendaproductora/contactos/"+id;
			final HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			final ResponseEntity<Contacto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Contacto.class); 
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				contacto = response.getBody();
			}
			return contacto;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
    public int guardaContacto(final Contacto contacto) throws Exception {
    	try {
    		int resultado = 0;
    		final RestTemplate restTemplate = new RestTemplate();
    		final HttpHeaders headers = new HttpHeaders();
    		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
    		final String url = "http://localhost:8080/agendaproductora/contactos/nuevo";
    		final HttpEntity<Contacto> requestEntity = new HttpEntity<Contacto>(contacto, headers);
    		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class); 
    		if(response.getStatusCode().equals(HttpStatus.CREATED)) {
    			resultado = 1;
    		}
            return resultado;
		} catch (Exception e) {
			throw new Exception(e);
		}
    }
 
    public int modificaContacto(final Contacto contacto, final Long id) throws Exception {
    	try {
    		int resultado = 0;
    		final RestTemplate restTemplate = new RestTemplate();
    		final HttpHeaders headers = new HttpHeaders();
    		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
    		final String url = "http://localhost:8080/agendaproductora/contactos/modificar/"+id;
    		final HttpEntity<Contacto> requestEntity = new HttpEntity<Contacto>(contacto, headers);
    		final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class); 
    		if(response.getStatusCode().equals(HttpStatus.OK)) {
    			resultado = 1;
    		}
            return resultado;
		} catch (Exception e) {
			throw new Exception(e);
		}
    }
 
    public int borraContacto(final Long id) throws Exception {
    	try {
    		int resultado = 0;
    		final RestTemplate restTemplate = new RestTemplate();
    		final HttpHeaders headers = new HttpHeaders();
    		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
    		final String url = "http://localhost:8080/agendaproductora/contactos/borrar/"+id;
    		final HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
    		final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class); 
    		if(response.getStatusCode().equals(HttpStatus.OK)) {
    			resultado = 1;
    		}
            return resultado;
		} catch (Exception e) {
			throw new Exception(e);
		}
    }
	
	
}
