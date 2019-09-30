package br.com.javaSpringBootMysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/listclientes")
	public String listclientes(Model model) {
		Iterable<Cliente> clientes = repository.findAll();
		model.addAttribute("clientes", clientes);
		return "listclientes";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {
		Cliente newCliente = new Cliente(nome, email, telefone);
		repository.save(newCliente);
		Iterable<Cliente> clientes = repository.findAll();
		model.addAttribute("clientes", clientes);
		return "listclientes";
	}
}
