package vlaeh.minecraft.forge.cookingforblockheadsconnector;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.block.ModBlocks;

@Mod.EventBusSubscriber(modid = CookingForBlockheadsConnector.MODID)
@Mod(modid = CookingForBlockheadsConnector.MODID,
     version = CookingForBlockheadsConnector.VERSION,
     name = CookingForBlockheadsConnector.NAME,
     acceptableRemoteVersions = "*",
     acceptedMinecraftVersions = "[1.12,1.13)",
     dependencies="required-after:cookingforblockheads@[6.3.0,)")
public class CookingForBlockheadsConnector {
    public static final String MODID = "cookingforblockheadsconnector";
    public static final String VERSION = "1.0";
    public static final String NAME = "Cooking For Blockheads Connector";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.registerTileEntities();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModBlocks.registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.registerModels();
    }

}
