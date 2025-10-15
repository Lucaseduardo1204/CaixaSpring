package br.com.gestaofinanceira.sistema_gestao.usuario; // <-- Pacote correto

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para o Spring Security encontrar o usuário pelo email na hora do login
    Optional<Usuario> findByEmail(String email);

}