package unittestkotlin.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import java.util.Random

class RandomParameterResolver : ParameterResolver {

    private val random: Random = Random();

    override fun supportsParameter(p0: ParameterContext, p1: ExtensionContext): Boolean {
        return p0.parameter.type == Random::class.java
    }


    // jika supportParam true maka return random
    override fun resolveParameter(p0: ParameterContext, p1: ExtensionContext): Any {
        return random
    }
}