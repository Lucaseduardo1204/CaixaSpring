package br.com.gestaofinanceira.sistema_gestao.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Usando 'record' do Java 17+ para uma classe de dados concisa
public record UsuarioCreateDTO(
        @NotBlank(message = "O nome de usuário é obrigatório")
        String usuario,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull(message = "A permissão é obrigatória")
        Permissao permissao,

        @NotNull(message = "O status é obrigatório")
        StatusUsuario status
) {}