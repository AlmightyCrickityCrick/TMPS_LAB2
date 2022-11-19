package gamecreation.tiles

class SafeTile: ITile {
    override var isChecked: Boolean = false
    override var type: String = "safe"

    override fun draw() :String {
        if (isChecked) return "O"
        else return "#"
    }
}