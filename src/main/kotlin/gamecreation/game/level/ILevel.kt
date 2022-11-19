package gamecreation.game.level

import gamecreation.tiles.ITile
import gamecreation.tiles.TileFactory

interface ILevel:ILevelSystem{
    var board : ArrayList<ArrayList<ITile>>
    var tileFactory : TileFactory
    abstract fun createLevel()
}
