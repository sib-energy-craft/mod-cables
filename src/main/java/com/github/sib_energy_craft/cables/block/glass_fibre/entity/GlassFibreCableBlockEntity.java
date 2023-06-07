package com.github.sib_energy_craft.cables.block.glass_fibre.entity;

import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.cables.block.glass_fibre.GlassFibreCableBlock;
import com.github.sib_energy_craft.cables.load.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class GlassFibreCableBlockEntity extends AbstractCableBlockEntity<GlassFibreCableBlock> {
    public GlassFibreCableBlockEntity(@NotNull BlockPos pos,
                                      @NotNull BlockState state,
                                      @NotNull GlassFibreCableBlock block) {
        super(Entities.GLASS_FIBRE_CABLE, pos, state, block);
    }
}
