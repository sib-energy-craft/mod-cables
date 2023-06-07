package com.github.sib_energy_craft.cables.block;

import com.github.sib_energy_craft.cables.block.entity.AbstractCableBlockEntity;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import com.github.sib_energy_craft.energy_api.constants.Constants;
import com.github.sib_energy_craft.energy_api.damage.DamageSources;
import com.github.sib_energy_craft.energy_api.tags.CoreTags;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @since 0.0.1
 * @author sibmaks
 */
@Slf4j
public abstract class AbstractCableBlock extends ConnectingBlock implements BlockEntityProvider {
    private static final Map<BooleanProperty, Function<BlockPos, BlockPos>> PLACEMENT_MODIFIERS = Map.of(
            NORTH, BlockPos::north,
            SOUTH, BlockPos::south,
            WEST, BlockPos::west,
            EAST, BlockPos::east,
            UP, BlockPos::up,
            DOWN, BlockPos::down
    );

    @Getter
    private final EnergyLevel energyLevel;
    @Getter
    private final BigDecimal resistance;
    private final IsolationType type;
    private final Supplier<BlockEntityType<? extends AbstractCableBlockEntity<?>>> blockEntityType;

    public AbstractCableBlock(@NotNull IsolationType type,
                              @NotNull Settings settings,
                              @NotNull EnergyLevel energyLevel,
                              @NotNull BigDecimal resistance,
                              @NotNull Supplier<BlockEntityType<? extends AbstractCableBlockEntity<?>>> blockEntityType) {
        super(type.radius, settings);
        this.type = type;
        this.energyLevel = energyLevel;
        this.resistance = resistance;
        this.blockEntityType = blockEntityType;
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    @Override
    public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        var blockView = ctx.getWorld();
        var blockState = this.getDefaultState();
        var blockPos = ctx.getBlockPos();

        for (var entry : PLACEMENT_MODIFIERS.entrySet()) {
            var neighborPos = entry.getValue().apply(blockPos);
            var neighborState = blockView.getBlockState(neighborPos);
            var neighborConnected = connectsTo(neighborState);
            blockState = blockState.with(entry.getKey(), neighborConnected);
        }

        return blockState;
    }

    @Override
    public BlockState getStateForNeighborUpdate(@NotNull BlockState state,
                                                @NotNull Direction direction,
                                                @NotNull BlockState neighborState,
                                                @NotNull WorldAccess world,
                                                @NotNull BlockPos pos,
                                                @NotNull BlockPos neighborPos) {
        return state.with(FACING_PROPERTIES.get(direction), this.connectsTo(neighborState));
    }

    @Override
    public VoxelShape getCameraCollisionShape(@NotNull BlockState state,
                                              @NotNull BlockView world,
                                              @NotNull BlockPos pos,
                                              @NotNull ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean isSideInvisible(@NotNull BlockState state,
                                   @NotNull BlockState stateFrom,
                                   @NotNull Direction direction) {
        if (!stateFrom.isOf(this)) {
            return false;
        }
        return state.get(FACING_PROPERTIES.get(direction)) &&
                stateFrom.get(FACING_PROPERTIES.get(direction.getOpposite()));
    }

    public final boolean connectsTo(@NotNull BlockState state) {
        return !cannotConnect(state) && CoreTags.isEnergyConductor(state);
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, UP, DOWN);
    }

    @NotNull
    @Override
    public BlockState rotate(@NotNull BlockState state, @NotNull BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180 -> {
                return state.with(NORTH, state.get(SOUTH))
                        .with(EAST, state.get(WEST))
                        .with(SOUTH, state.get(NORTH))
                        .with(WEST, state.get(EAST));
            }
            case COUNTERCLOCKWISE_90 -> {
                return state.with(NORTH, state.get(EAST))
                        .with(EAST, state.get(SOUTH))
                        .with(SOUTH, state.get(WEST))
                        .with(WEST, state.get(NORTH));
            }
            case CLOCKWISE_90 -> {
                return state.with(NORTH, state.get(WEST))
                        .with(EAST, state.get(NORTH))
                        .with(SOUTH, state.get(EAST))
                        .with(WEST, state.get(SOUTH));
            }
        }
        return state;
    }

    @NotNull
    @Override
    public BlockState mirror(@NotNull BlockState state, @NotNull BlockMirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT -> {
                return state.with(NORTH, state.get(SOUTH)).with(SOUTH, state.get(NORTH));
            }
            case FRONT_BACK -> {
                return state.with(EAST, state.get(WEST)).with(WEST, state.get(EAST));
            }
        }
        return super.mirror(state, mirror);
    }

    @Override
    public boolean canPathfindThrough(@NotNull BlockState state,
                                      @NotNull BlockView world,
                                      @NotNull BlockPos pos,
                                      @NotNull NavigationType type) {
        return false;
    }

    @Override
    public void onSteppedOn(@NotNull World world,
                            @NotNull BlockPos pos,
                            @NotNull BlockState state,
                            @NotNull Entity entity) {
        if (!type.damage) {
            return;
        }
        var blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof AbstractCableBlockEntity<?> cableBlockEntity)) {
            return;
        }
        var energyOffer = cableBlockEntity.getMostValuableOffer();
        if(energyOffer == null) {
            return;
        }
        var energy = energyOffer.getEnergyAmount();
        final int amount = energy.getAmount().max(Constants.ACCURATE_ONE).intValue();
        if (energyOffer.acceptOffer()) {
            var damageSource = DamageSources.energy(world);
            entity.damage(damageSource, amount % 30);
            return;
        }
        var damageSource = DamageSources.energy(world);
        entity.damage(damageSource, amount % 10);
    }

    /**
     *
     *
     * @param givenType given block entity type
     * @param expectedType block entity type to compare
     * @param ticker ticker
     * @return the ticker if the given type and expected type are the same, or {@code null} if they are different
     * @param <E> type of block entity
     * @param <A> type of given block entity
     */
    @Nullable
    protected static <E extends BlockEntity,
            A extends BlockEntity> BlockEntityTicker<A> checkType(@NotNull BlockEntityType<A> givenType,
                                                                  @NotNull BlockEntityType<E> expectedType,
                                                                  @NotNull BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Nullable
    @Override
    public <E extends BlockEntity> BlockEntityTicker<E> getTicker(@NotNull World world,
                                                                  @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<E> type) {
        return world.isClient ? null : checkType(type, blockEntityType.get(), AbstractCableBlockEntity::tick);
    }

}
