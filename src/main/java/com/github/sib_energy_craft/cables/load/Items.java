package com.github.sib_energy_craft.cables.load;

import com.github.sib_energy_craft.cables.item.CableBlockItem;
import com.github.sib_energy_craft.sec_utils.load.ModRegistrar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

import static com.github.sib_energy_craft.sec_utils.utils.ItemUtils.register;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public final class Items implements ModRegistrar {
    public static final CableBlockItem COPPER_CABLE;
    public static final CableBlockItem COPPER_CABLE_ISOLATED;

    public static final CableBlockItem TIN_CABLE;
    public static final CableBlockItem TIN_CABLE_ISOLATED;

    public static final CableBlockItem GOLD_CABLE;
    public static final CableBlockItem GOLD_CABLE_ISOLATED;
    public static final CableBlockItem GOLD_CABLE_ISOLATED_X2;

    public static final CableBlockItem GLASS_FIBRE_CABLE;

    static {
        var emptySettings = new Item.Settings();

        COPPER_CABLE = register(ItemGroups.FUNCTIONAL,
                Blocks.COPPER_CABLE,
                it -> new CableBlockItem(it, emptySettings));

        COPPER_CABLE_ISOLATED = register(ItemGroups.FUNCTIONAL,
                Blocks.COPPER_CABLE_ISOLATED,
                it -> new CableBlockItem(it, emptySettings));

        TIN_CABLE = register(ItemGroups.FUNCTIONAL,
                Blocks.TIN_CABLE,
                it -> new CableBlockItem(it, emptySettings));

        TIN_CABLE_ISOLATED = register(ItemGroups.FUNCTIONAL,
                Blocks.TIN_CABLE_ISOLATED,
                it -> new CableBlockItem(it, emptySettings));

        GOLD_CABLE = register(ItemGroups.FUNCTIONAL,
                Blocks.GOLD_CABLE,
                it -> new CableBlockItem(it, emptySettings));

        GOLD_CABLE_ISOLATED = register(ItemGroups.FUNCTIONAL,
                Blocks.GOLD_CABLE_ISOLATED,
                it -> new CableBlockItem(it, emptySettings));

        GOLD_CABLE_ISOLATED_X2 = register(ItemGroups.FUNCTIONAL,
                Blocks.GOLD_CABLE_ISOLATED_X2,
                it -> new CableBlockItem(it, emptySettings));

        GLASS_FIBRE_CABLE = register(ItemGroups.FUNCTIONAL,
                Blocks.GLASS_FIBRE_CABLE,
                it -> new CableBlockItem(it, emptySettings));
    }
}
