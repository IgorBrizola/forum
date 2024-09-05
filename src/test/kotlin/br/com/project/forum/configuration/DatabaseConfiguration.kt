package br.com.project.forum.configuration

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class DatabaseContainerConfiguration {

    companion object {

        @Container
        private val mySQLContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testeDB")
            withUsername("teste")
            withPassword("123456")
            withReuse(true)
        }

        @Container
            private val rediscontainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)

            registry.add("spring.redis.host", rediscontainer::getHost)
            registry.add("spring.redis.port", rediscontainer::getFirstMappedPort)
        }
    }
}