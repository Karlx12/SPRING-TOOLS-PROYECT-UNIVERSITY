package com.example.sem8.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sem8.entidad.Alumno;
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
