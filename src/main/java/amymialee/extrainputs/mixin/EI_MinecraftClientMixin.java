package amymialee.extrainputs.mixin;

import amymialee.extrainputs.ExtraInputs;
import amymialee.extrainputs.input.UsableAttack;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class EI_MinecraftClientMixin {
    @Shadow @Nullable public ClientPlayerInteractionManager interactionManager;

    @Shadow protected abstract boolean doAttack();

    @Inject(method = "handleInputEvents()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;handleBlockBreaking(Z)V", shift = At.Shift.BEFORE), cancellable = true)
    private void ExtraInputs$AttackableHeld(CallbackInfo ci) {
        MinecraftClient minecraft = MinecraftClient.getInstance();

        if (minecraft.player != null) {
            PlayerEntity player = minecraft.player;
            ItemStack stack = player.getMainHandStack();

            if (stack.getItem() instanceof UsableAttack usableAttack) {
                if (!minecraft.options.attackKey.isPressed()) {
                    return;
                }
                ci.cancel();
                if (usableAttack.attackableTicksHeld()) {
                    if (usableAttack.attackableUse(player.world, player, Hand.MAIN_HAND).getResult() == ActionResult.PASS) {
                        if (interactionManager != null) {
                            interactionManager.cancelBlockBreaking();
                        }
                    }
                    ClientPlayNetworking.send(ExtraInputs.ITEM_ATTACK, new PacketByteBuf(Unpooled.buffer()));
                }
            }
        }
    }

    @Inject(method = "handleInputEvents()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z", shift = At.Shift.BEFORE, ordinal = 0))
    private void ExtraInputs$AttackableUsing(CallbackInfo ci) {
        MinecraftClient minecraft = MinecraftClient.getInstance();

        if (minecraft.player != null) {
            PlayerEntity player = minecraft.player;
            ItemStack stack = player.getMainHandStack();

            if (stack.getItem() instanceof UsableAttack usableAttack) {
                if (usableAttack.attackableTicksHeld() && player.isUsingItem()) {
                    while (minecraft.options.attackKey.wasPressed()) {
                        this.doAttack();
                    }
                }
            }
        }
    }


    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void ExtraInputs$DoAttackUse(CallbackInfoReturnable<Boolean> cir) {
        ClientPlayNetworking.send(ExtraInputs.ITEM_ATTACK, PacketByteBufs.empty());
        MinecraftClient minecraft = MinecraftClient.getInstance();

        if (minecraft.player != null) {
            PlayerEntity player = minecraft.player;
            ItemStack stack = player.getMainHandStack();

            if (stack.getItem() instanceof UsableAttack usableAttack) {
                usableAttack.attackableUse(player.world, player, Hand.MAIN_HAND);
                ClientPlayNetworking.send(ExtraInputs.ITEM_ATTACK, PacketByteBufs.empty());
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "doItemUse", at = @At("HEAD"))
    private void ExtraInputs$TrackUse(CallbackInfo ci) {
        ClientPlayNetworking.send(ExtraInputs.KEYBINDING_USE, PacketByteBufs.empty());
    }

    @Inject(method = "doItemPick", at = @At("HEAD"))
    private void ExtraInputs$TrackPick(CallbackInfo ci) {
        ClientPlayNetworking.send(ExtraInputs.KEYBINDING_PICK, PacketByteBufs.empty());
    }
}
