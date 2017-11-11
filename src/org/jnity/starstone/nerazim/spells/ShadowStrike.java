package org.jnity.starstone.nerazim.spells;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.cards.SpellCard;

public class ShadowStrike extends SpellCard {

    private static final long serialVersionUID = -479982009316387272L;

    private static int DAMAGE = 2;
    private static int MAX_DAMAGE = 4;

    public ShadowStrike() {
        super("SHADOWSTRIKE", 2, 0);
    }

    @Override
    public void play(CreatureCard target){
        super.play(target);
        if(getOwner().getCountPlayedCard() > 0)
            target.takeDamage(MAX_DAMAGE);
        else
            target.takeDamage(DAMAGE);
    }
}
