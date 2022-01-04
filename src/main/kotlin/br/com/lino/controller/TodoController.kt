package br.com.lino.controller

import br.com.lino.model.Todo
import br.com.lino.repository.TodoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/todos")
class TodoController(private val todoRepository: TodoRepository) {

    @Get
    fun getTodos(): HttpResponse<List<Todo>> {
        return HttpResponse.ok(todoRepository.findAll())
    }

    @Post
    fun addTodo(todo: Todo): HttpResponse<Todo> {
        return HttpResponse.created(todoRepository.save(todo))
    }

}