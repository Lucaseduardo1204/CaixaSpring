package br.com.gestaofinanceira.sistema_gestao.caixa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Anotação que marca esta classe como um componente de Serviço do Spring.
public class RegistroCaixaService {

    @Autowired // Anotação para Injeção de Dependência: O Spring vai criar e gerenciar o repositório para nós.
    private RegistroCaixaRepository registroCaixaRepository;

    //Ação: Criar novo registro
    public RegistroCaixa criarRegistro(RegistroCaixa registro){
        //Regras de negócio: Atribuir a data e hora atuais no momento da criação.
        registro.setData(LocalDate.now());
        registro.setHora(LocalTime.now());

        // Poderíamos adicionar outras regras aqui (ex: validar se o valor é > 0)
        return this.registroCaixaRepository.save(registro);
    }

    // Ação: LER todos os registros
    public List<RegistroCaixa> listarTodos() {
        return this.registroCaixaRepository.findAll();
    }

    // Ação: ATUALIZAR um registro existente
    public RegistroCaixa editarRegistro(Long id, RegistroCaixa dadosParaAtualizar) {
        // 1. Busca o registro no banco de dados para garantir que ele existe.
        return this.registroCaixaRepository.findById(id)
                .map(registroExistente -> {
                    // 2. Atualiza os campos do registro existente com os novos dados.
                    registroExistente.setTipo(dadosParaAtualizar.getTipo());
                    registroExistente.setDescricao(dadosParaAtualizar.getDescricao());
                    registroExistente.setValor(dadosParaAtualizar.getValor());
                    // Não atualizamos data e hora, pois geralmente são do momento da criação.

                    // 3. Salva o registro atualizado de volta no banco.
                    return this.registroCaixaRepository.save(registroExistente);
                }).orElseThrow(() -> new RuntimeException("Registro não encontrado com o id: " + id)); // Lança um erro se o ID não existir.
    }

    // Ação: EXCLUIR um registro
    public void excluirRegistro(Long id) {
        // 1. Verifica se o registro existe antes de tentar deletar.
        if (!this.registroCaixaRepository.existsById(id)) {
            throw new RuntimeException("Registro não encontrado com o id: " + id);
        }
        // 2. Deleta o registro.
        this.registroCaixaRepository.deleteById(id);
    }

}
