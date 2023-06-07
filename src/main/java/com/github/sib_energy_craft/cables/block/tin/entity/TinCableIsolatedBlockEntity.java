package com.github.sib_energy_craft.cables.block.tin.entity;

import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.block.tin.TinCableIsolatedBlock;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class TinCableIsolatedBlockEntity extends AbstractCableBlockEntity<TinCableIsolatedBlock> {
    public TinCableIsolatedBlockEntity(@NotNull BlockPos pos,
                                       @NotNull BlockState state,
                                       @NotNull TinCableIsolatedBlock cableBlock) {
        super(Entities.TIN_CABLE_ISOLATED, pos, state, cableBlock);
    }
}
