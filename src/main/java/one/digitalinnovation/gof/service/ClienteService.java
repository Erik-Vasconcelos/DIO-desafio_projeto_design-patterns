package one.digitalinnovation.gof.service;

import java.util.List;

import one.digitalinnovation.gof.model.Cliente;

//Strategy
public interface ClienteService {
	List<Cliente> obterClientes();
	
	Cliente obterCliente(Long id);
	
	Cliente inserir(Cliente cliente);
	
	void atualizar(Long id, Cliente cliente);
	
	void removerCliente(Long id);
}
