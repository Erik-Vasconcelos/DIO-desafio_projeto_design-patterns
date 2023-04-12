package one.digitalinnovation.gof.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.repository.EnderecoRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired // usando o singleton
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ViaCepService viaCepService;

	@Override
	public List<Cliente> obterClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente obterCliente(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Nehum cliente encontrado com o id informado!"));
	}

	@Override
	public Cliente inserir(Cliente cliente) {
		return salvarClienteComEndereco(cliente);
	}

	private Cliente salvarClienteComEndereco(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();

		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.regatarEndereco(cep);

			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});

		cliente.setEndereco(endereco);
		return clienteRepository.save(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clieteOptional = clienteRepository.findById(id);
		clieteOptional.ifPresent(c -> salvarClienteComEndereco(cliente));
	}

	@Override
	public void removerCliente(Long id) {
		clienteRepository.deleteById(id);
	}

}
