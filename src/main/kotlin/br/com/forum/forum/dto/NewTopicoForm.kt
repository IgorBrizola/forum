package br.com.forum.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewTopicoForm(
    @field:NotEmpty(message = "Titulo deve estar em branco!!!")
    @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 caracteres!!!")
    val titulo:String,
    @field:NotEmpty(message = "Titulo deve estar em branco!!!" )
    val mensagem:String,
    @field:NotNull val idCurso: Long,
    @field:NotNull val idAutor: Long
)