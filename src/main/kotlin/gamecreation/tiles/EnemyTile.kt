package gamecreation.tiles

class EnemyTile : ITile {
    override var isChecked: Boolean = false
    override var type: String = "enemy"

    override fun draw() : String {
        if(isChecked) return "&"
        else return "#"
    }
}