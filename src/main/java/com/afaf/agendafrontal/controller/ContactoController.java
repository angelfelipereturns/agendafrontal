package com.afaf.agendafrontal.controller;

import java.util.List;

import javax.validation.Valid;

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

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<Contacto> contactos = contactoRestClient.consultaTodos();
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
		contactoRestClient.guardaContacto(contacto);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView opcionContacto(@RequestParam Long id, @RequestParam String opcion) {
		ModelAndView modelAndView = new ModelAndView();
		if("modificar".equals(opcion)) {
			Contacto contacto = contactoRestClient.consultaPorId(id);
			modelAndView.addObject("contacto", contacto);
			modelAndView.setViewName("modificacontacto");
		}
		else if("borrar".equals(opcion)) {
			contactoRestClient.borraContacto(id);
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/modificacontacto", method=RequestMethod.POST)
	public ModelAndView modificaContacto(@Valid Contacto contacto, @RequestParam Long id) {
		ModelAndView modelAndView = new ModelAndView();
		contacto.setId(id);
		contactoRestClient.modificaContacto(contacto, id);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
}
