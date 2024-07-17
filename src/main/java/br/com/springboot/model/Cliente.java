package br.com.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;
    @CPF(message = "CPF inválido")
    private String cpf;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    @NotNull(message = "Informe a data de nascimento")
    private LocalDate dataDeNascimento;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Informe o sexo")
    private Sexo sexo;
    private String telefone;
    private String celular;
    @Column(length = 50)
    @Email(message = "E-mail inválido")
    private String email;
    private boolean ativo;

    public Cliente() {
        this.ativo = true;
    }
}
