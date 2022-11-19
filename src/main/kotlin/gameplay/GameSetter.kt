package gameplay

import gamecreation.game.level.ILevelSystem
import gamecreation.game.player.IPlayer

open class GameSetter(var imp:IGameExecutor) {
    fun setLevelSystem(system: Pair<ILevelSystem, IPlayer>){
        imp.levelSystem = system.first
        imp.player = system.second
    }
    fun startGame(){
        imp.startGame()
    }

}