package com.example.sem8.controlador;

import com.example.sem8.entidad.Alumno;
import com.example.sem8.servicio.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired private AlumnoService alumnoService;

    @GetMapping({"/index",""})
    public String getIndex(Model model){
        model.addAttribute("listaAlumnos", alumnoService.listarTodos());
        return "alumno/alumnoIndex";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("alumno", new Alumno());
        return "alumno/alumnoForm";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(Model model, @PathVariable("id") Long id){
        Alumno aux= alumnoService.buscar(id);
        model.addAttribute("alumno", aux!=null?aux:new Alumno());
        return "alumno/alumnoForm";
    }
    @PostMapping("/guardar")
    public String ProcesarFormulario(Model model,
                                     @Valid @ModelAttribute("alumno") Alumno alumno, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "alumno/alumnoForm";
        }
        if(alumno.getId()==null){
            alumnoService.agregar(alumno);
        }else{
            alumnoService.actualizar(alumno);
        }
        return "redirect:/alumno/index";
    }

    @GetMapping("/eliminar/{id}")
    public String Alumnoeliminar(Model model, @PathVariable("id") Long id){
        alumnoService.eliminar(id);
        return "redirect:/alumno/index";
    }
}
