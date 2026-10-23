package net.cribus.iceaspect.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;

public class FreezingEffect extends StatusEffect {
    public FreezingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isOnFire()) {
            entity.removeStatusEffect(ModEffects.FREEZING);
            entity.setFrozenTicks(0);
            return false;
        }
        entity.setFrozenTicks(Math.max(entity.getFrozenTicks(), 300));


        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}