package one.digitalinnovation.gof.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.model.Endereco;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws/")
public interface ViaCepService {

	@GetMapping("{cep}/json")
	Endereco regatarEndereco(@PathVariable String cep);
}
