package ca.lanewelch.minecrafttweaks.init;

import ca.lanewelch.minecrafttweaks.MinecraftTweaksMain;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;
import org.openjdk.nashorn.internal.objects.Global;

import javax.annotation.Nullable;
import java.util.List;

public class ItemInit {

    public static class ModCreativeTab extends CreativeModeTab {

        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CHAINLINK.get());
        }
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "minecrafttweaks");

    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftTweaksMain.MOD_ID);

    public static final RegistryObject<Item> CHAINLINK = ITEMS.register("chainlink", () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));


    public static final RegistryObject<Item> RAWGOLDENAPPLE = ITEMS.register("rawgoldenapple", () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(2)
                    .effect(() -> new MobEffectInstance(MobEffects.HARM, 200, 2147483639), 0.7F)
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.LUCK, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 12000, 3), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, 2), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 12000, 20), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HARM, 200, 2147483639), 0.85F)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12000, 20), 1F).build())));

    public class KeyboardHelper {

        private static final long WINDOW = Minecraft.getInstance().getWindow().getWindow();

        @OnlyIn(Dist.CLIENT)
        public static boolean isHoldingShift() {
            return InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
        }

        @OnlyIn(Dist.CLIENT)
        public static boolean isHoldingControl() {
            return InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
        }

        @OnlyIn(Dist.CLIENT)
        public static boolean isHoldingSpace() {
            return InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_SPACE);
        }
    }

}

//effect clear