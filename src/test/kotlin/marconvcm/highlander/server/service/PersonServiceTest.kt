package marconvcm.highlander.server.service

import marconvcm.highlander.server.payload.RegisterPayload
import marconvcm.highlander.server.repository.PersonRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class PersonServiceTest {

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var personRepository: PersonRepository

    @Test
    fun itShouldRegisterUser() {

        val payload = RegisterPayload(email = "marconvcm@gmail.com")

        val email = personService.register(payload).let {
            personRepository.findOne(it.id).email
        }

        assert(email == payload.email)
    }

    @Test
    fun itShouldRegisterUserTwice() {

        val payload = RegisterPayload(email = "marconvcm@gmail.com")

        val email = personService.register(payload).let {
            personService.register(payload).let {
                personRepository.findOne(it.id).email
            }
        }

        assert(email == payload.email)
        assert(personRepository.count() == 1L)
    }
}

