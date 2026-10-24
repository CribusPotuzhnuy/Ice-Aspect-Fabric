package net.cribus.iceaspect.enchantment;

import net.cribus.iceaspect.IceAspect;
import net.cribus.iceaspect.enchantment.custom.IceAspectEnchantmentEffect;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> ICE_ASPECT =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(IceAspect.MOD_ID, "ice_aspect"));
    public static final TagKey<Enchantment> ICE_ASPECT_CONFLICTS =
            TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(IceAspect.MOD_ID, "exclusive_set/ice_aspect_conflicts"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, ICE_ASPECT, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                2,
                2,
                Enchantment.leveledCost(10, 20),
                Enchantment.leveledCost(60, 20),
                4,
                AttributeModifierSlot.MAINHAND
        )).exclusiveSet(enchantments.getOrThrow(ICE_ASPECT_CONFLICTS))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new IceAspectEnchantmentEffect()));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
