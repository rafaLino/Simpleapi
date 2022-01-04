package br.com.lino.configuration.security

import jakarta.inject.Singleton
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Singleton
class BCryptPasswordEncoderService : PasswordEncoder {
    private val bcryptEncoder = BCryptPasswordEncoder()
    override fun encode(rawPassword: CharSequence?): String {
        return bcryptEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return bcryptEncoder.matches(rawPassword, encodedPassword)
    }
}