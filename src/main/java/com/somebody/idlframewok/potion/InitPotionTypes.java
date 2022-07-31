package com.somebody.idlframewok.potion;

import com.somebody.idlframewok.blocks.ModBlocks;
import com.somebody.idlframewok.item.ModItems;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;

import static net.minecraft.init.PotionTypes.AWKWARD;

public class InitPotionTypes {
    public static final PotionType BLINDNESS;
    public static final PotionType LUCKY;
    public static final PotionType UNLUCKY;
    public static final PotionType HASTE;
    public static final PotionType RESISTANCE;
    public static final PotionType WITHER;
    public static final PotionType LEVITATION;
    public static final PotionType GLOWING;
    //public static final PotionType LONG_BLINDNESS;

    public static final int DURA_GOOD_3 = 1600;
    public static final int DURA_GOOD_4 = 1200;

    public static final ModPotionType REGEN_3  = new ModPotionType("regen_3", new PotionEffect(MobEffects.REGENERATION, 450, 2));
    public static final ModPotionType REGEN_4  = new ModPotionType("regen_4", new PotionEffect(MobEffects.REGENERATION, 200, 3));

    static
    {
        if (!Bootstrap.isRegistered())
        {
            throw new RuntimeException("Accessed Potions before Bootstrap!");
        }
        else
        {
            BLINDNESS = createType(MobEffects.BLINDNESS);
            LUCKY = createType(MobEffects.LUCK);
            UNLUCKY = createType(MobEffects.UNLUCK);
            HASTE = createType(MobEffects.HASTE);
            RESISTANCE = createType(MobEffects.RESISTANCE);
            WITHER = createType(MobEffects.WITHER);
            LEVITATION = createType(MobEffects.LEVITATION);
            GLOWING = createType(MobEffects.GLOWING);

        }
    }

    static void init()
    {
        //Vanilla Fixing
//        PotionHelper.addMix(AWKWARD, Item.getItemFromBlock(ModBlocks.FLESH_BLOCK_EYE), BLINDNESS);
//
//        PotionHelper.addMix(AWKWARD, ModItems.CLOVER, LUCKY);
//        final Item DEGREATOR = Items.FERMENTED_SPIDER_EYE;
//        PotionHelper.addMix(LUCKY, DEGREATOR, UNLUCKY);
//
//        PotionHelper.addMix(AWKWARD, Items.STONE_PICKAXE, HASTE);
//        PotionHelper.addMix(AWKWARD, Items.SHIELD, RESISTANCE);
//        PotionHelper.addMix(AWKWARD, Item.getItemFromBlock(Blocks.SOUL_SAND), WITHER);
//        PotionHelper.addMix(AWKWARD, ModItems.FLOAT_FOOD, LEVITATION);
//        PotionHelper.addMix(AWKWARD, Item.getItemFromBlock(Blocks.TORCH), GLOWING);

        //Modded potion effects
//        PotionHelper.addMix(AWKWARD, ModItems.CHILLI, ModPotions.DEADLY.getPotionType());
//        PotionHelper.addMix(AWKWARD, ModItems.CHILLI, ModPotions.ZEN_HEART.getPotionType());
//        PotionHelper.addMix(AWKWARD, ModItems.CHILLI, ModPotions.VIRUS_ONE.getPotionType());
//        PotionHelper.addMix(AWKWARD, Items.ROTTEN_FLESH, ModPotions.UNDYING.getPotionType());

        //-----------------------------Types


    }

    static ModPotionType createType(Potion potion)
    {
        //mostly for vanilla
        return new ModPotionType(potion.getName(), getEffect(potion));
    }

    public static PotionEffect getEffect(Potion potion)
    {
        return new PotionEffect(potion, potion.isBadEffect() ? 1800 : 3600);
    }
}
