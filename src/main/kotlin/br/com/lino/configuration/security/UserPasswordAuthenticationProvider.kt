package br.com.lino.configuration.security

import br.com.lino.repository.UserRepository
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import java.lang.Long.parseLong


@Singleton
class UserPasswordAuthenticationProvider(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoderService: BCryptPasswordEncoderService
    ) : AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        val user = userRepository.findById(parseLong(authenticationRequest?.identity as String))
        if(user.isPresent) {
            if(bCryptPasswordEncoderService.matches(authenticationRequest.secret as String, user.get().password))
                return Flowable.just(AuthenticationResponse.success(user.get().cpf.toString()))
            return Flowable.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH))
        }
        else {
            return Flowable.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH))
        }
    }
}