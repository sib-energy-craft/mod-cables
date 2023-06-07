package com.github.sib_energy_craft.cables.block.gold.entity;

import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.block.gold.GoldCableIsolatedBlock;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.3
 * @author sibmaks
 */
public class GoldCableIsolatedBlockEntity extends AbstractCableBlockEntity<GoldCableIsolatedBlock> {
    public GoldCableIsolatedBlockEntity(@NotNull BlockPos pos,
                                        @NotNull BlockState state,
                                        @NotNull GoldCableIsolatedBlock cableBlock) {
        super(Entities.GOLD_CABLE_ISOLATED, pos, state, cableBlock);
    }
}
