package org.jnity.starstone.modifiers;

import org.jnity.starstone.cards.CreatureCard;

public class Buff extends CreatureModifier {

    private static int POWER_BUFF;
    private int HITS_BUFF;

    public Buff(CreatureCard target, int powerBuff, int hitsBuff) {
        super(target);
        this.POWER_BUFF = powerBuff;
        this.HITS_BUFF = hitsBuff;
    }

    @Override
    public int modifyPower(int value, CreatureCard creatureCard){
        return value + POWER_BUFF;
    }

    @Override
    public int modifyMaxHits(int value, CreatureCard creatureCard){
        return value + HITS_BUFF;
    }


}
