package br.com.project.forum.repository

import br.com.project.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {

     fun findByEmail(username: String?): Usuario?

}