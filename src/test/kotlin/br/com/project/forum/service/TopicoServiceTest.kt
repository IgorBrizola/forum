package br.com.project.forum.service

import br.com.project.forum.exception.NotFoundException
import br.com.project.forum.mapper.TopicoFormMapper
import br.com.project.forum.mapper.TopicoMapper
import br.com.project.forum.model.Topico
import br.com.project.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import br.com.project.forum.model.TopicoTest
import br.com.project.forum.model.TopicoViewTest
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.Optional

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))
    val topico = TopicoTest.build()

     val topicoView = TopicoViewTest.build()

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos

    }
    val topicoMapper: TopicoMapper = mockk() {
        every { toDomainTopicoView(any()) } returns TopicoViewTest.build()

    }
    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository, topicoFormMapper, topicoMapper
    )

    @Test
    fun `deve listar topicos a partir de nome do curso`(){
        every { topicoMapper.toDomainTopicoView(any()) } returns TopicoViewTest.build()
        topicoService.listar("Kotlin", paginacao)
        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoMapper.toDomainTopicoView(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos os topicos quando nome do curso for nulo`() {
        val slot = slot<Topico>()
        every { topicoMapper.toDomainTopicoView(capture(slot)) } returns topicoView

        topicoService.listar(null, paginacao)

        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoMapper.toDomainTopicoView(any()) }

        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
        assertThat(slot.captured.status).isEqualTo(topico.status)
    }

    @Test
    fun `deve listar not found exception quando topico nao for achado`(){
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }

        assertThat(atual.message).isEqualTo("Topico nao encontrado!")
    }


}