package amymialee.extrainputs.mixin;

import amymialee.extrainputs.ExtraInputs;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class EI_MinecraftClientMixin {
    @Inject(method = "doAttack", at = @At("HEAD"))
    private void doAttack(CallbackInfo ci) {
        ClientPlayNetworking.send(ExtraInputs.KEYBINDING_ATTACK, PacketByteBufs.empty());
    }

    @Inject(method = "doItemUse", at = @At("HEAD"))
    private void doItemUse(CallbackInfo ci) {
        ClientPlayNetworking.send(ExtraInputs.KEYBINDING_USE, PacketByteBufs.empty());
    }

    @Inject(method = "doItemPick", at = @At("HEAD"))
    private void doItemPick(CallbackInfo ci) {
        ClientPlayNetworking.send(ExtraInputs.KEYBINDING_PICK, PacketByteBufs.empty());
    }
}
