package eu.rapasoft.cache

import eu.rapasoft.dto.Timer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import kotlin.concurrent.fixedRateTimer

@Service
open class TimerService(@Autowired val simpleMessagingTemplate: SimpMessagingTemplate) {
    val timers: MutableMap<String, Timer> = hashMapOf()

    fun start(name: String): Timer {
        val timer = Timer(name)
        timers.put(name, timer)

        fixedRateTimer(name, true, 0, 1000, {
            if (timer.counter > 0) {
                timer.counter--
                val message = "Timer $name - remaining ${timer.counter} seconds"
                println(message)
                simpleMessagingTemplate.convertAndSend("/topic/timers", message)
            } else {
                this.cancel()
            }
        })

        return timer;
    }
}