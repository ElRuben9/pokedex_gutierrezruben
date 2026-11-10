package gutierrez.ruben.pokedexcompose.data

import gutierrez.ruben.pokedexcompose.R

object PokedexCatalog {

    val absol = Pokemon(
        "Absol",
        359,
        "Dark",
        "It senses coming disasters and appears before people only to warn them of impending danger.",
        1.2f,
        47.0f,
        true,
        "Pressure/Super Luck",
        R.drawable.absol
    )
    val pokemons = listOf(
        Pokemon(
            "Gardevoir",
            282,
            "Psychic",
            "To protect its Trainer, it will expend all its psychic power to create a small black hole.",
            1.6f,
            48.4f,
            true,
            "Synchronize/Trace",
            R.drawable.gardevoir
        ),
        Pokemon("Salazzle", 758, "Poison", "Salazzle makes its opponents light-headed with poisonous gas, then captivates them with alluring movements to turn them into loyal servants.", 1.2f, 22.2f, false, "Corrosion", R.drawable.salazzle),
        Pokemon("Primarina", 730, "Water", "To Primarina, every battle is a stage. It takes down its prey with beautiful singing and dancing.", 1.8f, 44.0f, true, "Torrent", R.drawable.primarina),
        Pokemon("Espurr", 677, "Psychic", "It keeps its ears covered to prevent the intense psychic power in its body from escaping, but it falls unconscious if this power builds too much.", 0.3f, 3.5f, true, "Keen Eye/Infiltrator", R.drawable.espurr),
        Pokemon("Hatterene", 858, "Fairy", "Hatterene knocks out those that intrude in its home forest by blasting them with a beam, then slashing with claws enhanced by psychic power.", 2.1f, 5.1f, false, "Healer/Anticipation", R.drawable.hatterene),
        Pokemon("Absol", 359, "Dark", "It senses coming disasters and appears before people only to warn them of impending danger.", 1.2f, 47.0f, false, "Pressure/Super Luck", R.drawable.absol),
        Pokemon("Noivern", 715, "Flying", "Ultrasonic waves emitted by a Noivern can pulverize a large boulder. This Pokémon has a cruel disposition.", 1.5f, 85.0f, false, "Frisk/Infiltrator", R.drawable.noivern),
        Pokemon("Alolan Vulpix", 37, "Ice", "They live together in a skulk, helping one another. Before eating their prey, they freeze it solid with their −58 degree Fahrenheit breath.", 0.6f, 9.9f, true, "Snow Cloak", R.drawable.vulpix),
        Pokemon("Dragapult", 887, "Dragon", "Dragapult can make its whole body transparent by clearing its mind and focusing. Even the Dreepy in Dragapult’s horns become invisible.", 3.0f, 50.0f, true, "Clear Body/Infiltrator", R.drawable.dragapult),
        Pokemon("Zoroark", 571, "Dark", "Stories say those who tried to catch Zoroark were trapped in an illusion and punished.", 1.6f, 81.1f, true, "Illusion", R.drawable.zoroark)
    )
}
