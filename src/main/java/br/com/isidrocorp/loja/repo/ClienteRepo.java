package br.com.isidrocorp.loja.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.isidrocorp.loja.model.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Integer> {
	
	
	public ArrayList<Cliente> findByOrderByNome();
	public ArrayList<Cliente> findByNomeContaining(String nome);
	
}
