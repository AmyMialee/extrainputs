package amymialee.extrainputs.input;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface UsableAttack {
    TypedActionResult<ItemStack> attackableUse(World world, PlayerEntity user, Hand hand);

    default boolean attackableTicksHeld() {
        return false;
    }
}
