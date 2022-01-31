package ca.lanewelch.minecrafttweaks;

import ca.lanewelch.minecrafttweaks.init.ItemInit;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// Sets the MODID value (matches with the MODID in the mods.toml file)
@Mod("minecrafttweaks")
public class MinecraftTweaksMain
{
    // 
    public static String MOD_ID = "minecrafttweaks";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public MinecraftTweaksMain() {
        // Register the setup method for modloading (automatic)
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading (automatic)
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading (automatic)
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in (automatic)
        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        ItemInit.ITEMS.register(modEventBus);
    }

    //Sets a void class for 'setup'
    private void setup(final FMLCommonSetupEvent event) {

    }

    // (Automatic)
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod (Automatic)
        InterModComms.sendTo("minecrafttweaks", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    // (Automatic)
    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods (automatic)
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // (Automatic)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
