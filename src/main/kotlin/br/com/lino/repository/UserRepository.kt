package br.com.lino.repository

import br.com.lino.configuration.security.BCryptPasswordEncoderService
import br.com.lino.model.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
abstract class UserRepository(
    private val bCryptPasswordEncoderService: BCryptPasswordEncoderService
) : JpaRepository<User, Long> {

     fun saveEncoded(entity: User): User {
      return save(entity.copy(password = bCryptPasswordEncoderService.encode(entity.password)))
    }
}