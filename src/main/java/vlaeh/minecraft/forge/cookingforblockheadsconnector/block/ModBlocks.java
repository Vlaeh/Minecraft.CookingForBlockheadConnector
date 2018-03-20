package vlaeh.minecraft.forge.cookingforblockheadsconnector.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.tile.TileFloorConnector;

public class ModBlocks {
    public static final BlockKitchenFloorConnector floorConnector = new BlockKitchenFloorConnector();

    public static void registerBlocks() {
        registerBlockContainer(floorConnector, TileFloorConnector.class);
    }

    private final static void registerBlockContainer(Block block, Class<? extends TileEntity> tileEntityClass) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        GameRegistry.registerTileEntity(
                tileEntityClass,
                block.getRegistryName().toString());
    }

    public static void registerRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.floorConnector), "B W", " P ", "W B", 'W', Blocks.COAL_BLOCK, 'B', Blocks.QUARTZ_BLOCK, 'P', "plankWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.floorConnector), "B W", " P ", "W B", 'W', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15), 'B', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0), 'P', "plankWood"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(Item.getItemFromBlock(floorConnector), 0, new ModelResourceLocation(floorConnector.getRegistryName(), "inventory"));
    }

}

