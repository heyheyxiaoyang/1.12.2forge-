package com.somebody.idlframewok.entity.creatures.ideallandTeam;

import com.somebody.idlframewok.entity.creatures.EntityModUnit;
import com.somebody.idlframewok.potion.ModPotions;
import com.somebody.idlframewok.util.IDLGeneral;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

import static com.somebody.idlframewok.util.CommonDef.TICK_PER_SECOND;

public class EntityIDLInterferer extends EntityIdeallandUnitBase {
    public float range_base = 32f;
    public EntityIDLInterferer(World worldIn) {
        super(worldIn);
        setBuilding();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        //this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(range_base);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(range_base);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote)
        {
            if (world.getWorldTime() % TICK_PER_SECOND == 0)
            {
                double range = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
                //Idealland.Log("Casting buff: range = " + range);

                Vec3d basePos = this.getPositionVector();
                List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, IDLGeneral.ServerAABB(basePos.addVector(-range, -range, -range), basePos.addVector(range, range, range)));
                for (EntityLivingBase living: entities
                ) {
                    if (living instanceof EntityModUnit)// && !((EntityCubix)living).is_mechanic)
                    {

                    }else {
                        living.addPotionEffect(new PotionEffect(ModPotions.INTERFERENCE, TICK_PER_SECOND * 5, 0));
                    }
                    //Idealland.Log("Casting buff: tick = " + TICK_PER_SECOND * (int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                }
            }
        }
    }
}
