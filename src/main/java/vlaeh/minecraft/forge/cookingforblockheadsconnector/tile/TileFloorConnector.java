package vlaeh.minecraft.forge.cookingforblockheadsconnector.tile;

import net.blay09.mods.cookingforblockheads.api.capability.CapabilityKitchenConnector;
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

    private static final ItemStackHandler emptyItemHandler = new ItemStackHandler(0) {};
    private final MyKitchenItemProvider itemProvider = new MyKitchenItemProvider(emptyItemHandler);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityKitchenItemProvider.CAPABILITY
                || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityKitchenItemProvider.CAPABILITY) {
            final BlockPos position = pos.offset(EnumFacing.UP);
            final TileEntity tileEntity = world.getTileEntity(position);
            if (itemProvider.lastTileEntity == tileEntity)
                return (T) itemProvider.refreshSize();
            if (tileEntity != null) {
                if (tileEntity.hasCapability(CapabilityKitchenItemProvider.CAPABILITY, null)
                    || tileEntity.hasCapability(CapabilityKitchenSmeltingProvider.CAPABILITY, null)
                    || tileEntity.hasCapability(CapabilityKitchenConnector.CAPABILITY, null)) {
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

    private final static class MyKitchenItemProvider extends KitchenItemProvider {
        TileEntity lastTileEntity = null;
        IItemHandler lastItemHandler = null;
        int lastSize = -1;

        public MyKitchenItemProvider(final IItemHandler itemHandler) {
            setItemHandler(itemHandler);
        }

        @Override
        public void setItemHandler(final IItemHandler itemHandler) {
            lastItemHandler = itemHandler;
            lastSize = itemHandler.getSlots();
            super.setItemHandler(itemHandler);
        }

        public MyKitchenItemProvider refreshSize() {
            final int newSize = lastItemHandler.getSlots();
            if (lastSize < newSize) {
                lastSize = newSize;
                super.setItemHandler(lastItemHandler);
            }
            return this;
        }
    }

}