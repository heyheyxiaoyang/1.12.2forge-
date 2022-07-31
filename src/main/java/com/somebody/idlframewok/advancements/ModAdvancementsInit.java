package com.somebody.idlframewok.advancements;

import com.somebody.idlframewok.Idealland;
import com.somebody.idlframewok.advancements.trigger.TestTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class ModAdvancementsInit {
    public static final TestTrigger TEST_TRIGGER = CriteriaTriggers.register(new TestTrigger());

    public static void initialization() {};

    public void triggerCriteria(EntityLivingBase livingBase)//arguments todo. varags.
    {
        if (livingBase.world != null && !livingBase.world.isRemote && livingBase instanceof EntityPlayerMP) {
            TEST_TRIGGER.trigger((EntityPlayerMP) livingBase);
        }
    }

    public static boolean giveAdvancement(EntityPlayer player, Advancement advancement)
    {
        if (player.world == null || player.world.isRemote)
        {
            return false;
        }

        EntityPlayerMP playerMP = (EntityPlayerMP) player;
        if (advancement == null)
        {
            return false;
        }
        AdvancementProgress advancementprogress = playerMP.getAdvancements().getProgress(advancement);

        if (advancementprogress.isDone())
        {
            return false;
        }
        else
        {
            for (String s : advancementprogress.getRemaningCriteria())
            {
                playerMP.getAdvancements().grantCriterion(advancement, s);
            }

            return true;
        }
    }

    public static boolean giveAdvancement(EntityPlayer player, String id)
    {
        if (player.world == null || player.world.isRemote)
        {
            return false;
        }

        EntityPlayerMP playerMP = (EntityPlayerMP) player;
        Advancement advancement = findAdvancement(playerMP.mcServer, id);
        if (advancement == null)
        {
            Idealland.LogWarning("failed to find an advancement:%s", id);
            return false;
        }

        return giveAdvancement(player, advancement);
    }

    public static boolean hasAdvancement(EntityPlayer player, String id) {
        if (player.getEntityWorld().isRemote) {
            return false;
        }

        EntityPlayerMP playerMP = (EntityPlayerMP) player;
        Advancement advancement = findAdvancement(playerMP.mcServer, id);
        if (advancement == null) {
            Idealland.LogWarning("failed to find an advancement:%s", id);
            return false;
        }

        return hasAdvancement(playerMP, advancement);
    }

    public static boolean hasAdvancement(EntityPlayerMP p_192552_2_, Advancement p_192552_3_)
    {
        AdvancementProgress advancementprogress = p_192552_2_.getAdvancements().getProgress(p_192552_3_);
        return advancementprogress.isDone();
    }
        
    public static Advancement findAdvancement(MinecraftServer server, String id)
    {
        Advancement advancement = server.getAdvancementManager().getAdvancement(new ResourceLocation(Idealland.MODID, id));
        if (advancement == null)
        {
            //vanilla
            advancement = server.getAdvancementManager().getAdvancement(new ResourceLocation(id));
        }

        if (advancement == null)
        {
            Idealland.Log("Cannot find advancement:%s", id);
            return null;
        }
        else
        {
            return advancement;
        }
    }
}