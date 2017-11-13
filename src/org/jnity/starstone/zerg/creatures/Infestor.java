package org.jnity.starstone.zerg.creatures;

import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.core.Player;
import org.jnity.starstone.modifiers.Poison;

import java.util.ArrayList;
import java.util.List;

public class Infestor extends CreatureCard {

    private static final long serialVersionUID = 4428563786048463728L;

    public Infestor() {
        super("INFESTOR",0, 0, 5, 3);
    }

    @Override
    public void play(CreatureCard target){
        super.play(target);
        getTargets().forEach(c -> c.addModifier(new Poison(c)));
    }

    private List<CreatureCard> getTargets(){
        List<CreatureCard> targets = new ArrayList<>();
        for (Player p : getGame().getPlayers()) {
            if (p.equals(this)) continue;
            else {
                targets = p.getCreatures();
            }
        }
        return targets;
    }
}
