package br.com.project.forum.service

import br.com.project.forum.dto.NewTopicoForm
import br.com.project.forum.dto.TopicoPorCategoriaDto
import br.com.project.forum.dto.TopicoView
import br.com.project.forum.dto.UpdateTopicoForm
import br.com.project.forum.exception.NotFoundException
import br.com.project.forum.mapper.TopicoFormMapper
import br.com.project.forum.mapper.TopicoMapper
import br.com.project.forum.model.Topico
import br.com.project.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoFormMapper: TopicoFormMapper,
    private val topicoMapper: TopicoMapper,
    private val notFoundMessage: String = "Topico nao encontrado!"
    ) {

    @Cacheable(cacheNames = ["topicos"], key = "#root.method.name")
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
        val topico = repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
        return topicoMapper.toDomainTopicoView(topico)
    }

    @CacheEvict(cacheNames  = ["topicos"], allEntries = true)
    fun cadastrar(form: NewTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoMapper.toDomainTopicoView(topico)
    }

    @CacheEvict(cacheNames  = ["topicos"], allEntries = true)
    fun atualizar(form: UpdateTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow{ NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        topico.dataAlteracao = LocalDate.now()
        return topicoMapper.toDomainTopicoView(topico)
    }

    @CacheEvict(cacheNames  = ["topicos"], allEntries = true)
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