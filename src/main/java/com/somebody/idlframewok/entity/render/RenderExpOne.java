//package com.somebody.idlframewok.entity.render;
//
//import com.somebody.idlframewok.entity.creatures.misc.EntityExpOne;
//import com.somebody.idlframewok.entity.render.model.ModelCentaur;
//import com.somebody.idlframewok.util.Reference;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//
//import javax.annotation.Nullable;
//
//public class RenderExpOne extends RenderLiving<EntityExpOne> {
//    //public static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation(Reference.MOD_ID , "entity/centaur");
//    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/centaur.png");
//    //public static  final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation(Reference.MOD_ID + ":entity/centaur");
//
//    //public static final ResourceLocation TEXTURE_DEFAULT = new ResourceLocation(Reference.MOD_ID + ":textures/entity/centaur");
//    public RenderExpOne(RenderManager manager){
//        super(manager, new ModelCentaur(), 0.5F);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(EntityExpOne entity) {
//        return TEXTURES;
//    }
//
//    @Override
//    protected void applyRotations(EntityExpOne entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
//        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
//    }
//}
