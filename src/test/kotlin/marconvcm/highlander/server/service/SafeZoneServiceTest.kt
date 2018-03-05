package marconvcm.highlander.server.service

import marconvcm.highlander.server.entity.Person
import marconvcm.highlander.server.exception.ForbbidenException
import marconvcm.highlander.server.payload.RegisterPayload
import marconvcm.highlander.server.repository.PersonRepository
import marconvcm.highlander.server.repository.SafeZoneRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class SafeZoneServiceTest {

    @Autowired
    lateinit var safeZoneRepository: SafeZoneRepository

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var personService: PersonService

    @Autowired
    lateinit var safeZoneService: SafeZoneService

    lateinit var person1: Person

    lateinit var person2: Person

    @Before
    fun setup() {
        safeZoneRepository.deleteAll()
        personRepository.deleteAll()

        person1 = personService.register(RegisterPayload(email = "marconvcm@gmail.com"))
        person2 = personService.register(RegisterPayload(email = "marcos@htmind.com.br"))
    }

    @Test
    fun itShouldOpenASafeZone(){
        safeZoneService.open(person1.id).let {
            assert(!it.id.isNullOrEmpty())
        }
    }

    @Test(expected = ForbbidenException::class)
    fun itShouldAvoidOpenASafeZone(){
        safeZoneService.open(person1.id).let {
            safeZoneService.open(person2.id).let {
                assert(!it.id.isNullOrEmpty())
            }
        }
    }

    @Test
    fun itShouldAllowAnUserAfterCloseSafeZone() {
        safeZoneService.open(person1.id).let { safeZone ->
            safeZoneService.close(safeZone.id).let {
                safeZoneService.open(person2.id).let {
                    assert(!it.id.isNullOrEmpty())
                }
            }
        }
    }

    @Test
    fun itShouldCleanUpOutdatedSafeZone() {
        val expireIn = 1000L
        safeZoneService.open(person1.id).let { safeZone ->
            Thread.sleep(expireIn + 10).let {
                safeZoneService.cleanup(expireIn)
                safeZoneService.open(person2.id).let {
                    assert(!it.id.isNullOrEmpty())
                }
            }
        }
    }
}