package com.github.sib_energy_craft.cables.load;

import com.github.sib_energy_craft.cables.block.copper.entity.CopperCableBlockEntity;
import com.github.sib_energy_craft.cables.block.copper.entity.CopperCableIsolatedBlockEntity;
import com.github.sib_energy_craft.cables.block.glass_fibre.entity.GlassFibreCableBlockEntity;
import com.github.sib_energy_craft.cables.block.gold.entity.GoldCableBlockEntity;
import com.github.sib_energy_craft.cables.block.gold.entity.GoldCableIsolatedBlockEntity;
import com.github.sib_energy_craft.cables.block.gold.entity.GoldCableIsolatedX2BlockEntity;
import com.github.sib_energy_craft.cables.block.tin.entity.TinCableBlockEntity;
import com.github.sib_energy_craft.cables.block.tin.entity.TinCableIsolatedBlockEntity;
import com.github.sib_energy_craft.sec_utils.load.ModRegistrar;
import net.minecraft.block.entity.BlockEntityType;

import static com.github.sib_energy_craft.sec_utils.utils.EntityUtils.register;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public final class Entities implements ModRegistrar {
    public static final BlockEntityType<CopperCableBlockEntity> COPPER_CABLE;
    public static final BlockEntityType<CopperCableIsolatedBlockEntity> COPPER_CABLE_ISOLATED;

    public static final BlockEntityType<TinCableBlockEntity> TIN_CABLE;
    public static final BlockEntityType<TinCableIsolatedBlockEntity> TIN_CABLE_ISOLATED;

    public static final BlockEntityType<GoldCableBlockEntity> GOLD_CABLE;
    public static final BlockEntityType<GoldCableIsolatedBlockEntity> GOLD_CABLE_ISOLATED;
    public static final BlockEntityType<GoldCableIsolatedX2BlockEntity> GOLD_CABLE_ISOLATED_X2;

    public static final BlockEntityType<GlassFibreCableBlockEntity> GLASS_FIBRE_CABLE;

    static {
        COPPER_CABLE = register(Blocks.COPPER_CABLE, CopperCableBlockEntity::new);
        COPPER_CABLE_ISOLATED = register(Blocks.COPPER_CABLE_ISOLATED, CopperCableIsolatedBlockEntity::new);

        TIN_CABLE = register(Blocks.TIN_CABLE, TinCableBlockEntity::new);
        TIN_CABLE_ISOLATED = register(Blocks.TIN_CABLE_ISOLATED, TinCableIsolatedBlockEntity::new);

        GOLD_CABLE = register(Blocks.GOLD_CABLE, GoldCableBlockEntity::new);
        GOLD_CABLE_ISOLATED = register(Blocks.GOLD_CABLE_ISOLATED, GoldCableIsolatedBlockEntity::new);
        GOLD_CABLE_ISOLATED_X2 = register(Blocks.GOLD_CABLE_ISOLATED_X2, GoldCableIsolatedX2BlockEntity::new);

        GLASS_FIBRE_CABLE = register(Blocks.GLASS_FIBRE_CABLE, GlassFibreCableBlockEntity::new);
    }
}
