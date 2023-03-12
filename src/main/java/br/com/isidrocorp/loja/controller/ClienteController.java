package br.com.isidrocorp.loja.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.loja.dto.MensagemErro;
import br.com.isidrocorp.loja.model.Cliente;
import br.com.isidrocorp.loja.services.IClienteService;

@RestController
public class ClienteController {

	@Autowired
	private IClienteService service;

	@GetMapping("/clientes")
	public ArrayList<Cliente> recuperarTodos() {
		return service.recuperarTodos();
	}

	@GetMapping("/clientes/{codigo}")
	public ResponseEntity<Cliente> recuperarPeloCodigo(@PathVariable int codigo) {
		Cliente c = service.recuperarPeloCodigo(codigo);
		if (c != null) {
			return ResponseEntity.ok(c);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/clientes/busca")
	public ResponseEntity<?> buscarPorNome(@RequestParam(name = "palavra") String palavra) {
		ArrayList<Cliente> lista = service.recuperarPeloNome(palavra);
		if (lista.size() > 0) {
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.status(404).body(new MensagemErro(9876, "Criterio de busca não foi satisfeito."));
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> adicionarNovo(@RequestBody Cliente c) {
		Cliente res = service.adicionarNovo(c);
		if (res != null) {
			return ResponseEntity.status(201).body(res);
		}
		return ResponseEntity.badRequest().build();

	}

	@PutMapping("/clientes")
	public ResponseEntity<Cliente> atualizarDados(@RequestBody Cliente c) {
		Cliente res = service.atualizarDados(c);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/clientes/{codigo}")
	public ResponseEntity<?> removerCliente(@PathVariable int codigo) {
		if (service.excluirCliente(codigo)) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(404).body(new MensagemErro(1234, "Codigo de cliente inexistente"));
	}

}
