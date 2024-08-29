package br.com.project.forum.service

import br.com.project.forum.model.Resposta
import br.com.project.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService
) {

    fun salvar(resposta: Resposta){
        respostaRepository.save(resposta)
        val emailAutor = resposta.autor.email
        emailService.notificar(emailAutor)
    }
}