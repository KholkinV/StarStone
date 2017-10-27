package org.jnity.starstone.modifiers;

import org.jnity.starstone.cards.Card;
import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.events.GameEvent;
import org.jnity.starstone.events.GameListener;

public class Poison extends CreatureModifier implements GameListener {

    public Poison(CreatureCard target) {
        super(target);
        target.getGame().addListener(this);
    }

    public void on(GameEvent gameEvent, Card card) {

        int count = 0;
        if(GameEvent.END_OF_TURN == gameEvent && count < 2) {
            count++;
            getTarget().takeDamage(1);
        } else getTarget().removeModifier(this);
    }
}
