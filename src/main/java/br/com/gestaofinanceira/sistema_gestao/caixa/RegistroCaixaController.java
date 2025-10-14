package br.com.gestaofinanceira.sistema_gestao.caixa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Anotação que combina @Controller e @ResponseBody. Diz ao Spring que esta classe irá lidar com requisições web e retornar dados (JSON).
@RequestMapping("/caixa") // Define que todos os endpoints nesta classe começarão com "/caixa". Ex: http://localhost:8080/caixa
public class RegistroCaixaController {
    @Autowired // O Spring injeta a instância do nosso Service. O Controller DELEGA a lógica para o Service.
    private RegistroCaixaService registroCaixaService;

    // Endpoint para CRIAR um novo registro
    // Mapeia requisições HTTP POST para /caixa
    @PostMapping("/")
    public ResponseEntity<RegistroCaixa> criar(@RequestBody RegistroCaixa novoRegistro) {
        var registroSalvo = this.registroCaixaService.criarRegistro(novoRegistro);
        return ResponseEntity.ok().body(registroSalvo);
    }

    // Endpoint para LER todos os registros
    // Mapeia requisições HTTP GET para /caixa
    @GetMapping("/")
    public ResponseEntity<List<RegistroCaixa>> listar() {
        var listaDeRegistros = this.registroCaixaService.listarTodos();
        return ResponseEntity.ok().body(listaDeRegistros);
    }

    // Endpoint para EDITAR um registro
    // Mapeia requisições HTTP PUT para /caixa/{id}
    @PutMapping("/{id}")
    public ResponseEntity<RegistroCaixa> editar(@PathVariable Long id, @RequestBody RegistroCaixa dadosParaAtualizar) {
        var registroAtualizado = this.registroCaixaService.editarRegistro(id, dadosParaAtualizar);
        return ResponseEntity.ok().body(registroAtualizado);
    }

    // Endpoint para EXCLUIR um registro
    // Mapeia requisições HTTP DELETE para /caixa/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        this.registroCaixaService.excluirRegistro(id);
        return ResponseEntity.ok().build(); // Retorna uma resposta de sucesso sem corpo.
    }

}
