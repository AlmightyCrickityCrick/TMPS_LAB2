package gamecreation.utils

class GridLocationAdapter(var loc: GridLocation) : LinearLocation(location = IntArray(0)){
    var lc: IntArray

    init{
        var locc = ArrayList<Int>()
        for (i in this.loc.location) locc.add((i.first + 1)* (i.second + 1))
        lc =  locc.toIntArray()
        location = lc
    }
}