package br.com.springboot.bo;

import br.com.springboot.model.Cliente;
import br.com.springboot.model.Sexo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteBOTest {
    @Autowired
    private ClienteBO bo;

    @Test
    @Order(1)
    public void insere() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joe Duo");
        cliente.setCpf("11122233312");
        cliente.setDataDeNascimento(LocalDate.of(1995, 1, 7));
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setTelefone("1112223331");
        cliente.setCelular("11122233312");
        cliente.setAtivo(true);
        cliente.setEmail("teste@teste.com");
        bo.insere(cliente);
    }
    @Test
    @Order(2)
    public void pesquisaPeloId() {
        Cliente cliente = bo.pesquisaPeloId(1L);
        System.out.println(cliente);
    }

    @Test
    @Order(3)
    public void atualiza() {
        Cliente cliente = bo.pesquisaPeloId(1L);
        cliente.setCpf("55566677792");
        bo.atualiza(cliente);
    }
}
