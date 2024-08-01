package br.com.project.forum.model

import br.com.project.forum.model.Curso

object CursoTest {
    fun build() = Curso(
        id = 1,
        nome = "Kotlin Basico",
        categoria = "Programacao"

    )
}