package org.jnity.starstone.core;

import org.jnity.starstone.modifiers.Modifier;

import java.util.ArrayList;
import java.util.List;

public class ModifierContainer extends GamePart {
	private final ArrayList<Modifier> modifiers = new ArrayList<>();
	
	public List<Modifier> getModifiers() {
		return (List<Modifier>) modifiers.clone();
	}

	public void addModifier(Modifier modifier) {
		if(!modifier.canBeDuplicated())
			modifiers.removeIf(m -> m.getClass() == modifier.getClass());
		modifiers.add(modifier);
	}

	public void removeModifier(Modifier modifier) {
		modifiers.remove(modifier);
	}
	
	public boolean hasModifier(Class<? extends Modifier> modifierType) {
		return getModifiers().stream().anyMatch(m -> m.getClass() == modifierType);
	}
	
}
