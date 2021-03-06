package org.jnity.starstone.core;

import org.jnity.starstone.cards.Card;
import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.events.GameEvent;
import org.jnity.starstone.events.GameListener;
import org.jnity.starstone.modifiers.CombatFatigue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
	private final ArrayList<GameListener> listeners = new ArrayList<>();
	private final List<Player> players = new ArrayList<>();
	private AtomicInteger turnNumber = new AtomicInteger(0);
	private Player activePlayer;

	public Game(Player p1, Player p2) {
		players.add(p1);
		players.add(p2);
		players.forEach(p -> p.setGame(this));
	}

	public void addListener(GameListener listener) {
		listeners.add(listener);
	}

	public void removeListener(Object listener) {
		listeners.remove(listener);
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	
	public void emit(GameEvent gameEvent, Card card, CreatureCard target) {
		Debug.print(card + " " + gameEvent + " " + target);
		@SuppressWarnings("unchecked")
		ArrayList<GameListener> listeners = (ArrayList<GameListener>)this.listeners.clone();
		for (GameListener gameListener : listeners) {
			gameListener.on(gameEvent, card, target);
		}
	}

	public void emit(GameEvent gameEvent, Card card) {
		Debug.print(gameEvent + " " + card);
		@SuppressWarnings("unchecked")
		ArrayList<GameListener> listeners = (ArrayList<GameListener>)this.listeners.clone();
		for (GameListener gameListener :  listeners) {
			gameListener.on(gameEvent, card);
		}
	}

	public void nextTurn() {
		if (activePlayer == null) {
			activePlayer = players.get(0);
			emit(GameEvent.GAME_BEGIN, activePlayer);
			players.get(0).drawCard();
			players.get(0).drawCard();
			players.get(0).drawCard();
			
			players.get(1).drawCard();
			players.get(1).drawCard();
			players.get(1).drawCard();
			players.get(1).drawCard();
			
		} else {
			emit(GameEvent.END_OF_TURN, activePlayer);
			if (activePlayer.equals(players.get(0))) {
				activePlayer = players.get(1);
			} else {
				activePlayer = players.get(0);
			}
		}
		activePlayer.drawCard();
		turnNumber.incrementAndGet();
		emit(GameEvent.NEW_TURN, activePlayer);
	}

	public int getTurnNumber() {
		return turnNumber.get();
	}

	public void battle(CreatureCard card, CreatureCard target) {
		int atackerPower = card.getPower();
		int defenderPower = target.getPower();
		emit(GameEvent.ATACKS, card, target);
		emit(GameEvent.DEFENDED, target, card);

		if(card.isHasSpecialAttack())
			card.specialAttack(target);
		else{
			target.takeDamage(atackerPower);
			card.addModifier(new CombatFatigue(card));
		}
		if(target.isHasSpecialAttack())
			target.specialAttack(card);
		else card.takeDamage(defenderPower);

	}

}
