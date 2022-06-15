package amymialee.extrainputs.mixin;

import amymialee.extrainputs.input.ExtraCooldowns;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class EI_PlayerEntityMixin implements ExtraCooldowns {
    @Unique
    private final ItemCooldownManager attackCooldownManager = new ItemCooldownManager();

    @Unique
    private final ItemCooldownManager extraCooldownManager = new ItemCooldownManager();

    @Override
    public ItemCooldownManager getAttackCooldownManager() {
        return attackCooldownManager;
    }

    @Override
    public ItemCooldownManager getExtraCooldownManager() {
        return extraCooldownManager;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        attackCooldownManager.update();
        extraCooldownManager.update();
    }
}