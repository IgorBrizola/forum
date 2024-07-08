package br.com.forum.forum.service

import br.com.forum.forum.model.Curso
import br.com.forum.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService (
    private val repository: CursoRepository
) {
    fun buscarPorId(id: Long): Curso {
      return repository.getReferenceById(id)
    }
}
