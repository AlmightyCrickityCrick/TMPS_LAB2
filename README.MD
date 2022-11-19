# Structural Design Patterns


## Professor: Mihai Gaidau
## Student: Scripca Lina

----

## Objectives:

* Study and understand the Structural Design Patterns;
* Implement at least 5 SDPs for a specific domain;


## Used Design Patterns:

* Adapter
* Bridge
* Composite
* Facade
* Flyweight
* Decorator


## Implementation
The design patterns mentioned earlier have been implemented within the context of a Minesweeper Game builder.
The game builder allows for 2 regimes, easy and hard, requiring different level schemas, boards and player configurations for each, as well as two playing options - in console or within the app.

##### Important Concepts:
* Level Schema - Parametric notations regarding the configurations of a level or blueprint for level creation. Specifies the size of the game board, the number and locations of the bombs, enemies, treasures,
  without actually creating the gaming board.
* Level - Implementation of a schema according to its parameters.(i.e. The implementation of the gaming board with its elements)
* Easy Level - contains only safe tiles and bomb tiles. The user has only 1 life.
* Hard level - contains bombs, safe tiles, enemies and treasure. The user has 3 lives.
* Bundles - bundles are sets of levels that correspond to a single user instance and can be manipulated within the Setter and Executor classes just like single level, generating one playable board at a time.
### Facade
Facade is a structural design pattern that provides a simplified interface to a complex set of classes.

Within the current iteration of the laboratory work, the operations needed to construct a level schema and level itself have been simplified through the abstraction of the Game Creator Facade.
Which mean that by grouping up all the functionalities and steps necessary for the creation of a level, instead of invoking the following functions:

```
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
    fun bundleEasyLevels(levels:ArrayList<ILevel>, player:EasyPlayer): Pair<EasyBundle, EasyPlayer>{
        return Pair(EasyBundle(levels), player)
    }
```
the user can now just call a single: 

```
var gc = GameCreatorFacade().createEasyLevel(null)
```
### Adapter Pattern
Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.

Within this work, a GridLocationAdapter has been used to convert the user friendly Grid representation of game objects into the previously used Linear representation, within the context of the Builder and GameCreator classes.

```
class GridLocationAdapter(var loc: GridLocation) : LinearLocation(location = IntArray(0)){
    var lc: IntArray

    init{
        var locc = ArrayList<Int>()
        for (i in this.loc.location) locc.add((i.first + 1)* (i.second + 1))
        lc =  locc.toIntArray()
        location = lc
    }
}
```
In the main function, when invoking the GameCreatorFacade, the user sends a list of 2D coordinates for mines, enemies and treasures.
```
fun createEasyLevel(bombLocations:GridLocation?):Pair<EasyLevel, EasyPlayer>{
```
which means that he will indicate directly that he wants a mine in tile (2, 4). However, as the Builder classes have been created to hold integers, not coordonates,
the same mine has to be represented as 19. Which is where the adapter comes in.

```
if (bombLocations!=null)levelBuilder.setMineLocations(GridLocationAdapter(bombLocations,))
```

### Composite Pattern

Composite Pattern is a structural Design Pattern that lets one compose objects into tree structures and then work with the structures as if they were individual objects.

Within this work, the Composite pattern has been used for representation of groupings of levels and grouping of groupings of levels within a single structure called Bundle. For this, an ILevelSystem Interface was created that represents the totality of all structures (meaning Bundles and Levels) that correspond to a single user, 
can be checked whether a winning condition has been reached, as well as opened and drawn within the Executor classes.
```
interface ILevelSystem {
    fun draw() : ArrayList<String>
    fun checkWinCondition():Boolean
    fun open(x:Int, y:Int)
    fun getType(x:Int, y:Int):String
}

```
To realize the recursive call, akin to the file system folder and files within an OS, each necessary member within a bundle is being called upon with the same function as the highest level object.
```
override fun checkWinCondition(): Boolean {
        for (i in 0 until levelList.size) {
            if(levelList[i].checkWinCondition()) levelStatus[i] = true

        }
        return false in levelStatus
    }
```

### Bridge Method
The Bridge pattern lets one split large sets of closely related classes into two hierarchies - abstraction and implementation - that can be 
developed independently of each other.

Within this laboratory work the bridge has been used to separate the modus operandi(Classical Setting and Debugging setting) from the game environment implementations (App and Console Executors).


```
open class GameSetter(var imp:IGameExecutor) {
    fun setLevelSystem(system: Pair<ILevelSystem, IPlayer>){
        imp.levelSystem = system.first
        imp.player = system.second
    }
    fun startGame(){
        imp.startGame()
    }

}

class DebugGameSetter(imp: IGameExecutor) : GameSetter(imp) {
    fun startGameWithDebug(){
        val logger = KotlinLogging.logger{}

        thread {
            sleep(50000)
            logger.debug {
                imp.state
                imp.levelSystem
                imp.player
            }
        }
        imp.startGame()

    }

}
```
The Game Setter and Debug Game Setters Initialize the Level Systems used for the games and start them up in the corresponding mode.

While each of the Executors realizes the gameplay.
```
class ConsoleGameExecutor() :IGameExecutor{
    override lateinit var player: IPlayer
    override lateinit var levelSystem: ILevelSystem
    override var isPlayed: Boolean = false
    override lateinit var state: ArrayList<String>
    override fun startGame() {
        isPlayed = true
        while(player.lives>0 && !levelSystem.checkWinCondition()){
            println("*********************************")
            println("User has ${player.lives} lives \n Current score: ${player.score}")
            state = levelSystem.draw()
            for (row in state )print(row)
            getUserInput()
        }
        isPlayed = false
        stopGame()
        showScore()
    }
```

### Flyweight
Flyweight is a structural design pattern that lets us save memory by sharing common parts of state between objects instead of keeping it in each object.

Within this work, Flyweight was used to save and map out sources for images meant to be used within the App Executor witin the board.

```
class AppGameExecutor :IGameExecutor {
    override lateinit var player: IPlayer
    override lateinit var levelSystem: ILevelSystem
    override var isPlayed: Boolean = false
    override lateinit var state: ArrayList<String>
    
    var flyweight = mapOf<String, DrawableObject>("#" to DrawableObject("#", "grey_square"), "@" to DrawableObject("X", "mine"),
    "^" to DrawableObject("^", "treasure_image"), "&" to DrawableObject("&", "enemy_image"), "O" to DrawableObject("0", "empty_square")
    )

```
### Decorator

Decorator is a structural design pattern that lets users attach new behaviors to objects by placing these objects inside special wrappers that contain said behavior.

In this case, their usage was imposed by the Jetpack Compose framework to create the App Executor GUI interface.
Here, the function responsible for generating the grid of buttons has been decorated with the Composable and OptIn functionalities, to ensure the compilation of the grid into GUI objects and 
to allow for the usage of experimental components(LazyGrid) within it.

```
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
```

## Conclusions / Screenshots / Results

By implementing Design patterns we may hide and abstractize away the need for the client to know all the details and requirements for using objects, thus limiting the ammount of errors and the time needed for a person to get productive with the libraries.
As a result, within this project, the user need only know about the Facade for the game creation, the two modus operandi of the game, and the User Interfaces available to them.
![img.png](img.png))
![img_1.png](img_1.png)