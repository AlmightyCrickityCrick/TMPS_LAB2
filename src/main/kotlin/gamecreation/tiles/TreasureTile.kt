package gamecreation.tiles

class TreasureTile: ITile {
    override var isChecked: Boolean = false
    override var type: String = "treasure"

    override fun draw(): String {
        if (isChecked) return "^"
        else return "#"
    }
}