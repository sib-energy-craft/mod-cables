package com.github.sib_energy_craft.cables.block.entity;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import com.github.sib_energy_craft.energy_api.EnergyLevel;
import com.github.sib_energy_craft.energy_api.EnergyOffer;
import com.github.sib_energy_craft.energy_api.cable.EnergyCable;
import com.github.sib_energy_craft.energy_api.supplier.EnergySupplier;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public abstract class AbstractCableBlockEntity<T extends AbstractCableBlock> extends BlockEntity
        implements EnergyCable {
    private final T cableBlock;
    private final Map<EnergySupplier, EnergyOffer> upcomingOffers;
    @Getter
    private volatile EnergyOffer mostValuableOffer;

    public AbstractCableBlockEntity(@NotNull BlockEntityType<? extends AbstractCableBlockEntity> type,
                                    @NotNull BlockPos pos,
                                    @NotNull BlockState state,
                                    @NotNull T cableBlock) {
        super(type, pos, state);
        this.cableBlock = cableBlock;
        this.upcomingOffers = new ConcurrentHashMap<>();
    }

    public static void tick(@NotNull World world,
                            @NotNull BlockPos pos,
                            @NotNull BlockState state,
                            @NotNull AbstractCableBlockEntity<?> blockEntity) {
        if(world.isClient || !(world instanceof ServerWorld serverWorld)) {
            return;
        }
        blockEntity.tick(serverWorld, blockEntity);
        markDirty(world, pos, state);
    }

    @Override
    public @NotNull BigDecimal getResistance() {
        return cableBlock.getResistance();
    }

    @Override
    public void receiveOffer(@NotNull EnergyOffer energyOffer) {
        upcomingOffers.compute(energyOffer.getSource(), (key, val) -> {
            if(val == null) {
                return energyOffer;
            }
           return energyOffer.compareTo(val) > 0 ? energyOffer : val;
        });
    }

    @Override
    public @NotNull Map<EnergySupplier, EnergyOffer> retrieveUpcomingOffers() {
        var upcomingOffers = new HashMap<>(this.upcomingOffers);
        for (var entry : upcomingOffers.entrySet()) {
            this.upcomingOffers.remove(entry.getKey(), entry.getValue());
        }
        mostValuableOffer = upcomingOffers.values()
                .stream()
                .max(EnergyOffer::compareTo)
                .orElse(null);
        return upcomingOffers;
    }

    @Override
    public @NotNull EnergyLevel getEnergyLevel() {
        return cableBlock.getEnergyLevel();
    }
}
