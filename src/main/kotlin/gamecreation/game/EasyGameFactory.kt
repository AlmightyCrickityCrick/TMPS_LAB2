package gamecreation.game

import gamecreation.game.level.EasyLevel
import gamecreation.game.level.ILevel
import gamecreation.game.player.EasyPlayer
import gamecreation.game.player.IPlayer
import gamecreation.levelschema.EasyLevelSchema
import gamecreation.levelschema.ILevelSchema

class EasyGameFactory() : AbstractGameFactory() {
    override fun  createLevel(schema: ILevelSchema): ILevel {
        val easySchema = schema as EasyLevelSchema
        return EasyLevel(easySchema)
    }

    override fun createPlayer(): IPlayer {
        return EasyPlayer()
    }
}