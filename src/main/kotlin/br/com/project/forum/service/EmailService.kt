package br.com.project.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService (
    private val javaMailSender: JavaMailSender
) {
    fun notificar(emailAutor: String,) {
        val message = SimpleMailMessage()

        message.subject = "[FORUM] Resposta Recebida"
        message.text = "Olá, seu topico foi respondida. Vamos la conferir."
        message.setTo(emailAutor)

        javaMailSender.send(message)
    }
}