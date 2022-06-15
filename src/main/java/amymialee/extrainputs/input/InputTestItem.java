package amymialee.extrainputs.input;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class InputTestItem extends Item implements UsableAttack, UsableExtra {
    public InputTestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> attackableUse(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!((ExtraCooldowns) user).getAttackCooldownManager().isCoolingDown(this)) {
            shoot(world, user, itemStack, new ItemStack(Items.SPECTRAL_ARROW), 2, 0, 0.0F);
            ((ExtraCooldowns) user).getAttackCooldownManager().set(this, 4);
        }
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public boolean attackableTicksHeld(ItemStack stack) {
        return isFast(stack);
    }

    @Override
    public void extraUse(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        setFast(stack, !isFast(stack));
    }

    public static boolean isFast(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean("fast");
    }

    public static void setFast(ItemStack stack, boolean fast) {
        stack.getOrCreateNbt().putBoolean("fast", fast);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        shootAll(world, user, itemStack, new ItemStack(Items.ARROW), 5, 0.0F);
        return TypedActionResult.consume(itemStack);
    }

    public static void shootAll(World world, LivingEntity entity, ItemStack stack, ItemStack itemStack, float speed, float divergence) {
        if (!itemStack.isEmpty()) {
            shoot(world, entity, stack, itemStack, speed, divergence, 0.0F);
            shoot(world, entity, stack, itemStack, speed, divergence, -2.0F);
            shoot(world, entity, stack, itemStack, speed, divergence, 2.0F);
        }
    }

    private static void shoot(World world, LivingEntity shooter, ItemStack crossbow, ItemStack projectile, float speed, float divergence, float simulated) {
        if (!world.isClient) {
            PersistentProjectileEntity projectileEntity;
            projectileEntity = createArrow(world, shooter, crossbow, projectile);
            projectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            if (shooter instanceof CrossbowUser crossbowUser) {
                crossbowUser.shoot(crossbowUser.getTarget(), crossbow, projectileEntity, simulated);
            } else {
                Vec3d crossbowUser = shooter.getOppositeRotationVector(1.0F);
                Quaternion quaternion = new Quaternion(new Vec3f(crossbowUser), simulated, true);
                Vec3d vec3d = shooter.getRotationVec(1.0F);
                Vec3f vec3f = new Vec3f(vec3d);
                vec3f.rotate(quaternion);
                ((ProjectileEntity)projectileEntity).setVelocity(vec3f.getX(), vec3f.getY(), vec3f.getZ(), speed, divergence);
            }
            world.spawnEntity(projectileEntity);
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1);
        }
    }

    private static PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
        ArrowItem arrowItem = (ArrowItem)(arrow.getItem() instanceof ArrowItem ? arrow.getItem() : Items.ARROW);
        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, arrow, entity);
        if (entity instanceof PlayerEntity) {
            persistentProjectileEntity.setCritical(true);
        }
        persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        persistentProjectileEntity.setShotFromCrossbow(true);
        int i = EnchantmentHelper.getLevel(Enchantments.PIERCING, crossbow);
        if (i > 0) {
            persistentProjectileEntity.setPierceLevel((byte)i);
        }
        return persistentProjectileEntity;
    }
}
