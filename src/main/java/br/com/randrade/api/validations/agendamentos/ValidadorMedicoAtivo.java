package br.com.randrade.api.validations.agendamentos;

import br.com.randrade.api.dtos.consultas.DadosAgendamentoConsulta;
import br.com.randrade.api.infra.exception.ValidacaoException;
import br.com.randrade.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído!");
        }
    }
}
