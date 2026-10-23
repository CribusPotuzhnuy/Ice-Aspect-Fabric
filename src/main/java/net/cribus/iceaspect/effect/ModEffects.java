package net.cribus.iceaspect.effect;

import net.cribus.iceaspect.IceAspect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> FREEZING = registerStatusEffect("freezing",
            new FreezingEffect(StatusEffectCategory.HARMFUL, 0x80e5ef).addAttributeModifier(EntityAttributes.MOVEMENT_SPEED,
                    Identifier.of(IceAspect.MOD_ID, "freezing"
                    ),-0.50f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(IceAspect.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        IceAspect.LOGGER.info("Registering Mod Effects For " + IceAspect.MOD_ID);
    }
}
