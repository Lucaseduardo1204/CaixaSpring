package br.com.gestaofinanceira.sistema_gestao.usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus; // Adicione este import

import java.net.URI;

@RestController
@RequestMapping("/usuarios") // Todas as requisições para /usuarios cairão aqui
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping // Mapeia para requisições HTTP POST
    public ResponseEntity<UsuarioResponseDTO> criar(
            @RequestBody @Valid UsuarioCreateDTO dados,
            UriComponentsBuilder uriBuilder) {

        // 1. Mapear DTO para Entidade
        var usuario = new Usuario();
        usuario.setUsuario(dados.usuario());
        usuario.setEmail(dados.email());
        usuario.setSenhaHash(dados.senha()); // Passamos a senha em texto puro para o service
        usuario.setPermissao(dados.permissao());
        usuario.setStatus(dados.status());

        // 2. Chamar o Service para criptografar a senha e salvar
        var novoUsuario = usuarioService.criarUsuario(usuario);

        // 3. Criar o DTO de resposta
        var responseDto = new UsuarioResponseDTO(novoUsuario);

        // 4. Construir a URI do novo recurso criado
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(novoUsuario.getId()).toUri();

        // 5. Retornar status 201 Created com a URI e o corpo da resposta
        return ResponseEntity.created(uri).body(responseDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO dados) {

        var usuarioAtualizado = usuarioService.atualizarUsuario(id, dados);
        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);

        // Retorna status 204 No Content, que é o padrão para delete com sucesso.
        return ResponseEntity.noContent().build();
    }
}
