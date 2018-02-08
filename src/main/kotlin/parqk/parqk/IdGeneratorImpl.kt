package parqk.parqk

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import javax.persistence.EntityManager

@Component
open class IdGeneratorImpl constructor(private val em: EntityManager): IdGenerator {


    override fun nextId(): Long {
        var result: Any? = em.createNativeQuery("select nextval('scala_id_seq')").getResultList().get(0)
        return result?.toString()?.toLong() ?: 0
    }
}