package com.example.sem8.servicio;

import com.example.sem8.entidad.Usuario;
import com.example.sem8.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario agregar(Usuario entidad) {
        return usuarioRepository.save(entidad);
    }
    @Override
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    @Override
    public Usuario actualizar(Usuario entidad){
        return usuarioRepository.save(entidad);
    }
    @Override
    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

}
