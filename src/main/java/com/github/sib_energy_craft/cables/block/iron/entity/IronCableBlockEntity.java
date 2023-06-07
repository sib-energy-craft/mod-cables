package com.github.sib_energy_craft.cables.block.iron.entity;

import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.block.iron.IronCableBlock;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.8
 * @author sibmaks
 */
public class IronCableBlockEntity extends AbstractCableBlockEntity<IronCableBlock> {
    public IronCableBlockEntity(@NotNull BlockPos pos,
                                @NotNull BlockState state,
                                @NotNull IronCableBlock cableBlock) {
        super(Entities.IRON_CABLE, pos, state, cableBlock);
    }
}
