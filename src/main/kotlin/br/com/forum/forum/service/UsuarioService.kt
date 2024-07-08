package br.com.forum.forum.service

import br.com.forum.forum.model.Usuario
import br.com.forum.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
data class UsuarioService (
    private val repository: UsuarioRepository
) {

    fun buscarPorId(id: Long): Usuario  {
        return repository.getReferenceById(id)
    }

}

