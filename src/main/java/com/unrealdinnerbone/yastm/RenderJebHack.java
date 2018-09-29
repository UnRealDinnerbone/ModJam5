//package com.unrealdinnerbone.yastm;
//
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.passive.EntitySheep;
//import net.minecraft.item.EnumDyeColor;
//
//public class RenderJebHack<T extends EntityLivingBase> implements LayerRenderer<T> {
//
//    private final RenderLiving renderLiving;
//    private final ModelBase modelBase;
//
//    public RenderJebHack(RenderLiving renderLiving, ModelBase modelBase)
//    {
//        this.renderLiving = renderLiving;
//        this.modelBase = modelBase;
//    }
//
//    @Override
//    public void doRenderLayer(T t, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//        int i1 = 25;
//        int i = t.ticksExisted / 25 + t.getEntityId();
//        int j = EnumDyeColor.values().length;
//        int k = i % j;
//        int l = (i + 1) % j;
//        float f = ((float) (t.ticksExisted % 25) + partialTicks) / 25.0F;
//        float[] afloat1 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(k));
//        float[] afloat2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(l));
//        GlStateManager.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
//        this.modelBase.setModelAttributes(this.renderLiving.getMainModel());
//        this.modelBase.setLivingAnimations(t, limbSwing, limbSwingAmount, partialTicks);
//        this.modelBase.render(t, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//    }
//
//    @Override
//    public boolean shouldCombineTextures() {
//        return false;
//    }
//}
