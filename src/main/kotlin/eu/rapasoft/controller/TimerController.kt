package eu.rapasoft.controller

import eu.rapasoft.cache.TimerService
import eu.rapasoft.dto.CurrentState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*

@RequestMapping("/timer")
@RestController
class TimerController(@Autowired val timerService: TimerService) {

    @Async
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = arrayOf("http://localhost:3000"))
    @RequestMapping(value = "/start/{name}", method = arrayOf(RequestMethod.GET))
    fun startTimer(@PathVariable name: String): CurrentState {
        return timerService.start(name).getCurrentState()
    }

}