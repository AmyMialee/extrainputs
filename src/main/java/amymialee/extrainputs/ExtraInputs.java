package amymialee.extrainputs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExtraInputs implements ModInitializer {
    public static final String MODID = "extrainputs";
    public static final Identifier KEYBINDING1 = new Identifier(MODID, "keybinding1");
    public static final Identifier KEYBINDING2 = new Identifier(MODID, "keybinding2");
    public static final Identifier KEYBINDING3 = new Identifier(MODID, "keybinding3");
    public static final Identifier KEYBINDING4 = new Identifier(MODID, "keybinding4");
    public static final Identifier KEYBINDING5 = new Identifier(MODID, "keybinding5");
    public static final Identifier KEYBINDING6 = new Identifier(MODID, "keybinding6");

    @Override
    public void onInitialize() {
        Registry.register(Registry.CUSTOM_STAT, "keybinding1", KEYBINDING1);
        Registry.register(Registry.CUSTOM_STAT, "keybinding2", KEYBINDING2);
        Registry.register(Registry.CUSTOM_STAT, "keybinding3", KEYBINDING3);
        Registry.register(Registry.CUSTOM_STAT, "keybinding4", KEYBINDING4);
        Registry.register(Registry.CUSTOM_STAT, "keybinding5", KEYBINDING5);
        Registry.register(Registry.CUSTOM_STAT, "keybinding6", KEYBINDING6);

        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING1, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING1));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING2, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING2));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING3, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING3));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING4, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING4));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING5, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING5));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING6, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING6));
    }
}
