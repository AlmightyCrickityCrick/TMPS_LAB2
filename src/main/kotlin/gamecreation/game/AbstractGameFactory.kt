package gamecreation.game

import gamecreation.game.level.EasyLevel
import gamecreation.game.level.HardLevel
import gamecreation.game.level.ILevel
import gamecreation.game.player.IPlayer
import gamecreation.levelschema.ILevelSchema
import java.lang.IllegalArgumentException

abstract class AbstractGameFactory() {
    abstract fun createLevel(schema: ILevelSchema): ILevel
    abstract fun createPlayer(): IPlayer

    companion object{
        inline fun <reified T: ILevel> create(): AbstractGameFactory = when(T::class){
            EasyLevel::class -> EasyGameFactory()
            HardLevel::class -> HardGameFactory()
            else -> throw IllegalArgumentException()
        }
    }
}