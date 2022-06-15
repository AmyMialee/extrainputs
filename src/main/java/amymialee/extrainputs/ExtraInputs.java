package amymialee.extrainputs;

import amymialee.extrainputs.input.ExtraCooldowns;
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
    public static final Identifier ITEM_ATTACK = id("item_attack");
    public static final Identifier ITEM_EXTRA = id("item_extra");
    public static final Item INPUT_TEST_ITEM = Registry.register(Registry.ITEM, id("input_item"), new InputTestItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(ITEM_ATTACK, (server, playerEntity, handler, packetByteBuf, sender) -> server.execute(() -> {
            ItemStack stack = playerEntity.getMainHandStack();
            if (stack.getItem() instanceof UsableAttack attack) {
                if (!((ExtraCooldowns) playerEntity).getAttackCooldownManager().isCoolingDown(stack.getItem())) {
                    attack.attackableUse(playerEntity.world, playerEntity, Hand.MAIN_HAND);
                }
            }
        }));
        ServerPlayNetworking.registerGlobalReceiver(ITEM_EXTRA, (server, playerEntity, handler, packetByteBuf, sender) -> server.execute(() -> {
            ItemStack stack = playerEntity.getMainHandStack();
            if (stack.getItem() instanceof UsableExtra extra) {
                if (!((ExtraCooldowns) playerEntity).getExtraCooldownManager().isCoolingDown(stack.getItem())) {
                    extra.extraUse(playerEntity.world, playerEntity, Hand.MAIN_HAND);
                }
            }
        }));
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }
}
