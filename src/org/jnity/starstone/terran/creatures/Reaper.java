package org.jnity.starstone.terran.creatures;

import org.jnity.starstone.cards.Card;
import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.events.GameEvent;
import org.jnity.starstone.events.GameListener;
import org.jnity.starstone.modifiers.CombatFatigue;

public class Reaper extends CreatureCard implements GameListener{

    private static final long serialVersionUID = 7038081483022175035L;

    private int attackCount = 0;

    public Reaper() {
        super("REAPER", 0, 0, 9, 1);
    }

    @Override
    public boolean isHasSpecialAttack() {
        return true;
    }

    @Override
    public void specialAttack(CreatureCard target) {

        target.takeDamage(this.getPower());
        attackCount++;

        if(attackCount == 2) addModifier(new CombatFatigue(this));
    }

    public void on(GameEvent gameEvent, Card card) {
        if (gameEvent == GameEvent.END_OF_TURN) {
            attackCount = 0;
        }
    }
}
