package vlaeh.minecraft.forge.cookingforblockheadsconnector.block;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.CookingForBlockheadsConnector;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.tile.TileFloorConnector;

@GameRegistry.ObjectHolder(CookingForBlockheadsConnector.MODID)
public class ModBlocks {

    @GameRegistry.ObjectHolder(BlockKitchenFloorConnector.name)
    public static final Block floorConnector = Blocks.STONE;


    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.registerAll(
                new BlockKitchenFloorConnector().setRegistryName(BlockKitchenFloorConnector.name));
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                new ItemBlock(floorConnector).setRegistryName(BlockKitchenFloorConnector.name));
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileFloorConnector.class, BlockKitchenFloorConnector.registryName.toString());
    }

    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(floorConnector), 0, new ModelResourceLocation(BlockKitchenFloorConnector.registryName, "inventory"));
    }

}

