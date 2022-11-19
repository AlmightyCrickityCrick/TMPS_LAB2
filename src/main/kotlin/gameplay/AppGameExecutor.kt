package gameplay

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.Key.Companion.Window
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import gamecreation.game.level.ILevelSystem
import gamecreation.game.player.HardPlayer
import gamecreation.game.player.IPlayer
import gamecreation.utils.Constants
import gamecreation.utils.DrawableObject

class AppGameExecutor :IGameExecutor {
    override lateinit var player: IPlayer
    override lateinit var levelSystem: ILevelSystem
    override var isPlayed: Boolean = false
    override lateinit var state: ArrayList<String>
    var flyweight = mapOf<String, DrawableObject>("#" to DrawableObject("#", "grey_square"), "@" to DrawableObject("X", "mine"),
    "^" to DrawableObject("^", "treasure_image"), "&" to DrawableObject("&", "enemy_image"), "O" to DrawableObject("0", "empty_square")
    )

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @Preview
    fun ButtonGrid(ls:ILevelSystem){
        var text by remember { mutableStateOf("#") }
        var itemslist = getItemlist()
        var rows = kotlin.math.sqrt(itemslist.size.toDouble()).toInt()
        LazyVerticalGrid(cells = GridCells.Fixed(rows)) {
            items(itemslist.size){ item ->  Column (
                horizontalAlignment = Alignment.CenterHorizontally){
                MaterialTheme {
                    Button(
                        onClick = {
                            levelSystem.open(item / rows, item%rows)
                            text = getItemlist()[item]
                            evaluate(text)

                        }
                    ) {

                    }
                }
            }
            }
        }
    }

    fun evaluate(text:String){
        when (text) {
            "&" -> (player as HardPlayer).fight()
            "X" -> player.loseLives(1)
            "^" -> player.addScore(Constants.TREASURE_SCORE)
            else -> player.addScore(Constants.SAFE_SCORE)
        }
    }

    fun getItemlist(): ArrayList<String>{
        val itemslist = ArrayList<String>()
        for (i in levelSystem.draw())
            for (j in i) if(j.toString()!="\n")itemslist.add(j.toString())
        return itemslist
    }

    override fun startGame() {
        isPlayed = true
        application {
            Window(onCloseRequest = ::exitApplication) { ButtonGrid(levelSystem) }

        }
    }

            override fun stopGame() {
                if(player.lives>0)println("You won")
                else println("You lost")
            }

            override fun showScore() {
                println("Score is ${player.score}")
            }
        }
