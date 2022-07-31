package com.somebody.idlframewok.potion.instance;

import net.minecraft.entity.EntityLivingBase;

import javax.annotation.Nonnull;

public class PotionDeadly extends ModPotionAttr {


    public PotionDeadly(boolean isBadEffectIn, int liquidColorIn, String name, int icon) {
        super(isBadEffectIn, liquidColorIn, name, icon);
    }

    @Override
    public void performEffect(@Nonnull EntityLivingBase living, int amplified) {
        //do nothing
    }
}
