package com.chibcha.plus.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chibcha.plus.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>  {
    public Optional<Usuario> findByUsername(String username);
}