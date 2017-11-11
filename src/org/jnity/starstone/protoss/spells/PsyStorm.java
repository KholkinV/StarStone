package org.jnity.starstone.protoss.spells;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.cards.SpellCard;

// НЕ ФЛЕЙМСТРАЙК!!!
public class PsyStorm extends SpellCard {

    private static final long serialVersionUID = 3074406581143639274L;

    private static int DAMAGE = 4;

    public PsyStorm() {
        super("PSYSTORM", 7, 0);
    }

    @Override
    public void play(CreatureCard target){
        super.play(target);
        target.getOwner().getCreatures().forEach(c -> c.takeDamage(DAMAGE));
    }
}
