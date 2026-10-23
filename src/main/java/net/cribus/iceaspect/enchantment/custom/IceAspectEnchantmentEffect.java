package net.cribus.iceaspect.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.cribus.iceaspect.IceAspect;
import net.cribus.iceaspect.IceAspectClient;
import net.cribus.iceaspect.effect.ModEffects;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;

public record IceAspectEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<IceAspectEnchantmentEffect> CODEC = MapCodec.unit(IceAspectEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (level > 0 && target instanceof LivingEntity livingEntity) {
            int duration = 80 + (level * 60);

            livingEntity.addStatusEffect(new StatusEffectInstance(
                    ModEffects.FREEZING, duration, 0, false, true, true
            ));

            if (livingEntity.isOnFire()) {
                livingEntity.setOnFire(false);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
