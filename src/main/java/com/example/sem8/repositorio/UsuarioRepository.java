package com.example.sem8.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sem8.entidad.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}