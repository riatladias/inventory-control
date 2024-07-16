package br.com.springboot.controller;

import br.com.springboot.bo.ClienteBO;
import br.com.springboot.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    // salva os dados no banco de dados
    // @ModelAttribute Cliente cliente -> monta um obj do tipo cliente vindo do formulário
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@ModelAttribute Cliente cliente) {
        clienteBO.insere(cliente);
        return "/cliente/formulario";
    }
}
