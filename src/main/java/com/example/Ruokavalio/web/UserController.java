package com.example.Ruokavalio.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Ruokavalio.domain.Rekisteröinti;
import com.example.Ruokavalio.domain.User;
import com.example.Ruokavalio.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repository;

	// Rekisteröinti sivu

	@RequestMapping(value = "rekisterointi")
	public String addUser(Model model) {
		model.addAttribute("rekisteroi", new Rekisteröinti());
		return "rekisterointi";
	}

	// Uuden käyttäjän rekisteröinti ja validointi
	@RequestMapping(value = "tallennakayttaja", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("rekisteroi") Rekisteröinti rekisteröinti, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (rekisteröinti.getUsername().equals(rekisteröinti.getPasswordCheck())) {
				String salasana = rekisteröinti.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashSalasana = bc.encode(salasana);

				User newUser = new User();
				newUser.setPasswordHash(hashSalasana);
				newUser.setUsername(rekisteröinti.getUsername());
				newUser.setRole("USER");
				if (repository.findByUsername(rekisteröinti.getUsername()) == null) {
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "rekisterointi";
		}
		return "redirect:/login";
	}

}
