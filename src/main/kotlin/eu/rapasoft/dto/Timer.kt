package eu.rapasoft.dto

class Timer(val name: String, val durationInSeconds: Long = 10) {
    var counter = durationInSeconds

    fun getCurrentState(): CurrentState {
        return CurrentState(name, if (counter == durationInSeconds) {
            State.STARTED
        } else if (counter > 0) {
            State.IN_PROGRESS
        } else {
            State.ENDED
        }, counter)
    }
}

data class CurrentState(val name: String, val state: State, val counter: Long)

enum class State {
    STARTED,
    IN_PROGRESS,
    ENDED
}