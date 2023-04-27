package com.github.sib_energy_craft.cables.block.copper.entity;

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
public class CopperCableBlockEntity extends AbstractCableBlockEntity {
    public CopperCableBlockEntity(@NotNull BlockPos pos,
                                  @NotNull BlockState state,
                                  @NotNull AbstractCableBlock cableBlock) {
        super(Entities.COPPER_CABLE, pos, state, cableBlock);
    }
}
