package parqk.parqk

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.web.client.RestTemplate
import java.util.Random
import java.util.concurrent.CompletableFuture
import javax.persistence.EntityManager


@RestController
open class HomeController @Autowired constructor(private val idGenerator: IdGenerator,private val em: EntityManager) {

    @RequestMapping("/seq")
    open fun seq(): String {
        val t0 = System.nanoTime()

        val id1 = idGenerator.nextId()
        val id2 = idGenerator.nextId()
        val id3 = idGenerator.nextId()
        val id4 = idGenerator.nextId()
        val id5 = idGenerator.nextId()

        val t1 = System.nanoTime()
        val dt = "Elapsed time: " + (t1 - t0) + " ns"

        return dt
    }

    @RequestMapping("/remote")
    fun remoteCall(): String {
        val t0 = System.nanoTime()

        val restTempalte = RestTemplate()
        val rand = Random()
        val r1 = restTempalte.getForObject("https://www.emoney.ge/index.php/main/welcome?r="+rand.nextInt(1000) + 1, String::class.java)
        val r2 = restTempalte.getForObject("https://www.emoney.ge/index.php/main/welcome?r="+rand.nextInt(1000) + 1, String::class.java)
        val r3 = restTempalte.getForObject("https://www.emoney.ge/index.php/main/welcome?r="+rand.nextInt(1000) + 1, String::class.java)
        val t1 = System.nanoTime()
        val dt = "Elapsed time: " + (t1 - t0) + " ns"

        return dt
    }

    @RequestMapping("/complex-query")
    open fun complexQuery(): String {
        val t0 = System.nanoTime()

        var result1 = em.createNativeQuery("SELECT _accession_key, _refs_key FROM mgd.acc_accessionreference TABLESAMPLE SYSTEM (0.01)").getResultList().get(0)
        var result2 = em.createNativeQuery("SELECT _accession_key, _refs_key FROM mgd.acc_accessionreference TABLESAMPLE SYSTEM (0.01)").getResultList().get(0)
        var result3 = em.createNativeQuery("SELECT _accession_key, _refs_key FROM mgd.acc_accessionreference TABLESAMPLE SYSTEM (0.01)").getResultList().get(0)

        val t1 = System.nanoTime()
        val dt = "Elapsed time: " + (t1 - t0) + " ns"

        return dt

    }

    @Async
    open fun makeCall(): CompletableFuture<String>  {
        val restTempalte = RestTemplate()
        val rand = Random()
       val r: String = restTempalte.getForObject("https://www.emoney.ge/index.php/main/welcome?r="+rand.nextInt(1000) + 1, String::class.java)
       return CompletableFuture.completedFuture(r)
    }
}