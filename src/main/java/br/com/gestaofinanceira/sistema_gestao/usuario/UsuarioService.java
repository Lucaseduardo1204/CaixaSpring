package br.com.gestaofinanceira.sistema_gestao.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- IMPORT NECESSÁRIO

import java.util.List; // <-- IMPORT NECESSÁRIO

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- SEU CÓDIGO EXISTENTE (PERFEITO) ---

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenhaHash(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    // --- MÉTODOS NOVOS ADICIONADOS ---

    /**
     * Busca todos os usuários cadastrados. (Do passo das requisições GET)
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca um usuário específico pelo seu ID. (Do passo das requisições GET)
     * Usado também para validar se um usuário existe antes de atualizar ou deletar.
     */
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    /**
     * Atualiza os dados de um usuário existente. (Do passo de PUT/DELETE)
     */
    @Transactional
    public Usuario atualizarUsuario(Long id, UsuarioUpdateDTO dados) {
        var usuario = this.buscarPorId(id); // Reutiliza o método para buscar e já trata o erro se não existir

        if (dados.usuario() != null && !dados.usuario().isBlank()) {
            usuario.setUsuario(dados.usuario());
        }
        if (dados.permissao() != null) {
            usuario.setPermissao(dados.permissao());
        }
        if (dados.status() != null) {
            usuario.setStatus(dados.status());
        }

        // Não precisa de save() por causa do @Transactional
        return usuario;
    }

    /**
     * Deleta um usuário do banco de dados. (Do passo de PUT/DELETE)
     */
    public void deletarUsuario(Long id) {
        this.buscarPorId(id); // Valida se o usuário existe antes de tentar deletar
        usuarioRepository.deleteById(id);
    }
}