package com.example.sem8.servicio;

import com.example.sem8.entidad.Nota;
import com.example.sem8.entidad.NotaID;
import com.example.sem8.repositorio.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaRepository notaRepository;

    @Override
    public Nota agregar(Nota entidad) {
        return notaRepository.save(entidad);
    }
    @Override
    public List<Nota> listarTodos(){
        return notaRepository.findAll();
    }
    @Override
    public Nota buscar(NotaID id) {
        return notaRepository.findById(id).orElse(null);
    }
    @Override
    public Nota actualizar(Nota entidad){
        return notaRepository.save(entidad);
    }
    @Override
    public void eliminar(NotaID id){
        notaRepository.deleteById(id);
    }

}
