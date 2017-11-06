package org.jnity.starstone.modifiers;

import org.jnity.starstone.cards.CreatureCard;

public class TurnAttackBuff extends CreatureModifier{

    private static int POWER_BUFF;

    public TurnAttackBuff(CreatureCard target, int powerBuff) {
        super(target);
        this.POWER_BUFF = powerBuff;
    }

    @Override
    public int modifyPower(int value, CreatureCard creatureCard){
        getTarget().removeModifier(this);
        return value + POWER_BUFF;
    }
}
