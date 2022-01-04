package br.com.lino.repository

import br.com.lino.model.Todo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface TodoRepository: JpaRepository<Todo, Long> {
}