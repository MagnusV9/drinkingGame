package dice

class Dice{
    private val numSides: IntRange = 1.. 6;
    fun rollDice() :Int{
        return numSides.random();
    }
}