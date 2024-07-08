package br.com.forum.forum.mapper

import br.com.forum.forum.dto.NewTopicoForm
import br.com.forum.forum.model.Topico
import br.com.forum.forum.service.CursoService
import br.com.forum.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) :Mapper<NewTopicoForm, Topico>{

    override fun map(t: NewTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }

}
