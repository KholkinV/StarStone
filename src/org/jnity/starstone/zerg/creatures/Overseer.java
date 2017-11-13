package org.jnity.starstone.zerg.creatures;

import org.jnity.starstone.cards.CreatureCard;

public class Overseer extends CreatureCard {
    
    private static final int HITS = 4;
    private static final int POWER = 4;
    private static final long serialVersionUID = 7867773086540525572L;

    public Overseer(){
        super("OVERSEER", 0, 0, 4, 3);
    } 

    @Override
    public void play(CreatureCard target) {
        super.play(target);
        target.setHits(HITS);
        target.setPower(POWER);
    }

}