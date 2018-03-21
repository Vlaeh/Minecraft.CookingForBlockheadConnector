package vlaeh.minecraft.forge.cookingforblockheadsconnector.tile;

import net.blay09.mods.cookingforblockheads.api.capability.CapabilityKitchenItemProvider;
import net.blay09.mods.cookingforblockheads.api.capability.CapabilityKitchenSmeltingProvider;
import net.blay09.mods.cookingforblockheads.api.capability.KitchenItemProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class TileFloorConnector extends TileEntity {

    private final ItemStackHandler emptyItemHandler = new ItemStackHandler(0) {};
    private final KitchenItemProvider itemProvider = new KitchenItemProvider(emptyItemHandler);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityKitchenItemProvider.CAPABILITY
                || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityKitchenItemProvider.CAPABILITY) {
            final BlockPos position = pos.offset(EnumFacing.UP);
            final TileEntity tileEntity = world.getTileEntity(position);
            if (tileEntity != null) {
                if (tileEntity.hasCapability(CapabilityKitchenItemProvider.CAPABILITY, null)
                    || tileEntity.hasCapability(CapabilityKitchenSmeltingProvider.CAPABILITY, null)) {
                    itemProvider.setItemHandler(emptyItemHandler); // avoid scanning twice CookingForBlockheads blocks
                } else {
                    final IItemHandler c = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                    if (c != null)
                        itemProvider.setItemHandler(c);
                    else
                        itemProvider.setItemHandler(emptyItemHandler);
                }
            } else
                itemProvider.setItemHandler(emptyItemHandler);
            return (T) itemProvider;
        }
        return super.getCapability(capability, facing);
    }

}