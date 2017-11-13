package org.jnity.starstone.zerg.creatures;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.modifiers.CombatFatigue;
import org.jnity.starstone.modifiers.PlasmaShield;


public class Ravager extends CreatureCard {

    private static final long serialVersionUID = 6702633870724841421L;

    public Ravager() {
        super("RAVAGER", 3, 0, 4, 3);
    }

    @Override
    public boolean isHasSpecialAttack() {
        return true;
    }

    @Override
    public void specialAttack(CreatureCard target){
        target.getModifiers().forEach(m -> {
            if(m instanceof PlasmaShield){
                target.removeModifier(m);
            }
        });
        target.takeDamage(this.getPower());
        addModifier(new CombatFatigue(this));
    }
}
