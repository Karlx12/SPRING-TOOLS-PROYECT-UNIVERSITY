package com.example.sem8.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sem8.entidad.Docente;
public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
