package com.github.sib_energy_craft.cables.block.gold;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.cables.block.IsolationType;
import com.github.sib_energy_craft.cables.block.gold.entity.GoldCableIsolatedX2BlockEntity;
import com.github.sib_energy_craft.cables.constants.Constants;
import com.github.sib_energy_craft.cables.load.Entities;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @since 0.0.3
 * @author sibmaks
 */
public class GoldCableIsolatedX2Block extends AbstractCableBlock {

    public GoldCableIsolatedX2Block(@NotNull IsolationType isolationType,
                                    @NotNull Settings settings) {
        super(isolationType, settings, EnergyLevel.L3, Constants.LOSS_02, () -> Entities.GOLD_CABLE_ISOLATED_X2);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GoldCableIsolatedX2BlockEntity(pos, state,this);
    }
}
