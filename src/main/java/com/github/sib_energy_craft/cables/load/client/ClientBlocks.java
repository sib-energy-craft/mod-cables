package com.github.sib_energy_craft.cables.load.client;

import com.github.sib_energy_craft.cables.load.Blocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

/**
 * @since 0.0.7
 * @author sibmaks
 */
public final class ClientBlocks implements ClientModInitializer {

    static {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.GLASS_FIBRE_CABLE.entity(), RenderLayer.getTranslucent());
    }

    @Override
    public void onInitializeClient() {

    }
}
