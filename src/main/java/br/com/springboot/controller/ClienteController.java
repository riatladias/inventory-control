package br.com.springboot.controller;

import br.com.springboot.bo.ClienteBO;
import br.com.springboot.model.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteBO clienteBO;

    // manda o html com o obj cliente
    @GetMapping("/novo")
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("cliente", new Cliente());
        return new ModelAndView("/cliente/formulario", model);
    }

    // salvar atualizar cadastrar
    @PostMapping
    public String salva(@Valid @ModelAttribute Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) return "cliente/formulario";
        if (cliente.getId() != null) {
            clienteBO.atualiza(cliente);
            attr.addFlashAttribute("feedback", "Cliente foi atualizado com sucesso!");
        } else {
            clienteBO.insere(cliente);
            attr.addFlashAttribute("feedback", "Cliente foi cadastrado com sucesso!");
        }
        return "redirect:/clientes";
    }

    // listagem de clientes
    @GetMapping
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("clientes", clienteBO.list());
        return new ModelAndView("/cliente/lista", model);
    }

    // editar cliente
    @GetMapping("/edita/{id}")
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
        model.addAttribute("cliente", clienteBO.pesquisaPeloId(id));
        return new ModelAndView("/cliente/formulario", model);
    }

    // Inativar cliente
    @GetMapping("/inativa/{id}")
    public String inativa(@PathVariable Long id, ModelMap model, RedirectAttributes attr) {
        Cliente cliente = clienteBO.pesquisaPeloId(id);
        clienteBO.inativa(cliente);
        attr.addFlashAttribute("feedback", "Cliente foi inativado com sucesso!");
        return "redirect:/clientes";
    }

    // Ativar cliente
    @GetMapping("/ativa/{id}")
    public String ativa(@PathVariable Long id, ModelMap model, RedirectAttributes attr) {
        Cliente cliente = clienteBO.pesquisaPeloId(id);
        clienteBO.ativa(cliente);
        attr.addFlashAttribute("feedback", "Cliente foi ativado com sucesso!");
        return "redirect:/clientes";
    }
}
