package br.com.project.forum.mapper

import br.com.project.forum.dto.NewTopicoForm
import br.com.project.forum.model.Topico
import br.com.project.forum.service.CursoService
import br.com.project.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) : Mapper<NewTopicoForm, Topico> {

    override fun map(t: NewTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }

}
