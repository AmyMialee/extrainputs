package amymialee.extrainputs.client;

import amymialee.extrainputs.ExtraInputs;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ExtraInputsClient implements ClientModInitializer {
    private static final KeyBinding keyBinding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MODID + ".input1",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category." + ExtraInputs.MODID + ".inputs"
    ));
    private static final KeyBinding keyBinding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MODID + ".input2",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category." + ExtraInputs.MODID + ".inputs"
    ));
    private static final KeyBinding keyBinding3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MODID + ".input3",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category." + ExtraInputs.MODID + ".inputs"
    ));
    private static final KeyBinding keyBinding4 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MODID + ".input4",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category." + ExtraInputs.MODID + ".inputs"
    ));
    private static final KeyBinding keyBinding5 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MODID + ".input5",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category." + ExtraInputs.MODID + ".inputs"
    ));
    private static final KeyBinding keyBinding6 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ExtraInputs.MODID + ".input6",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category." + ExtraInputs.MODID + ".inputs"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding1.wasPressed()) {
                ClientPlayNetworking.send(ExtraInputs.KEYBINDING1, PacketByteBufs.empty());
            }
            if (keyBinding2.wasPressed()) {
                ClientPlayNetworking.send(ExtraInputs.KEYBINDING2, PacketByteBufs.empty());
            }
            if (keyBinding3.wasPressed()) {
                ClientPlayNetworking.send(ExtraInputs.KEYBINDING3, PacketByteBufs.empty());
            }
            if (keyBinding4.wasPressed()) {
                ClientPlayNetworking.send(ExtraInputs.KEYBINDING4, PacketByteBufs.empty());
            }
            if (keyBinding5.wasPressed()) {
                ClientPlayNetworking.send(ExtraInputs.KEYBINDING5, PacketByteBufs.empty());
            }
            if (keyBinding6.wasPressed()) {
                ClientPlayNetworking.send(ExtraInputs.KEYBINDING6, PacketByteBufs.empty());
            }
        });
    }
}
