package org.jnity.starstone.zerg.creatures;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.modifiers.CombatFatigue;
import org.jnity.starstone.modifiers.Poison;

public class Baneling extends CreatureCard{
    private static final long serialVersionUID = -930875945425631349L;

    public Baneling() {
        super("BANELING", 0, 0, 3, 2);
    }

    @Override
    public boolean isHasSpecialAttack() {
        return true;
    }

    @Override
    public void specialAttack(CreatureCard target){
        target.takeDamage(this.getPower());
        target.addModifier(new Poison(target));
        addModifier(new CombatFatigue(this));
    }

}
