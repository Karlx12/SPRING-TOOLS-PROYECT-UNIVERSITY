package com.example.sem8.controlador;

import com.example.sem8.entidad.Alumno;
import com.example.sem8.entidad.Curso;
import com.example.sem8.entidad.Nota;
import com.example.sem8.entidad.NotaID;
import com.example.sem8.servicio.AlumnoService;
import com.example.sem8.servicio.CursoService;
import com.example.sem8.servicio.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/nota")
public class NotaController {
    @Autowired private NotaService notaService;
    @Autowired private AlumnoService alumnoService;
    @Autowired private CursoService cursoService;

    @GetMapping("/matricular/{idAlumno}")
    public String getMatricularForm(Model model, @PathVariable("idAlumno") Long idAlumno){
        Alumno alumno=alumnoService.buscar(idAlumno);
        List<Long> idCursosActuales = notaService.listarPorAlumno(idAlumno).stream()
                .map(nota -> nota.getCurso().getId())
                .toList();
        model.addAttribute("alumno", alumno!=null?alumno:new Alumno());
        model.addAttribute("listaCursos", cursoService.listarTodos());
        model.addAttribute("nota", new Nota());
        return "nota/notaForm";
    }
    @PostMapping("/matricular/guardar")
    public String postMatricularForm(@RequestParam Long id, @RequestParam(required=false) List<Long> idsSeleccionados){
        Stream<Nota> notasActuales1 = notaService.listarPorAlumno(id).stream();
        Stream<Nota> notasActuales2 = notaService.listarPorAlumno(id).stream();
        notasActuales1.filter(nota -> idsSeleccionados == null || !idsSeleccionados.contains(nota.getCurso().getId()))
                .forEach(nota->notaService.eliminar(new NotaID(id,nota.getCurso().getId())));
        List<Long> idsCursosActuales = notasActuales2.map(nota -> nota.getCurso().getId()).toList();
        if (idsSeleccionados != null)
            idsSeleccionados.stream()
                    .filter(idCurso -> !idsCursosActuales.contains(idCurso))
                    .forEach(idCurso -> {
                        Nota notaAgregar = new Nota();
                        Alumno alumno = new Alumno();
                        Curso curso = new Curso();
                        alumno.setId(id);
                        curso.setId(idCurso);

                        notaAgregar.setAlumno(alumno);
                        notaAgregar.setCurso(curso);
                        notaService.agregar(notaAgregar);

                    });
        return "redirect:/alumno/index";
    }
}
