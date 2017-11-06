package org.jnity.starstone.zerg.creatures;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.modifiers.CombatFatigue;

import java.util.List;

public class Ultralisk extends CreatureCard {

    private static final long serialVersionUID = -2809682915424808307L;

    public Ultralisk() {
        super("ULTRALISK", 8, 0, 8, 6);
    }

    @Override
    public boolean isHasSpecialAttack() {
        return true;
    }

    @Override
    public void specialAttack(CreatureCard target){
        target.takeDamage(this.getPower());
        List<CreatureCard> neighbors = target.getOwner().getCreaturesNear(target);
        neighbors.forEach(c -> c.takeDamage(this.getPower() / 2));
        addModifier(new CombatFatigue(this));
    }

}
