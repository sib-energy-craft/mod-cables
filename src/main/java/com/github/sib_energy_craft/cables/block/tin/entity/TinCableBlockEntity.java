package com.github.sib_energy_craft.cables.block.tin.entity;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class TinCableBlockEntity extends AbstractCableBlockEntity {
    public TinCableBlockEntity(@NotNull BlockPos pos,
                               @NotNull BlockState state,
                               @NotNull AbstractCableBlock cableBlock) {
        super(Entities.TIN_CABLE, pos, state, cableBlock);
    }
}
