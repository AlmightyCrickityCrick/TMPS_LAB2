package gamecreation.tiles

class MineTile: ITile {
    override var isChecked: Boolean = false
    override var type: String = "mine"

    override fun draw(): String {
        if (isChecked) return "X"
        else return "#"
    }
}