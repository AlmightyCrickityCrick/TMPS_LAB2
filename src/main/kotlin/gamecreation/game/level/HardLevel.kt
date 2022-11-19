package gamecreation.game.level

import gamecreation.levelschema.HardLevelSchema
import gamecreation.tiles.ITile
import gamecreation.tiles.TileFactory
import kotlin.math.sqrt

class HardLevel(private var schema: HardLevelSchema): ILevel{
    override lateinit var board: ArrayList<ArrayList<ITile>>
    override var tileFactory= TileFactory()
    override fun createLevel() {
        val length = sqrt(this.schema.size.toDouble()).toInt()
        board = ArrayList()
        var x = 0
        for (i in 0 until length) {
            board.add(ArrayList(length))
            for (j in 0..length) {
                when (x) {
                    in schema.mineLocations.location -> board[i].add(tileFactory.getTile("mine"))
                    in schema.enemyLocations.location -> board[i].add(tileFactory.getTile("enemy"))
                    in schema.treasureLocations.location -> board[i].add(tileFactory.getTile("treasure"))
                    else -> board[i].add(tileFactory.getTile("safe"))
                }
                x++
            }
        }
    }

    init{
        createLevel()
    }

    override fun draw(): ArrayList<String> {
        var strList = ArrayList<String>()
        var str = ""
        for (row in board){
            for (col in row) {
                str+= col.draw()
            }
            str+= "\n"
            strList.add(str)
            str = ""}
        return strList
    }

    override fun checkWinCondition(): Boolean {
        var discovered = 0
        var total = 0
        for (row in board)
            for (col in row){
                if (col.type == "treasure") {
                    total++
                    if (col.isChecked) discovered++
                }
            }
        return discovered == total
    }
    override fun getType(x: Int, y: Int): String {
        return board[x][y].type
    }

    override fun open(x:Int, y:Int) {
        board[x][y].open()
    }


}