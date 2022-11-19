package gamecreation.game.level

interface ILevelSystem {
    fun draw() : ArrayList<String>
    fun checkWinCondition():Boolean
    fun open(x:Int, y:Int)
    fun getType(x:Int, y:Int):String
}