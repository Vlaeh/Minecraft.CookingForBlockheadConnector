package vlaeh.minecraft.forge.cookingforblockheadsconnector;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.block.ModBlocks;

@Mod(modid = CookingForBlockheadsConnector.MODID,
     version = CookingForBlockheadsConnector.VERSION,
     name = CookingForBlockheadsConnector.NAME,
     acceptableRemoteVersions = "*",
     acceptedMinecraftVersions = "[1.9,1.12)",
     dependencies="required-after:cookingforblockheads@[3.1.16,)")
public class CookingForBlockheadsConnector {
    public static final String MODID = "cookingforblockheadsconnector";
    public static final String VERSION = "1.0";
    public static final String NAME = "Cooking For Blockheads Connector";

    public static Configuration config;
    public static boolean printTimestamp = true;
    public static boolean printDayPhases = true;
    public static boolean printMoonPhases = true;

    @Mod.Instance
    public static CookingForBlockheadsConnector instance;

    @SidedProxy(clientSide = "vlaeh.minecraft.forge.cookingforblockheadsconnector.client.ClientProxy",
                serverSide = "vlaeh.minecraft.forge.cookingforblockheadsconnector.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.registerBlocks();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerModels();
        ModBlocks.registerRecipes();
    }

}
