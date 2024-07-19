package com.example.sem8.controlador;

import com.example.sem8.entidad.Curso;
import com.example.sem8.servicio.CursoService;
import com.example.sem8.servicio.DocenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/curso")
public class CursoController {
    @Autowired private CursoService cursoService;
    @Autowired private DocenteService docenteService;

    @GetMapping({"/index",""})
    public String Indice(Model model){
        model.addAttribute("listaCursos", cursoService.listarTodos());
        return "curso/cursoIndex";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("curso",new Curso());
        model.addAttribute("listaDocentes", docenteService.listarTodos());
        return "curso/cursoForm";
    }
    @PostMapping("/guardar")
    public String procesarFormularioNuevo(Model model,
                                          @Valid @ModelAttribute("curso") Curso curso, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaDocentes", docenteService.listarTodos());
            return "curso/cursoForm";
        }
        if (curso.getId() == null)
            cursoService.agregar(curso);
        else
            cursoService.actualizar(curso);

        return "redirect:/curso/index";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable("id") Long id){
        Curso buscado = cursoService.buscar(id);
        model.addAttribute("curso", buscado != null ? buscado : new Curso());
        model.addAttribute("listaDocentes", docenteService.listarTodos());
        return "curso/cursoForm";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(Model model, @PathVariable("id") Long id){
        cursoService.eliminar(id);
        return "redirect:/curso/index";
    }
}
