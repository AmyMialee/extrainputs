package amymialee.extrainputs.input;

import net.minecraft.entity.player.ItemCooldownManager;

public interface ExtraCooldowns {
    ItemCooldownManager getAttackCooldownManager();
    ItemCooldownManager getExtraCooldownManager();
}
