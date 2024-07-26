package br.com.forum.forum.service

import br.com.forum.forum.dto.NewTopicoForm
import br.com.forum.forum.dto.TopicoPorCategoriaDto
import br.com.forum.forum.dto.TopicoView
import br.com.forum.forum.dto.UpdateTopicoForm
import br.com.forum.forum.exception.NotFoundException
import br.com.forum.forum.mapper.TopicoFormMapper
import br.com.forum.forum.mapper.TopicoMapper
import br.com.forum.forum.model.Topico
import br.com.forum.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoFormMapper: TopicoFormMapper,
    private val topicoMapper: TopicoMapper,
    private val notFoundMessage: String = "Topico nao encontrado!"
    ) {


    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
             repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { t ->
            topicoMapper.toDomainTopicoView(t)}
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return topicoMapper.toDomainTopicoView(topico)
    }

    fun cadastrar(form: NewTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoMapper.toDomainTopicoView(topico)
    }

    fun atualizar(form: UpdateTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoMapper.toDomainTopicoView(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }

    fun notResponse(): List<Topico> {
        return repository.topicosNaoRespondidos()
    }

}