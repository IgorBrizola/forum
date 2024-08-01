package br.com.project.forum.service

import br.com.project.forum.model.Curso
import br.com.project.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService (
    private val repository: CursoRepository
) {
    fun buscarPorId(id: Long): Curso {
      return repository.getReferenceById(id)
    }
}
