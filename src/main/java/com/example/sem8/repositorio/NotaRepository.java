package com.example.sem8.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sem8.entidad.Nota;
public interface NotaRepository extends JpaRepository<Nota, Long> {
}