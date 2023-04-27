package com.github.sib_energy_craft.cables.item;

import com.github.sib_energy_craft.cables.block.AbstractCableBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class CableBlockItem extends BlockItem {

    public CableBlockItem(@NotNull AbstractCableBlock block,
                          @NotNull Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(@NotNull ItemStack stack,
                              @Nullable World world,
                              @NotNull List<Text> tooltip,
                              @NotNull TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        var cableBlock = (AbstractCableBlock) getBlock();
        var energyLevel = cableBlock.getEnergyLevel();
        var resistance = cableBlock.getResistance();
        tooltip.add(Text.translatable("attribute.name.sib_energy_craft.max_input_eu", energyLevel.to)
                .setStyle(Style.EMPTY.withColor(Color.GRAY.getRGB())));
        tooltip.add(Text.translatable("attribute.name.sib_energy_craft.energy_loss", resistance)
                .setStyle(Style.EMPTY.withColor(Color.GRAY.getRGB())));
    }
}
