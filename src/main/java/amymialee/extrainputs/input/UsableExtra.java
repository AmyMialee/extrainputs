package amymialee.extrainputs.input;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface UsableExtra {
    void extraUse(World world, PlayerEntity user, Hand hand);
}
