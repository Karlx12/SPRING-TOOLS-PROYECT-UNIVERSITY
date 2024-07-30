package com.example.sem8.controlador;

import com.example.sem8.entidad.Alumno;
import com.example.sem8.entidad.Docente;
import com.example.sem8.servicio.DocenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docente")
public class DocenteController {
    @Autowired private DocenteService docenteService;

    @GetMapping({"/index",""})
    public String Indice(Model model){
        model.addAttribute("listaDocentes", docenteService.listarTodos());
        return "docente/docenteIndex";
    }
    @GetMapping("/nuevo")
    public String DocenteNuevoForm(Model model){

        model.addAttribute("docente", new Docente());
        return "docente/docenteForm";
    }
    @PostMapping("/guardar")
    public String DocenteNuevoProcesar(@Valid @ModelAttribute("docente") Docente docente,
                                       BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "docente/docenteForm";
        }
        if (docente.getId()==null){
            docenteService.agregar(docente);
        }else
            docenteService.actualizar(docente);
        return "redirect:/docente/index";
    }
    @GetMapping("/editar/{id}")
    public String DocenteEditarForm(Model model, @PathVariable("id") Long id){
        Docente buscado= docenteService.buscar(id);
        model.addAttribute("docente", buscado!=null?buscado:new Docente());
        return "docente/docenteForm";
    }
    @GetMapping("/eliminar/{id}")
    public String DocenteEliminar(Model model, @PathVariable("id") Long id){
        docenteService.eliminar(id);
        return "redirect:/docente/index";
    }

}
