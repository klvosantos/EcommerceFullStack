package com.marcelosantos.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.marcelosantos.cursomc.domain.Cliente;
import com.marcelosantos.cursomc.dto.ClienteDTO;
import com.marcelosantos.cursomc.repositories.ClienteRepository;
import com.marcelosantos.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request; // Possibilita acesso ao id enviado na uri da requisição
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);// Faz casting de um tipo genérico(object) para o tipo map
		Integer uriId = Integer.parseInt(map.get("id")); // Pega o numero do id enviado na uri
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = repo.findByEmail(objDto.getEmail()); 
		if (aux != null && !aux.getId().equals(uriId)) { 				//Verifica se o e-mail existe e se não é igual ao e-mail enviado na requisição através do id. 
			list.add(new FieldMessage("email", "E-mail já existente")); // Se o id do banco(aux) for diferente do id passado na uri da requisição(uriId), ele adiciona um erro na lista 
		}																// Significa que foi houve a tentativa de atualizar um cliente contendo um e-mail que ja tinha em um outro cliente no banco de dados, nesse caso não sendo possivel deixar o e-mail se repetir
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

