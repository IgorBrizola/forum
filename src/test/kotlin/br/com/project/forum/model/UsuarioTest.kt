package br.com.project.forum.model


object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        email = "igorbrizzola@gmail.com",
        nome = "Igor Brizola",
        password = "2312"
    )
}