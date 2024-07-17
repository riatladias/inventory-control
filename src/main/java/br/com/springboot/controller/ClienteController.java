package br.com.springboot.controller;

import br.com.springboot.bo.ClienteBO;
import br.com.springboot.model.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteBO clienteBO;

    // manda o html com o obj cliente
    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("cliente", new Cliente());
        return new ModelAndView("/cliente/formulario", model);
    }

    // salvar atualizar cadastrar
    @RequestMapping(value = "", method = RequestMethod.POST)
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("clientes", clienteBO.list());
        return new ModelAndView("/cliente/lista", model);
    }

    // editar cliente
    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
        model.addAttribute("cliente", clienteBO.pesquisaPeloId(id));
        return new ModelAndView("/cliente/formulario", model);
    }

    // Inativar cliente
    @RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
    public String inativa(@PathVariable Long id, ModelMap model, RedirectAttributes attr) {
        Cliente cliente = clienteBO.pesquisaPeloId(id);
        clienteBO.inativa(cliente);
        attr.addFlashAttribute("feedback", "Cliente foi inativado com sucesso!");
        return "redirect:/clientes";
    }

    // Ativar cliente
    @RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
    public String ativa(@PathVariable Long id, ModelMap model, RedirectAttributes attr) {
        Cliente cliente = clienteBO.pesquisaPeloId(id);
        clienteBO.ativa(cliente);
        attr.addFlashAttribute("feedback", "Cliente foi ativado com sucesso!");
        return "redirect:/clientes";
    }
}
