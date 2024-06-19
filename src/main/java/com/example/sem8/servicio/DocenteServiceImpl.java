package com.example.sem8.servicio;

import com.example.sem8.entidad.Docente;
import com.example.sem8.repositorio.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public Docente agregar(Docente entidad) {
        entidad.setCreationTime(new Date());
        entidad.setModificationTime(new Date());
        return docenteRepository.save(entidad);
    }
    @Override
    public List<Docente> listarTodos(){
        return docenteRepository.findAll();
    }
    @Override
    public Docente buscar(Long id) {
        return docenteRepository.findById(id).orElse(null);
    }
    @Override
    public Docente actualizar(Docente entidad){
        entidad.setModificationTime(new Date());
        return docenteRepository.save(entidad);
    }
    @Override
    public void eliminar(Long id){
        docenteRepository.deleteById(id);
    }

}
