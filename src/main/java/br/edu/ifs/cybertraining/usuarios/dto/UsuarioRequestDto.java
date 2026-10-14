package br.edu.ifs.cybertraining.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {

    @NotBlank(message = "O nome deve ser informado.")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres.")
    private String nomeUsuario;

    @NotBlank(message = "O email deve ser informado.")
    @Email(message = "A formatação de email está incorreta.")
    @Size(max = 150, message = "O email não pode ultrapassar 150 caracteres.")
    private String email;

    @NotBlank(message = "A senha deve ser informada.")
    @Size(min = 6, max = 100, message = "A senha deve conter no mínimo 6 caracteres.")
    private String senha;
}
