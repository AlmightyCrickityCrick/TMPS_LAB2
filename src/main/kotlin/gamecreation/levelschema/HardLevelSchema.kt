package gamecreation.levelschema

import gamecreation.utils.LinearLocation

class HardLevelSchema(
    override var mode: String,
    override var size: Int,
    override var mines: Int,
    override var mineLocations: LinearLocation,
    var treasures:Int,
    var treasureLocations:LinearLocation,
    var enemies:Int,
    var enemyLocations: LinearLocation
) : ILevelSchema {

    override fun clone(): HardLevelSchema {
        return HardLevelSchema(mode, size, mines, mineLocations, treasures, treasureLocations, enemies, enemyLocations)
    }
}