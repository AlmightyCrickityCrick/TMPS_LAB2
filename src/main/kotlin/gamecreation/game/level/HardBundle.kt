package gamecreation.game.level

class HardBundle(override var levelList: ArrayList<ILevelSystem>, override var levelStatus: ArrayList<Boolean> = ArrayList()) :IBundle {
    init {
        for (i in levelList) levelStatus.add(false)
    }

    override fun draw(): ArrayList<String> {
        var i = 0
        while (i < levelStatus.size && levelStatus[i]) i++
        return levelList[i].draw()
    }

    override fun checkWinCondition(): Boolean {
        for (i in 0 until levelList.size) {
            if(levelList[i].checkWinCondition()) levelStatus[i] = true

        }
        return false in levelStatus
    }
    override fun open(x:Int, y:Int) {
        var i = 0
        while (i < levelStatus.size && levelStatus[i]) i++
         levelList[i].open(x, y)
    }
    override fun getType(x: Int, y: Int): String {
        var i = 0
        while (i < levelStatus.size && levelStatus[i]) i++
        return levelList[i].getType(x, y)
    }
}