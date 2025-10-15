package br.com.gestaofinanceira.sistema_gestao.usuario;

import java.sql.Timestamp;

// Este DTO garante que NUNCA enviaremos a senha de volta para o cliente
public record UsuarioResponseDTO(
        Long id,
        String usuario,
        String email,
        Permissao permissao,
        StatusUsuario status,
        Timestamp criacao
) {
    // Método construtor para facilitar a conversão da Entidade para o DTO
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getEmail(),
                usuario.getPermissao(),
                usuario.getStatus(),
                usuario.getCriacao()
        );
    }
}