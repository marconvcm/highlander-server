package marconvcm.highlander.server.repository

import marconvcm.highlander.server.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<Person, String>


