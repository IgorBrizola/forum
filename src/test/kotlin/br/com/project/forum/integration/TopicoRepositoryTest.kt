package br.com.project.forum.integration

import br.com.project.forum.dto.TopicoPorCategoriaDto
import br.com.project.forum.model.TopicoTest
import br.com.project.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

 @Autowired
 private lateinit var topicoRepository: TopicoRepository

 private val topico  = TopicoTest.build()


 companion object{
  @Container
  private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.3.0").apply {
   withDatabaseName("testeForumdb")
   withUsername("igor")
   withPassword("123456")
  }

  @JvmStatic
  @DynamicPropertySource
  fun properties(registry: DynamicPropertyRegistry) {
     registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
     registry.add("spring.datasource.password", mysqlContainer::getPassword)
     registry.add("spring.datasource.username", mysqlContainer::getUsername)
  }
 }

  @Test
  fun `deve gerar um relatorio`(){
     topicoRepository.save(topico)
     val relatorio = topicoRepository.relatorio()

   assertThat(relatorio).isNotNull
   assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)
  }

 @Test
 fun `deve litar topico por nome do curso`(){
  topicoRepository.save(topico)
  val topico = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))
  assertThat(topico).isNotNull
 }

}