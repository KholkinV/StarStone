package org.jnity.starstone.run;

import org.jnity.starstone.cards.Card;
import org.jnity.starstone.core.Game;
import org.jnity.starstone.core.Player;
import org.jnity.starstone.core.TextHolder;
import org.jnity.starstone.zerg.creatures.Baneling;
import org.jnity.starstone.zerg.creatures.Queen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
	public static void main(String[] args) throws IOException {
		TextHolder.load("./text/ru.inf");
		List<Card> deck1 = new ArrayList<>();
		List<Card> deck2 = new ArrayList<>();

		deck1.add(new Baneling());
		deck2.add(new Queen());
	
		Player p1 = new Player("First player", deck1);
		Player p2 = new Player("Second player", deck2);
		Game game = new Game(p1, p2);
		game.nextTurn();

		p1.play(p1.getHand().get(0), null, 0);
		game.nextTurn();
		p2.play(p2.getHand().get(0), null, 0);
		game.nextTurn();
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());
		game.battle(p1.getCreatures().get(0), p2.getCreatures().get(0));
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());
		game.battle(p1.getCreatures().get(0), p2.getCreatures().get(0));
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());
		game.battle(p1.getCreatures().get(0), p2.getCreatures().get(0));
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());
	}
}

