package br.com.project.forum.model

import br.com.project.forum.dto.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Kotlin basico",
        mensagem = "Aprendendo kotlin basico",
        status = StatusTopico.NAO_SOLUCIONADO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()
    )
}