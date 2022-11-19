package gameplay

import androidx.compose.runtime.Composable
import gamecreation.game.level.ILevelSystem
import gamecreation.game.player.IPlayer

interface IGameExecutor {
    var player:IPlayer
    var levelSystem:ILevelSystem
    var isPlayed :Boolean
    var state:ArrayList<String>

    fun startGame()
    fun stopGame()
    fun showScore()


}