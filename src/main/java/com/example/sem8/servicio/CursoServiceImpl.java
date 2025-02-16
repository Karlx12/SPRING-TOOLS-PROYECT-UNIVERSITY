package com.example.sem8.servicio;

import com.example.sem8.entidad.Curso;
import com.example.sem8.repositorio.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public Curso agregar(Curso entidad) {
        return cursoRepository.save(entidad);
    }
    @Override
    public List<Curso> listarTodos(){
        return cursoRepository.findAll();
    }
    @Override
    public Curso buscar(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }
    @Override
    public Curso actualizar(Curso entidad){
        return cursoRepository.save(entidad);
    }
    @Override
    public void eliminar(Long id){
        cursoRepository.deleteById(id);
    }

}

