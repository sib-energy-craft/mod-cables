package com.github.sib_energy_craft.cables.load;

import com.github.sib_energy_craft.cables.block.IsolationType;
import com.github.sib_energy_craft.cables.block.copper.CopperCableBlock;
import com.github.sib_energy_craft.cables.block.copper.CopperCableIsolatedBlock;
import com.github.sib_energy_craft.cables.block.glass_fibre.GlassFibreCableBlock;
import com.github.sib_energy_craft.cables.block.tin.TinCableBlock;
import com.github.sib_energy_craft.cables.block.tin.TinCableIsolatedBlock;
import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import com.github.sib_energy_craft.sec_utils.common.Identified;
import com.github.sib_energy_craft.sec_utils.load.ModRegistrar;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;

import static com.github.sib_energy_craft.sec_utils.utils.BlockUtils.register;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public final class Blocks implements ModRegistrar {
    public static final Identified<CopperCableBlock> COPPER_CABLE;
    public static final Identified<CopperCableIsolatedBlock> COPPER_CABLE_ISOLATED;

    public static final Identified<TinCableBlock> TIN_CABLE;
    public static final Identified<TinCableIsolatedBlock> TIN_CABLE_ISOLATED;

    public static final Identified<GlassFibreCableBlock> GLASS_FIBRE_CABLE;

    static {
        var copperCableMaterial = new Material.Builder(MapColor.ORANGE).build();
        var copperCableSettings = AbstractBlock.Settings
                .of(copperCableMaterial)
                .strength(0.3f)
                .sounds(BlockSoundGroup.METAL);
        var copperCableBlock = new CopperCableBlock(IsolationType.NOT_ISOLATED, copperCableSettings);
        COPPER_CABLE = register(Identifiers.of("copper_cable"), copperCableBlock);

        var cableIsolatedMaterial = new Material.Builder(MapColor.BLACK).build();
        var cableIsolatedSettings = AbstractBlock.Settings
                .of(cableIsolatedMaterial)
                .strength(0.3f)
                .sounds(BlockSoundGroup.BASALT);
        var copperCableIsolatedBlock = new CopperCableIsolatedBlock(IsolationType.ISOLATED, cableIsolatedSettings);
        COPPER_CABLE_ISOLATED = register(Identifiers.of("copper_cable_isolated"), copperCableIsolatedBlock);

        var tinCableMaterial = new Material.Builder(MapColor.WATER_BLUE).build();
        var tinCableSettings = AbstractBlock.Settings
                .of(tinCableMaterial)
                .strength(0.3f)
                .sounds(BlockSoundGroup.METAL);
        var tinCableBlock = new TinCableBlock(IsolationType.NOT_ISOLATED, tinCableSettings);
        TIN_CABLE = register(Identifiers.of("tin_cable"), tinCableBlock);

        var tinCableIsolatedBlock = new TinCableIsolatedBlock(IsolationType.ISOLATED, cableIsolatedSettings);
        TIN_CABLE_ISOLATED = register(Identifiers.of("tin_cable_isolated"), tinCableIsolatedBlock);

        var glassCableSettings = AbstractBlock.Settings
                .of(Material.GLASS)
                .sounds(BlockSoundGroup.GLASS)
                .nonOpaque()
                .solidBlock((state, world, pos) -> false)
                .suffocates((state, world, pos) -> false)
                .blockVision((state, world, pos) -> false)
                .strength(0.3f);
        var glassFibreCableBlock = new GlassFibreCableBlock(IsolationType.ISOLATED, glassCableSettings);
        GLASS_FIBRE_CABLE = register(Identifiers.of("glass_fibre_cable"), glassFibreCableBlock);
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.GLASS_FIBRE_CABLE.entity(), RenderLayer.getTranslucent());
    }
}
