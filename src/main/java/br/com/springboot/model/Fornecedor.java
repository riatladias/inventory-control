package br.com.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "fornecedores")
@Data
@AllArgsConstructor
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_fantasia", nullable = false, length = 50)
    @NotBlank(message = "Informe o nome fantasia")
    @Size(min = 3, max = 50)
    private String nomeFantasia;
    @Column(name = "razao_social", nullable = false, length = 50)
    @NotBlank(message = "Informe a razão social")
    @Size(min = 3, max = 50)
    private String razaoSocial;
    @Column(length = 18)
    @CNPJ(message = "CNPJ inválido")
    private String CNPJ;
    private String telefone;
    private String celular;
    @Column(length = 50)
    @Email(message = "E-mail inválido")
    private String email;
    private boolean ativo;

    public Fornecedor() {
        this.ativo = true;
    }
}
