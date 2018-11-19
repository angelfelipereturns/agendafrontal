package com.afaf.agendafrontal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.afaf.agendafrontal.data.Contacto;
import com.afaf.agendafrontal.rest.ContactoRestClient;

@Controller
public class ContactoController {
	
	@Autowired
	private ContactoRestClient contactoRestClient;
	
	final static Logger logger = LoggerFactory.getLogger(ContactoController.class);
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<Contacto> contactos = new ArrayList<Contacto>();
		try {
			contactos = contactoRestClient.consultaTodos();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		modelAndView.addObject("contactos", contactos);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value="/nuevocontacto", method=RequestMethod.GET)
	public ModelAndView nuevoContacto() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("nuevocontacto");
		return modelAndView;
	}
	
	@RequestMapping(value="/nuevocontacto", method=RequestMethod.POST)
	public ModelAndView nuevoContacto(@Valid Contacto contacto) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			contactoRestClient.guardaContacto(contacto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView opcionContacto(@RequestParam Long id, @RequestParam String opcion) {
		ModelAndView modelAndView = new ModelAndView();
		if("modificar".equals(opcion)) {
			Contacto contacto = new Contacto();
			try {
				contacto = contactoRestClient.consultaPorId(id);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			modelAndView.addObject("contacto", contacto);
			modelAndView.setViewName("modificacontacto");
		}
		else if("borrar".equals(opcion)) {
			try {
				contactoRestClient.borraContacto(id);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/modificacontacto", method=RequestMethod.POST)
	public ModelAndView modificaContacto(@Valid Contacto contacto, @RequestParam Long id) {
		ModelAndView modelAndView = new ModelAndView();
		contacto.setId(id);
		try {
			contactoRestClient.modificaContacto(contacto, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
}
