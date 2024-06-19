package com.example.sem8.servicio;

import com.example.sem8.entidad.Alumno;
import com.example.sem8.repositorio.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno agregar(Alumno entidad) {
        return alumnoRepository.save(entidad);
    }
    @Override
    public List<Alumno> listarTodos(){
        return alumnoRepository.findAll();
    }
    @Override
    public Alumno buscar(Long id) {
         Alumno encontrado=null;
         Optional<Alumno> buscado=alumnoRepository.findById(id);
         if (buscado.isPresent()){
             encontrado =buscado.get();

         }
         return encontrado;
    }
    @Override
    public Alumno actualizar(Alumno entidad){
        return alumnoRepository.save(entidad);
    }
    @Override
    public void eliminar(Long id){
        alumnoRepository.deleteById(id);
    }

}
