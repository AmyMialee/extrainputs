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

    public static final Identifier KEYBINDING_ATTACK = new Identifier(MODID, "attack");
    public static final Identifier KEYBINDING_USE = new Identifier(MODID, "use");
    public static final Identifier KEYBINDING_PICK = new Identifier(MODID, "pick");

    @Override
    public void onInitialize() {
        Registry.register(Registry.CUSTOM_STAT, "ei_keybinding1", KEYBINDING1);
        Registry.register(Registry.CUSTOM_STAT, "ei_keybinding2", KEYBINDING2);
        Registry.register(Registry.CUSTOM_STAT, "ei_keybinding3", KEYBINDING3);
        Registry.register(Registry.CUSTOM_STAT, "ei_keybinding4", KEYBINDING4);
        Registry.register(Registry.CUSTOM_STAT, "ei_keybinding5", KEYBINDING5);
        Registry.register(Registry.CUSTOM_STAT, "ei_keybinding6", KEYBINDING6);

        Registry.register(Registry.CUSTOM_STAT, "ei_attack", KEYBINDING_ATTACK);
        Registry.register(Registry.CUSTOM_STAT, "ei_use", KEYBINDING_USE);
        Registry.register(Registry.CUSTOM_STAT, "ei_pick", KEYBINDING_PICK);

        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING1, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING1));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING2, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING2));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING3, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING3));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING4, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING4));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING5, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING5));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING6, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING6));

        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING_ATTACK, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING_ATTACK));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING_USE, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING_USE));
        ServerPlayNetworking.registerGlobalReceiver(KEYBINDING_PICK, (server, playerEntity, playNetworkHandler, packetByteBuf, packetSender) -> playerEntity.incrementStat(KEYBINDING_PICK));
    }
}
