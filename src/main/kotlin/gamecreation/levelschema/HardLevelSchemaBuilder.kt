package gamecreation.levelschema

import gamecreation.utils.Constants
import gamecreation.utils.LinearLocation

class HardLevelSchemaBuilder : ISchemaBuilder {
    override var prototype: ILevelSchema = HardLevelSchema("hard", Constants.HARD_BOARD_SIZE, 0, LinearLocation(intArrayOf(0)), 0, LinearLocation(intArrayOf(0)), 0, LinearLocation(intArrayOf(0)))
    var result: HardLevelSchema = prototype.clone() as HardLevelSchema

    override fun reset() {
        this.result = prototype.clone()  as HardLevelSchema
    }
    override fun setSize() {
        result.size = Constants.HARD_BOARD_SIZE
    }

    override fun setMode() {
        result.mode = "hard"
    }

    override fun setMines(mines:Int) {
        result.mines = mines
    }

    override fun setMineLocations(loc:LinearLocation) {
        result.mineLocations = loc
    }

    fun setTreasures(tres:Int) {
        result.treasures = tres
    }

    fun setTreasureLocations(loc:LinearLocation) {
        result.treasureLocations = loc
    }

     fun setEnemies(en:Int) {
        result.enemies = en
    }

    fun setEnemiesLocations(loc:LinearLocation) {
        result.enemyLocations = loc
    }

    override fun build(): ILevelSchema {
        return this.result
    }
}