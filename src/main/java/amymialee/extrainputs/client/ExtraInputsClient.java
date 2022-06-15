package amymialee.extrainputs.client;

import amymialee.extrainputs.ExtraInputs;
import amymialee.extrainputs.input.ExtraCooldowns;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ExtraInputsClient implements ClientModInitializer {
    private static final KeyBinding itemExtra = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MOD_ID + ".item_extra",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category." + ExtraInputs.MOD_ID + ".inputs"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (itemExtra.wasPressed()) {
                if (client.player != null) {
                    ItemStack stack = client.player.getStackInHand(Hand.MAIN_HAND);
                    if (!((ExtraCooldowns) client.player).getExtraCooldownManager().isCoolingDown(stack.getItem())) {
                        ClientPlayNetworking.send(ExtraInputs.ITEM_EXTRA, PacketByteBufs.empty());
                    }
                }
            }
        });
    }
}
