package gamecreation.game.level

interface IBundle: ILevelSystem {
    var levelList:ArrayList<ILevelSystem>
    var levelStatus: ArrayList<Boolean>
}