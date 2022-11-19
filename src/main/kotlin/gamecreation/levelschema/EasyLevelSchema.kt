package gamecreation.levelschema

import gamecreation.utils.LinearLocation

class EasyLevelSchema(
    override var mode: String,
    override var size: Int,
    override var mines: Int,
    override var mineLocations: LinearLocation
) : ILevelSchema {
    override fun clone(): ILevelSchema {
        return EasyLevelSchema(mode, size, mines, mineLocations)
    }
}