package org.jnity.starstone.run;

import org.jnity.starstone.cards.Card;
import org.jnity.starstone.core.Bot;
import org.jnity.starstone.core.Game;
import org.jnity.starstone.core.Player;
import org.jnity.starstone.core.TextHolder;
import org.jnity.starstone.protoss.creatures.Zealot;
import org.jnity.starstone.zerg.creatures.Baneling;
import org.jnity.starstone.zerg.creatures.Queen;
import org.jnity.starstone.zerg.creatures.Ravager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
	public static void main(String[] args) throws IOException {
		TextHolder.load("./text/ru.inf");
		List<Card> deck1 = new ArrayList<>();
		List<Card> deck2 = new ArrayList<>();

		deck1.add(new Baneling());
		deck1.add(new Queen());
		deck1.add(new Baneling());
		deck1.add(new Baneling());
		deck1.add(new Baneling());
		deck1.add(new Baneling());

		deck2.add(new Ravager());
		deck2.add(new Ravager());
		deck2.add(new Zealot());
		deck2.add(new Zealot());
		deck2.add(new Zealot());
		deck2.add(new Zealot());

		Player p1 = new Player("First player", deck1);
		Bot p2 = new Bot("Second player", deck2);
		Game game = new Game(p1, p2);

		game.nextTurn();
		p1.play(p1.getHand().get(0), null, 0);
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());

		game.nextTurn();
		p2.core();
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());

		game.nextTurn();
		p1.play(p1.getHand().get(0), null, 0);
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());

		game.nextTurn();
		p2.core();
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());

		game.nextTurn();
		p1.play(p1.getHand().get(0), null, 0);
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());

		game.nextTurn();
		p2.core();
		System.out.println(p1.getCreatures());
		System.out.println(p2.getCreatures());
	}
}

