package gameplay

import kotlinx.coroutines.*
import mu.KotlinLogging
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class DebugGameSetter(imp: IGameExecutor) : GameSetter(imp) {
    fun startGameWithDebug(){
        val logger = KotlinLogging.logger{}

        thread {
            sleep(50000)
            logger.debug {
                imp.state
                imp.levelSystem
                imp.player
            }
        }
        imp.startGame()

    }

}