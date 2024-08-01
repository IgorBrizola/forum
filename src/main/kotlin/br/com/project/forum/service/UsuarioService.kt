package br.com.project.forum.service

import br.com.project.forum.model.Usuario
import br.com.project.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService (
    private val repository: UsuarioRepository
) : UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository.getReferenceById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
       val usuario = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }

}

