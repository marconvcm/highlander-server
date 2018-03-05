package marconvcm.highlander.server.web

import marconvcm.highlander.server.entity.Person
import marconvcm.highlander.server.payload.RegisterPayload
import marconvcm.highlander.server.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class PersonController(
    @Autowired val personService: PersonService) {

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterPayload): Person = personService.register(payload)
}

