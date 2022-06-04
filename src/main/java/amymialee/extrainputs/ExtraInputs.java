package amymialee.extrainputs;

import amymialee.extrainputs.input.InputTestItem;
import amymialee.extrainputs.input.UsableAttack;
import amymialee.extrainputs.input.UsableExtra;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ExtraInputs implements ModInitializer {
    public static final String MOD_ID = "extrainputs";
    public static final Identifier KEYBINDING1 = id("keybinding1");
    public static final Identifier KEYBINDING2 = id("keybinding2");
    public static final Identifier KEYBINDING3 = id("keybinding3");
    public static final Identifier KEYBINDING4 = id("keybinding4");
    public static final Identifier KEYBINDING5 = id("keybinding5");
    public static final Identifier KEYBINDING6 = id("keybinding6");

    public static final Identifier KEYBINDING_ATTACK = id("attack");
    public static final Identifier KEYBINDING_USE = id("use");
    public static final Identifier KEYBINDING_PICK = id("pick");

    public static final Identifier ITEM_ATTACK = id("item_attack");
    public static final Identifier ITEM_EXTRA = id("item_extra");
    public static final Item INPUT_TEST_ITEM = Registry.register(Registry.ITEM, id("input_item"), new InputTestItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));

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

        ServerPlayNetworking.registerGlobalReceiver(ITEM_ATTACK, (server, playerEntity, handler, packetByteBuf, sender) -> server.execute(() -> {
            ItemStack stack = playerEntity.getMainHandStack();
            if (stack.getItem() instanceof UsableAttack attack) {
                attack.attackableUse(playerEntity.world, playerEntity, Hand.MAIN_HAND);
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(ITEM_EXTRA, (server, playerEntity, handler, packetByteBuf, sender) -> server.execute(() -> {
            ItemStack stack = playerEntity.getMainHandStack();
            if (stack.getItem() instanceof UsableExtra extra) {
                extra.extraUse(playerEntity.world, playerEntity, Hand.MAIN_HAND);
            }
        }));
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }
}
