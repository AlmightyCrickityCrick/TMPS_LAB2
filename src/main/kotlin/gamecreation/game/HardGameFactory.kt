package gamecreation.game

import gamecreation.game.level.HardLevel
import gamecreation.game.level.ILevel
import gamecreation.game.player.HardPlayer
import gamecreation.game.player.IPlayer
import gamecreation.levelschema.HardLevelSchema
import gamecreation.levelschema.ILevelSchema

class HardGameFactory() : AbstractGameFactory() {
    override fun createLevel(schema: ILevelSchema): ILevel {
        val hardLevelSchema = schema as HardLevelSchema
        return HardLevel(hardLevelSchema)
    }

    override fun createPlayer(): IPlayer {
        return HardPlayer()
    }
}