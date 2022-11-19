package gameplay

import gamecreation.game.level.ILevelSystem
import gamecreation.game.player.HardPlayer
import gamecreation.game.player.IPlayer
import gamecreation.utils.Constants

class ConsoleGameExecutor() :IGameExecutor{
    override lateinit var player: IPlayer
    override lateinit var levelSystem: ILevelSystem
    override var isPlayed: Boolean = false
    override lateinit var state: ArrayList<String>
    override fun startGame() {
        isPlayed = true
        while(player.lives>0 && !levelSystem.checkWinCondition()){
            println("*********************************")
            println("User has ${player.lives} lives \n Current score: ${player.score}")
            state = levelSystem.draw()
            for (row in state )print(row)
            getUserInput()
        }
        isPlayed = false
        stopGame()
        showScore()
    }

    fun getUserInput(){
        println("Please select which square to open")
        val inp = readLine()
        var i = 0
        while((inp?.get(i)) != ' '){
            i++}
        var vl1 = inp?.substring(0, i)?.toInt()
        var vl2 = inp?.substring(i+1)?.toInt()
        if(vl1!=null && vl2 != null ) {
            var getType = levelSystem.getType(vl1, vl2)
            println("You encountered a $getType tile")
            levelSystem.open(vl1, vl2)
            when (getType) {
                "enemy" -> (player as HardPlayer).fight()
                "mine" -> player.loseLives(1)
                "treasure" -> player.addScore(Constants.TREASURE_SCORE)
                else -> player.addScore(Constants.SAFE_SCORE)
            }
        }
    }

    override fun stopGame() {
        if(player.lives>0)println("You won")
        else println("You lost")
    }

    override fun showScore() {
        println("Score is ${player.score}")
    }

}