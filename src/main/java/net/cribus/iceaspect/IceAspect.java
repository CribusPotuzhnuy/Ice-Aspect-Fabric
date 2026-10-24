package net.cribus.iceaspect;

import net.cribus.iceaspect.effect.ModEffects;
import net.cribus.iceaspect.enchantment.ModEnchantmentEffects;
import net.cribus.iceaspect.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IceAspect implements ModInitializer {
	public static final String MOD_ID = "iceaspect";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModEnchantmentEffects.registerEnchantmentEffects();

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SNOWBALL, ModPotions.FREEZING_POTION);
		});

		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
			if (entity.hasStatusEffect(ModEffects.FREEZING) && source.isOf(DamageTypes.FREEZE)) {
				if (entity.inPowderSnow) {
					return true;
				}

				if (amount == 1.5f) {
					return true;
				}

				entity.damage((ServerWorld) entity.getEntityWorld(), source, 1.5f);
				return false;
			}
			return true;
		});
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
