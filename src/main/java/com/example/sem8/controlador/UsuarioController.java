package com.example.sem8.controlador;

import com.example.sem8.entidad.Usuario;
import com.example.sem8.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;

    @GetMapping("/poblar")
    public String poblar(){
        usuarioService.agregar(new Usuario("Juan","123456","ADMIN",1));
        usuarioService.agregar(new Usuario("Ronald","123456","USUARIO",1));
        usuarioService.agregar(new Usuario("Max","123456","USUARIO",1));
        return "index";
    }
    @GetMapping("/login")
    public String login(Model model){
        return "usuario/paginaLogin";
    }
    @GetMapping("/denegado")
    public String denegado(Authentication authresult, Model model){
        String role= authresult.getAuthorities().toString();
        model.addAttribute("roles",role);
        return "usuario/pagina403";
    }
}
