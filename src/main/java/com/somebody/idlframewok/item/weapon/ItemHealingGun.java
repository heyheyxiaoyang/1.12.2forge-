package com.somebody.idlframewok.item.weapon;

import com.somebody.idlframewok.item.IWIP;
import com.somebody.idlframewok.item.ItemAdaptingBase;
import com.somebody.idlframewok.util.EntityUtil;
import com.somebody.idlframewok.util.IDLGeneral;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static com.somebody.idlframewok.util.CommonDef.METER_PER_BLOCK;

public class ItemHealingGun extends ItemAdaptingBase implements IWIP {
    public ItemHealingGun(String name) {
        super(name);
        setRangedWeapon();
        useable = true;

        cool_down = 10f;
        basic_val = 6f;
        base_range = 2.5f * METER_PER_BLOCK;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 512;
    }

    public void onCreatureStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        //Idealland.Log("onCreatureStoppedUsing");
        Vec3d basePos = entityLiving.getPositionVector();
        float range = getRange(stack);
        List<EntityLivingBase> entities = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, IDLGeneral.ServerAABB(basePos.addVector(-range, -range, -range), basePos.addVector(range, range, range)));
        //Idealland.Log("found %d targets", entities.size());
        for (EntityLivingBase target: entities
        ) {
            if (EntityUtil.getAttitude(entityLiving, target) == EntityUtil.EnumAttitude.FRIEND)
            {
                if (worldIn.isRemote)
                {
                    EntityUtil.SpawnParticleAround(target, EnumParticleTypes.VILLAGER_HAPPY, 10);
                }else {
                    target.heal(getVal(stack));
                }
            }
        }

        if (worldIn.isRemote)
        {
            //Idealland.Log("spawn particle!");
            spawnParticles(EnumParticleTypes.VILLAGER_HAPPY, worldIn, entityLiving);
        }

        entityLiving.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 1f, 0.8f);
    }

    void spawnParticles(EnumParticleTypes particleType, World worldIn, EntityLivingBase center)
    {
        int count = 60;
        float speedMagnitude = 1000f;
        float maxAngle = 6.282f;
        float deltaAngle = maxAngle/count;

        for (float angle = 0f; angle < maxAngle; angle += deltaAngle)
        {
           // float radius = center.getRNG().nextFloat() * speedMagnitude;
            //Idealland.Log("spawn particle at %s, %s, %s", center.posX, center.posY, center.posZ);
            center.world.spawnParticle(particleType, center.posX+Math.cos(angle),
                    center.getPositionEyes(0f).y,
                    center.posZ+Math.sin(angle),
                    Math.cos(angle) * speedMagnitude,0,Math.sin(angle) * speedMagnitude);
        }


//        Vec3d velocityShoot = Vec3d.ZERO;
//        Vec3d velocitySide = Vec3d.ZERO;
//
//        float theta = 0f;
//        float accel = 0f;
//
//        while(true)
//        {
//            //update
//            Vec3d speed = velocityShoot.scale(Math.cos(theta)).add(velocitySide.scale(Math.sin(theta)));
//            theta += accel;
//        }
    }

}
