package com.github.sib_energy_craft.cables.block.iron.entity;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.8
 * @author sibmaks
 */
public class IronCableIsolatedX2BlockEntity extends AbstractCableBlockEntity {
    public IronCableIsolatedX2BlockEntity(@NotNull BlockPos pos,
                                          @NotNull BlockState state,
                                          @NotNull AbstractCableBlock cableBlock) {
        super(Entities.IRON_CABLE_ISOLATED_X2, pos, state, cableBlock);
    }
}
