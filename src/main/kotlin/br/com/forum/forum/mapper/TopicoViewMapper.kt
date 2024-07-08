package br.com.forum.forum.mapper

import br.com.forum.forum.dto.TopicoView
import br.com.forum.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
       return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataDeCriacao = t.dataCriacao,
            status = t.status
        )
    }
}