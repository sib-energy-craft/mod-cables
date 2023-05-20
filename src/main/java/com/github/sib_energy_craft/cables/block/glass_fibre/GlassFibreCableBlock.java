package com.github.sib_energy_craft.cables.block.glass_fibre;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.cables.block.IsolationType;
import com.github.sib_energy_craft.cables.block.glass_fibre.entity.GlassFibreCableBlockEntity;
import com.github.sib_energy_craft.cables.load.Entities;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.github.sib_energy_craft.cables.constants.Constants.LOSS_0025;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class GlassFibreCableBlock extends AbstractCableBlock {

    public GlassFibreCableBlock(@NotNull IsolationType isolationType, @NotNull Settings settings) {
        super(isolationType, settings, EnergyLevel.L5, LOSS_0025, () -> Entities.GLASS_FIBRE_CABLE);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GlassFibreCableBlockEntity(pos, state, this);
    }

    @Override
    public boolean isSideInvisible(@NotNull BlockState state,
                                   @NotNull BlockState stateFrom,
                                   @NotNull Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getCameraCollisionShape(@NotNull BlockState state,
                                              @NotNull BlockView world,
                                              @NotNull BlockPos pos,
                                              @NotNull ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public float getAmbientOcclusionLightLevel(@NotNull BlockState state,
                                               @NotNull BlockView world,
                                               @NotNull BlockPos pos) {
        return 1.0f;
    }


}
