package com.example.sem8.servicio;

import com.example.sem8.entidad.Nota;
import com.example.sem8.entidad.NotaID;

import java.util.List;

public interface NotaService extends iGenericoService<Nota, NotaID> {
    public List<Nota> listarPorAlumno(Long idAlumno);
}
