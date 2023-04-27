package com.github.sib_energy_craft.cables.block.entity;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import com.github.sib_energy_craft.energy_api.EnergyOffer;
import com.github.sib_energy_craft.energy_api.cable.EnergyCable;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public abstract class AbstractCableBlockEntity extends BlockEntity implements EnergyCable {
    private final AbstractCableBlock cableBlock;
    private final Set<EnergyOffer> upcomingOffers;
    @Getter
    private volatile EnergyOffer mostValuableOffer;

    public AbstractCableBlockEntity(@NotNull BlockEntityType<? extends AbstractCableBlockEntity> type,
                                    @NotNull BlockPos pos,
                                    @NotNull BlockState state,
                                    @NotNull AbstractCableBlock cableBlock) {
        super(type, pos, state);
        this.cableBlock = cableBlock;
        this.upcomingOffers = new ConcurrentSkipListSet<>();
    }

    public static void tick(@NotNull World world,
                            @NotNull BlockPos pos,
                            @NotNull BlockState state,
                            @NotNull AbstractCableBlockEntity blockEntity) {
        if (world.isClient) {
            return;
        }
        blockEntity.tick(blockEntity);
        AbstractCableBlockEntity.markDirty(world, pos, state);
    }

    @Override
    public @NotNull BigDecimal getResistance() {
        return cableBlock.getResistance();
    }

    @Override
    public void receiveOffer(@NotNull EnergyOffer energyOffer) {
        upcomingOffers.add(energyOffer);
    }

    @Override
    public @NotNull Set<EnergyOffer> retrieveUpcomingOffers() {
        var upcomingOffers = new HashSet<>(this.upcomingOffers);
        this.upcomingOffers.removeAll(upcomingOffers);
        mostValuableOffer = upcomingOffers.stream().max(EnergyOffer::compareTo).orElse(null);
        return upcomingOffers;
    }

    @Override
    public @NotNull EnergyLevel getEnergyLevel() {
        return cableBlock.getEnergyLevel();
    }
}
