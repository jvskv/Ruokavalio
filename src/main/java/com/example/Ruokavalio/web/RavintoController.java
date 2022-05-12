package com.example.Ruokavalio.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Ruokavalio.domain.Ravinto;
import com.example.Ruokavalio.domain.RavintoRepository;

@Controller
public class RavintoController {

	@Autowired
	private RavintoRepository repository;

	// Kirjaudu sisään
	@RequestMapping(value = "/login")
	public String login() {
		return "login";

	}

	// Etusivu jolla lista ruokavaliosta
	@RequestMapping(value = { "/etusivu" })
	public String etusivu(Model model) {
		model.addAttribute("ravinto", repository.findAll());
		return "etusivu";
	}

	// Lisää ravinto
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/lisaa")
	public String lisaaRavinto(Model model) {
		model.addAttribute("ravinto", new Ravinto());
		return "lisaaravinto";
	}

	// Tallenna ravinto
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/tallenna", method = RequestMethod.POST)
	public String tallenna(Ravinto ravinto) {
		repository.save(ravinto);
		return "redirect:etusivu";
	}

	// Poista ravinto
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/poista/{id}", method = RequestMethod.GET)
	public String poistaRavinto(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../etusivu";
	}

	// Muokkaa ravintoa
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/muokkaa/{id}")
	public String muokkaaRavinto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ravinto", repository.findById(id));
		return "muokkaa";
	}

	// RESTful ruokavaliosta
	@RequestMapping(value = "/RESTravinto", method = RequestMethod.GET)
	public @ResponseBody List<Ravinto> ravintoRest() {
		return (List<Ravinto>) repository.findAll();
	}

	// RESTful yksittäisestä ravinnosta
	@RequestMapping(value = "/RESTravinnot/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Ravinto> findRavintoRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
}
