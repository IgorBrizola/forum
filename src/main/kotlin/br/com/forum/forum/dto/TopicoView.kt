package br.com.forum.forum.dto

import br.com.forum.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataDeCriacao: LocalDateTime
)
