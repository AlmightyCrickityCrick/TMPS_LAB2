package gamecreation

import gamecreation.game.*
import gamecreation.game.level.*
import gamecreation.game.player.EasyPlayer
import gamecreation.game.player.HardPlayer
import gamecreation.levelschema.*
import gamecreation.utils.GridLocation
import gamecreation.utils.GridLocationAdapter

class GameCreatorFacade {
    fun createEasyLevel(bombLocations:GridLocation?):Pair<EasyLevel, EasyPlayer>{
        var levelBuilder: ISchemaBuilder = EasyLevelSchemaBuilder()
        var director = Director(levelBuilder)
        director.make("easy")
        if (bombLocations!=null)levelBuilder.setMineLocations(GridLocationAdapter(bombLocations,))
        var levelSchema: ILevelSchema = (levelBuilder as EasyLevelSchemaBuilder).result
        println(levelSchema)
        var gameLevel = AbstractGameFactory.create<EasyLevel>().createLevel(levelSchema)
        var gamePlayer = AbstractGameFactory.create<EasyLevel>().createPlayer()
        return Pair(gameLevel, gamePlayer) as Pair<EasyLevel, EasyPlayer>
    }
    fun bundleEasyLevels(levels:ArrayList<ILevelSystem>, player:EasyPlayer): Pair<EasyBundle, EasyPlayer>{
        return Pair(EasyBundle(levels), player)
    }
    fun createHardLevel(bombLocations:GridLocation?, treasureLocations: GridLocation?, enemyLocations:GridLocation?): Pair<HardLevel, HardPlayer>{
        var levelBuilder = HardLevelSchemaBuilder()
        var director = Director(levelBuilder)
        director.make("hard")
        if(bombLocations!=null) levelBuilder.setMineLocations(GridLocationAdapter(bombLocations,))
        if(treasureLocations!=null) levelBuilder.setMineLocations(GridLocationAdapter(treasureLocations,))
        if(enemyLocations!=null) levelBuilder.setMineLocations(GridLocationAdapter(enemyLocations,))

        var levelSchema = (levelBuilder as HardLevelSchemaBuilder).result
        println(levelSchema.size)
        var gameLevel = AbstractGameFactory.create<HardLevel>().createLevel(levelSchema)
        var gamePlayer = AbstractGameFactory.create<HardLevel>().createPlayer()
        return Pair(gameLevel, gamePlayer) as Pair<HardLevel, HardPlayer>
    }
    fun bundleHardLevels(levels: ArrayList<ILevelSystem>, player: HardPlayer ):Pair<HardBundle, HardPlayer>{
        return Pair(HardBundle(levels), player)
    }
}