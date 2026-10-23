package net.cribus.iceaspect.enchantment;

import com.mojang.serialization.MapCodec;
import net.cribus.iceaspect.IceAspect;
import net.cribus.iceaspect.IceAspectClient;
import net.cribus.iceaspect.enchantment.custom.IceAspectEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> ICE_ASPECT =
            registerEntityEffect("ice_aspect", IceAspectEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(IceAspect.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        IceAspect.LOGGER.info("Registering Mod Enchantment Effects for " + IceAspect.MOD_ID);
    }
}
