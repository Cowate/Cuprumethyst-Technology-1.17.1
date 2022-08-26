package com.cowate.cuprumethyst.Entity.Projectile;


import com.cowate.cuprumethyst.Data.Server.misc.ModDamageSource;
import com.cowate.cuprumethyst.Initailize.ModEntityType;
import com.cowate.cuprumethyst.Item.SimpleItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.Arrays;

public class Pebble extends Projectile implements ItemSupplier {
    private static final double PEBBLE_BASE_DAMAGE = 5.0D;
    private static final EntityDataAccessor<Boolean> HEFTY_EFFECT = SynchedEntityData.defineId(Pebble.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> PEBBLE_TYPE = SynchedEntityData.defineId(Pebble.class, EntityDataSerializers.BYTE);
    private double baseDamage = 5.0D;

    public static Pebble.Type testPebbleType(ItemStack itemStack) {
        if (itemStack.isEmpty()) return Type.EMPTY;
        if (itemStack.is(SimpleItems.PEBBLE_STONE.get())) {
            return Type.STONE;
        }
        if (itemStack.is(SimpleItems.PEBBLE_BLACKSTONE.get())) {
            return Type.BLACKSTONE;
        }
        return Type.EMPTY;
    }

    public Pebble(EntityType<? extends Pebble> entityType, Level level) {
        super(entityType, level);
    }

    protected Pebble(EntityType<? extends Pebble> entityType, double x, double y, double z, Level level){
        this(entityType, level);
        this.setPos(x, y, z);
    }

    protected Pebble(EntityType<? extends Pebble> entityType, LivingEntity livingEntity, Level level) {
        this(entityType, livingEntity.getX(), livingEntity.getEyeY() - (double)0.1F , livingEntity.getZ(), level);
        this.setOwner(livingEntity);
    }

    public Pebble(Level level, LivingEntity livingEntity) {
        this(ModEntityType.PEBBLE.get(), livingEntity, level);
    }

    // public Sound Event

    protected void defineSynchedData() {
        this.entityData.define(PEBBLE_TYPE, (byte)0);
        this.entityData.define(HEFTY_EFFECT, false);
    }

    public double getBaseDamage() {
        return this.baseDamage;
    }
    public byte getPebbleType() {
        return this.entityData.get(PEBBLE_TYPE);
    }
    public boolean getHeftyEffect() {
        return this.entityData.get(HEFTY_EFFECT);
    }
    public void setHeftyEffect(boolean b) {
        this.entityData.set(HEFTY_EFFECT, b);
    }
    public void setPebbleType(byte b) {
        this.entityData.set(PEBBLE_TYPE, b);
    }
    public void setPebbleType(Type type) {
        if (type == Type.EMPTY){
            setPebbleType((byte)0);
            LOGGER.info("The projectile is not a pebble type!!! It turns out a stone pebble");
        }
        switch (type) {
            case STONE -> setPebbleType((byte)0);
            case BLACKSTONE -> setPebbleType((byte)1);
        }
    }
    public void setBaseDamage(double damage) {
        this.baseDamage = damage;
    }
    public void setDefaultDamage() {
        this.baseDamage = PEBBLE_BASE_DAMAGE;
    }
    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        boolean flag = false;
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult)hitResult).getBlockPos();
            BlockState state = this.level.getBlockState(pos);
            if (state.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(pos);
                flag = true;
            } else if (state.is(Blocks.END_GATEWAY)) {
                BlockEntity entity = this.level.getBlockEntity(pos);
                if (entity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, pos, state, this, (TheEndGatewayBlockEntity)entity);
                }
                flag = true;
            }
        }
        if (hitResult.getType() != HitResult.Type.MISS && !flag && !ForgeEventFactory.onProjectileImpact(this, hitResult)) {
                this.onHit(hitResult);
        }
        this.checkInsideBlocks();
        Vec3 deltaMovement = this.getDeltaMovement();
        double nextX = this.getX() + deltaMovement.x;
        double nextY = this.getY() + deltaMovement.y;
        double nextZ = this.getZ() + deltaMovement.z;
        this.updateRotation();
        float decay;
        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                double delay = 0.25D;
                this.level.addParticle(ParticleTypes.BUBBLE,
                        nextX - delay * deltaMovement.x,
                        nextY - delay * deltaMovement.y,
                        nextZ - delay * deltaMovement.z,
                        nextX, nextY, nextZ);
            }
            decay = 0.8F;
        } else {
            decay = 0.99F;
        }
        this.setDeltaMovement(deltaMovement.scale((double)decay));
        if (!this.isNoGravity()) {
            Vec3 dm1 = this.getDeltaMovement();
            this.setDeltaMovement(dm1.x, dm1.y - (double)this.getGravity(), dm1.z());
        }

        this.setPos(nextX, nextY, nextZ);
    }
    protected float getGravity() {
        return 0.03F;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double limit) {
        double d = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d)) {
            d = 4.0D;
        }
        d = d * 64.0D;
        return limit < d * d;
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity target = hitResult.getEntity();
        Entity owner = this.getOwner();
        DamageSource damageSource;
        if (owner == null) {
            if (getHeftyEffect())
                damageSource = ModDamageSource.hefty_pebble(this, this);
            else
                damageSource = ModDamageSource.pebble(this, this);
        } else {
            if (getHeftyEffect())
                damageSource = ModDamageSource.hefty_pebble(this, owner);
            else
                damageSource = ModDamageSource.pebble(this, owner);
            if (owner instanceof LivingEntity) {
                ((LivingEntity)owner).setLastHurtMob(target);
            }
        }
        boolean flag = target.getType() == EntityType.ENDERMAN;
        if (target.hurt(damageSource, (float)this.baseDamage)) {
            if (flag) return ; //skip EnderMan
            if (target instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)target;
                if (!this.level.isClientSide && owner instanceof ServerPlayer) {
                    ServerPlayer player = (ServerPlayer)owner;
                    CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(player, Arrays.asList(target)); // TODO
                }
            }
        } else {
            this.discard();
        }
    }


    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.discard();
        }
    }

    @Override
    public ItemStack getItem() {
        switch (getPebbleType()) {
            case 1 -> {
                return new ItemStack(SimpleItems.PEBBLE_BLACKSTONE.get());
            }
            default -> {
                return new ItemStack(SimpleItems.PEBBLE_STONE.get());
            }
        }
    }

    public enum Type{
        STONE ,
        BLACKSTONE,
        EMPTY
    }
}
