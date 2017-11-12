package org.jnity.starstone.events;

public enum GameEvent {
	NEW_GAME,
	GAME_BEGIN, END_OF_TURN, NEW_TURN,//global game events
	DRAW, PLAY,//card events
	ATACKS, DEFENDED,
	TAKE_DAMAGE, GIVE_DAMAGE, DIES, HEALED, SET_HITS, SET_POWER//player and creatures events
}
