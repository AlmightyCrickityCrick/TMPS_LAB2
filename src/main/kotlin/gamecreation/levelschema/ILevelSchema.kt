package gamecreation.levelschema

import gamecreation.utils.LinearLocation

interface ILevelSchema {
    var mode : String
    var size :Int
    var mines :Int
    var mineLocations : LinearLocation

    fun clone(): ILevelSchema
}