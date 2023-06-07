package com.github.sib_energy_craft.cables.block.iron.entity;

import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.block.iron.IronCableIsolatedX2Block;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.8
 * @author sibmaks
 */
public class IronCableIsolatedX2BlockEntity extends AbstractCableBlockEntity<IronCableIsolatedX2Block> {
    public IronCableIsolatedX2BlockEntity(@NotNull BlockPos pos,
                                          @NotNull BlockState state,
                                          @NotNull IronCableIsolatedX2Block cableBlock) {
        super(Entities.IRON_CABLE_ISOLATED_X2, pos, state, cableBlock);
    }
}
