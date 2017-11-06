package org.jnity.starstone.nerazim.spells;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.cards.SpellCard;
import org.jnity.starstone.modifiers.Invisibility;
import org.jnity.starstone.modifiers.TurnAttackBuff;

import java.util.List;

public class ShadowDance extends SpellCard {

    private static final long serialVersionUID = 4320370784822342205L;

    private static int POWER_BUFF = 2;

    public ShadowDance() {
        super("SHADOWDANCE", 0, 0);
    }

    @Override
    public void play(CreatureCard target){
        super.play(target);
        List<CreatureCard> creatures = getOwner().getCreatures();

        creatures.forEach((c) -> c.getModifiers().forEach((m) -> {
            if(m instanceof Invisibility) c.addModifier(new TurnAttackBuff(c, POWER_BUFF));
        }));

    }
}
