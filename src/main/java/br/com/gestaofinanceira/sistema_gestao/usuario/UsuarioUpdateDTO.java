package br.com.gestaofinanceira.sistema_gestao.usuario;

// DTO para receber dados de atualização de um usuário.
// Todos os campos são opcionais.
public record UsuarioUpdateDTO(
        String usuario,
        Permissao permissao,
        StatusUsuario status
) {}