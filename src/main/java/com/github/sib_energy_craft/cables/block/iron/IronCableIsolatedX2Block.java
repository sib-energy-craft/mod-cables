package com.github.sib_energy_craft.cables.block.iron;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.cables.block.IsolationType;
import com.github.sib_energy_craft.cables.block.iron.entity.IronCableIsolatedX2BlockEntity;
import com.github.sib_energy_craft.cables.constants.Constants;
import com.github.sib_energy_craft.cables.load.Entities;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @since 0.0.8
 * @author sibmaks
 */
public class IronCableIsolatedX2Block extends AbstractCableBlock {

    public IronCableIsolatedX2Block(@NotNull IsolationType isolationType,
                                    @NotNull Settings settings) {
        super(isolationType, settings, EnergyLevel.L4, Constants.LOSS_05, () -> Entities.IRON_CABLE_ISOLATED_X2);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new IronCableIsolatedX2BlockEntity(pos, state,this);
    }
}
