package vlaeh.minecraft.forge.cookingforblockheadsconnector.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.CookingForBlockheadsConnector;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.tile.TileFloorConnector;

import javax.annotation.Nullable;
import java.util.List;

public class BlockKitchenFloorConnector extends BlockContainer {

	public static final String name = "kitchen_floor_connector";
	public static final ResourceLocation registryName = new ResourceLocation(CookingForBlockheadsConnector.MODID, name);

	public BlockKitchenFloorConnector() {
		super(Material.ROCK);

		setUnlocalizedName(registryName.toString());
		setRegistryName(registryName.toString());
		setSoundType(SoundType.STONE);
		setCreativeTab(CookingForBlockheads.creativeTab);
		setHardness(5f);
		setResistance(10f);
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileFloorConnector();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(TextFormatting.YELLOW + I18n.format("tooltip." + CookingForBlockheadsConnector.MODID + ":multiblockKitchen"));
		for (String s : I18n.format("tooltip." + getRegistryName() + ".description").split("\\\\n")) {
			tooltip.add(TextFormatting.GRAY + s);
		}
	}

}
