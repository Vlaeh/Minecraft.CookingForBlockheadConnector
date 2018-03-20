package vlaeh.minecraft.forge.cookingforblockheadsconnector.client;

import vlaeh.minecraft.forge.cookingforblockheadsconnector.CommonProxy;
import vlaeh.minecraft.forge.cookingforblockheadsconnector.block.ModBlocks;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerModels() {
        ModBlocks.registerModels();
    }

}
