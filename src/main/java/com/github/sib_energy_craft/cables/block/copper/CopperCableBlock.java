package com.github.sib_energy_craft.cables.block.copper;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.cables.block.IsolationType;
import com.github.sib_energy_craft.cables.block.copper.entity.CopperCableBlockEntity;
import com.github.sib_energy_craft.cables.constants.Constants;
import com.github.sib_energy_craft.cables.load.Entities;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class CopperCableBlock extends AbstractCableBlock {

    public CopperCableBlock(@NotNull IsolationType isolationType,
                            @NotNull AbstractBlock.Settings settings) {
        super(isolationType, settings, EnergyLevel.L2, Constants.LOSS_02, () -> Entities.COPPER_CABLE);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CopperCableBlockEntity(pos, state, this);
    }
}
