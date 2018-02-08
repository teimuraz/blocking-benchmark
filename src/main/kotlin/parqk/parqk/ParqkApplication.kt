package parqk.parqk

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@SpringBootApplication
@EnableAsync
open class ParqkApplication {

    @Bean
    open fun asyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 20
        executor.maxPoolSize = 20
        executor.setQueueCapacity(500)
        executor.threadNamePrefix = "GithubLookup-"
        executor.initialize()
        return executor
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ParqkApplication::class.java, *args)
}
