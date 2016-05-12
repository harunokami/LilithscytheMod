package lilithscythemod.Entity.EntityModel;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class EntityHorunModel extends ModelSpider{
	 //fields
	//public static final ResourceLocation tex = new ResourceLocation("lilithscythemod:textures/entitymob/Horun.png");
	//private static Minecraft mc = Minecraft.getMinecraft();
 /*   ModelRenderer Leg52;
    ModelRenderer Leg62;
    ModelRenderer Leg7;
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RearEnd;
    ModelRenderer Leg8;
    ModelRenderer Leg82;
    ModelRenderer Leg6;
    ModelRenderer Leg4;
    ModelRenderer Leg2;
    ModelRenderer Leg72;
    ModelRenderer Leg5;
    ModelRenderer Leg3;
    ModelRenderer Leg1;
    ModelRenderer RearEnd2;
    ModelRenderer RearEnd3;
    ModelRenderer RearEnd4;
    ModelRenderer RearEnd5;
    ModelRenderer RearEnd6;
    ModelRenderer RearEnd7;*/

	//fields
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Leg5;
    ModelRenderer Leg6;
    ModelRenderer Leg7;
    ModelRenderer Leg72;
    ModelRenderer Leg8;
    ModelRenderer Leg82;
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RearEnd;
    ModelRenderer RearEnd2;
    ModelRenderer RearEnd3;
    ModelRenderer RearEnd4;
    ModelRenderer RearEnd5;
    ModelRenderer RearEnd6;
    ModelRenderer RearEnd7;
    ModelRenderer Piece1;
    public EntityHorunModel()
    {

    	textureWidth = 256;
        textureHeight = 256;

          Leg1 = new ModelRenderer(this, 18, 0);
          Leg1.addBox(-15F, -1F, -1F, 16, 2, 2);
          Leg1.setRotationPoint(-4F, 20F, 2F);
          Leg1.setTextureSize(64, 32);
          Leg1.mirror = true;
          setRotation(Leg1, 0F, 0.5759587F, -0.1919862F);
          Leg2 = new ModelRenderer(this, 18, 0);
          Leg2.addBox(-1F, -1F, -1F, 16, 2, 2);
          Leg2.setRotationPoint(4F, 20F, 2F);
          Leg2.setTextureSize(64, 32);
          Leg2.mirror = true;
          setRotation(Leg2, 0F, -0.5759587F, 0.1919862F);
          Leg3 = new ModelRenderer(this, 18, 0);
          Leg3.addBox(-15F, -1F, -1F, 16, 2, 2);
          Leg3.setRotationPoint(-4F, 20F, 1F);
          Leg3.setTextureSize(64, 32);
          Leg3.mirror = true;
          setRotation(Leg3, 0F, 0.2792527F, -0.1919862F);
          Leg4 = new ModelRenderer(this, 18, 0);
          Leg4.addBox(-1F, -1F, -1F, 16, 2, 2);
          Leg4.setRotationPoint(4F, 20F, 1F);
          Leg4.setTextureSize(64, 32);
          Leg4.mirror = true;
          setRotation(Leg4, 0F, -0.2792527F, 0.1919862F);
          Leg5 = new ModelRenderer(this, 18, 0);
          Leg5.addBox(-15F, -1F, -1F, 16, 2, 2);
          Leg5.setRotationPoint(-4F, 20F, 0F);
          Leg5.setTextureSize(64, 32);
          Leg5.mirror = true;
          setRotation(Leg5, 0F, -0.2792527F, -0.1919862F);
          Leg6 = new ModelRenderer(this, 18, 0);
          Leg6.addBox(-1F, -1F, -1F, 16, 2, 2);
          Leg6.setRotationPoint(4F, 20F, 0F);
          Leg6.setTextureSize(64, 32);
          Leg6.mirror = true;
          setRotation(Leg6, 0F, 0.2792527F, 0.1919862F);
          Leg7 = new ModelRenderer(this, 43, 20);
          Leg7.addBox(-8.333333F, -1F, -1F, 9, 2, 2);
          Leg7.setRotationPoint(-4F, 20F, -1F);
          Leg7.setTextureSize(64, 32);
          Leg7.mirror = true;
          setRotation(Leg7, -0.0371786F, -0.8726646F, 0.8726646F);
          Leg72 = new ModelRenderer(this, 37, 35);
          Leg72.addBox(-8F, -1F, -1F, 2, 13, 2);
          Leg72.setRotationPoint(-4F, 20F, -1F);
          Leg72.setTextureSize(64, 32);
          Leg72.mirror = true;
          setRotation(Leg72, 0F, -0.8726646F, 0.8726646F);
          Leg8 = new ModelRenderer(this, 43, 25);
          Leg8.addBox(-1F, -1F, -1F, 9, 2, 2);
          Leg8.setRotationPoint(4F, 20F, -1F);
          Leg8.setTextureSize(64, 32);
          Leg8.mirror = true;
          setRotation(Leg8, 0.1487144F, 0.8726646F, -0.8726646F);
          Leg82 = new ModelRenderer(this, 37, 30);
          Leg82.addBox(-1F, -1F, -1F, 13, 2, 2);
          Leg82.setRotationPoint(7F, 14F, -5F);
          Leg82.setTextureSize(64, 32);
          Leg82.mirror = true;
          setRotation(Leg82, 0.0F, 0.785398F, 0.785398F);
          Head = new ModelRenderer(this, 32, 4);
          Head.addBox(-3F, -7F, -6F, 6, 6, 6);
          Head.setRotationPoint(0F, 20F, -3F);
          Head.setTextureSize(64, 32);
          Head.mirror = true;
          setRotation(Head, 0F, 0F, 0F);
          Body = new ModelRenderer(this, 0, 0);
          Body.addBox(-3F, -3F, -3F, 6, 6, 6);
          Body.setRotationPoint(0F, 20F, 0F);
          Body.setTextureSize(64, 32);
          Body.mirror = true;
          setRotation(Body, 0F, 0F, 0F);
          RearEnd = new ModelRenderer(this, 0, 30);
          RearEnd.addBox(-3F, -4F, -6F, 6, 4, 2);
          RearEnd.setRotationPoint(0F, 20F, 9F);
          RearEnd.setTextureSize(64, 32);
          RearEnd.mirror = true;
          setRotation(RearEnd, 0F, 0F, 0F);
          RearEnd2 = new ModelRenderer(this, 0, 37);
          RearEnd2.addBox(-4F, -5F, -4F, 8, 5, 2);
          RearEnd2.setRotationPoint(0F, 20F, 9F);
          RearEnd2.setTextureSize(64, 32);
          RearEnd2.mirror = true;
          setRotation(RearEnd2, 0F, 0F, 0F);
          RearEnd3 = new ModelRenderer(this, 0, 45);
          RearEnd3.addBox(-5F, -6F, -3F, 10, 7, 3);
          RearEnd3.setRotationPoint(0F, 20F, 9F);
          RearEnd3.setTextureSize(64, 32);
          RearEnd3.mirror = true;
          setRotation(RearEnd3, 0F, 0F, 0F);
          RearEnd4 = new ModelRenderer(this, 0, 56);
          RearEnd4.addBox(-5F, -7F, -1F, 10, 9, 3);
          RearEnd4.setRotationPoint(0F, 20F, 9F);
          RearEnd4.setTextureSize(64, 32);
          RearEnd4.mirror = true;
          setRotation(RearEnd4, 0F, 0F, 0F);
          RearEnd5 = new ModelRenderer(this, 0, 70);
          RearEnd5.addBox(-6F, -9F, 2F, 12, 11, 4);
          RearEnd5.setRotationPoint(0F, 20F, 9F);
          RearEnd5.setTextureSize(64, 32);
          RearEnd5.mirror = true;
          setRotation(RearEnd5, 0F, 0F, 0F);
          RearEnd6 = new ModelRenderer(this, 35, 50);
          RearEnd6.addBox(-5F, -7F, 5F, 10, 9, 2);
          RearEnd6.setRotationPoint(0F, 20F, 9F);
          RearEnd6.setTextureSize(64, 32);
          RearEnd6.mirror = true;
          setRotation(RearEnd6, 0F, 0F, 0F);
          RearEnd7 = new ModelRenderer(this, 35, 62);
          RearEnd7.addBox(-5F, -6F, 6F, 10, 7, 2);
          RearEnd7.setRotationPoint(0F, 20F, 9F);
          RearEnd7.setTextureSize(64, 32);
          RearEnd7.mirror = true;
          setRotation(RearEnd7, 0F, 0F, 0F);
         // Leg8.addChild(Leg82);


    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {

      setRotationAngles(f, f1, f2, f3, f4, f5,entity);
      Leg1.render(f5);
      Leg2.render(f5);
      Leg3.render(f5);
      Leg4.render(f5);
      Leg5.render(f5);
      Leg6.render(f5);
      Leg7.render(f5);
      Leg72.render(f5);
      Leg8.render(f5);
      Leg82.render(f5);
      Head.render(f5);
      Body.render(f5);
      RearEnd.render(f5);
      RearEnd2.render(f5);
      RearEnd3.render(f5);
      RearEnd4.render(f5);
      RearEnd5.render(f5);
      RearEnd6.render(f5);
      RearEnd7.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    /**
     * f1:移動距離の累積値
     * f2:活動強度（息の上がり具合）
     * f3:Entityが生成されてからの時間:
     * f4:頭の横振り角度
     * f5:頭の縦振り角度
     * f6:モデルで指定されているサイズ倍率
     * */
    public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6,Entity entity)
    {

     super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
      this.Head.rotateAngleY = f4 / (180F / (float)Math.PI);
      this.Head.rotateAngleX = f5 / (180F / (float)Math.PI);
      float p6 = ((float)Math.PI / 4F);
      this.Leg1.rotateAngleZ = -p6;
      this.Leg2.rotateAngleZ = p6;
      this.Leg3.rotateAngleZ = -p6 * 0.74F;
      this.Leg4.rotateAngleZ = p6 * 0.74F;
      this.Leg5.rotateAngleZ = -p6 * 0.74F;
      this.Leg6.rotateAngleZ = p6 * 0.74F;

      /*this.Leg7.rotateAngleZ = p6;
      this.Leg72.rotateAngleZ =p6;
      this.Leg8.rotateAngleZ = p6;
      this.Leg82.rotateAngleZ = p6;*/
      float p7 = -0.0F;
      float p8 = 0.3926991F;
      this.Leg1.rotateAngleY = p8 * 2.0F + p7;
      this.Leg2.rotateAngleY = -p8 * 2.0F - p7;
      this.Leg3.rotateAngleY = p8 * 1.0F + p7;
      this.Leg4.rotateAngleY = -p8 * 1.0F - p7;
      this.Leg5.rotateAngleY = -p8 * 1.0F + p7;
      this.Leg6.rotateAngleY = p8 * 1.0F - p7;

      /*this.Leg7.rotateAngleY = -p8 * 2.0F + p7;
      this.Leg8.rotateAngleY = p8 * 2.0F - p7;
      this.Leg72.rotateAngleY = -p8 * 2.0F + p7;
      this.Leg82.rotateAngleY = p8 * 2.0F - p7;*/

      float p9 = -(MathHelper.cos(f1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * f2;
      float p10 = -(MathHelper.cos(f1 * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * f2;
      float p11 = -(MathHelper.cos(f1 * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * f2;
      float p12 = -(MathHelper.cos(f1 * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * f2;
      float p13 = Math.abs(MathHelper.sin(f1 * 0.6662F + 0.0F) * 0.4F) * f2;
      float p14 = Math.abs(MathHelper.sin(f1 * 0.6662F + (float)Math.PI) * 0.4F) * f2;
      float p15 = Math.abs(MathHelper.sin(f1 * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * f2;
      float p16 = Math.abs(MathHelper.sin(f1 * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * f2;
      this.Leg1.rotateAngleY += p9;
      this.Leg2.rotateAngleY += -p9;
      this.Leg3.rotateAngleY += p10;
      this.Leg4.rotateAngleY += -p10;
      this.Leg5.rotateAngleY += p11;
      this.Leg6.rotateAngleY += -p11;

      /*this.Leg7.rotateAngleY += p12;
      this.Leg8.rotateAngleY += -p12;
      this.Leg72.rotateAngleY += p12;
      this.Leg82.rotateAngleY += -p12;*/

      this.Leg1.rotateAngleZ += p13;
      this.Leg2.rotateAngleZ += -p13;
      this.Leg3.rotateAngleZ += p14;
      this.Leg4.rotateAngleZ += -p14;
      this.Leg5.rotateAngleZ += p15;
      this.Leg6.rotateAngleZ += -p15;

     /* this.Leg7.rotateAngleZ += p16;
      this.Leg8.rotateAngleZ += -p16;
      this.Leg72.rotateAngleZ += p16;
      this.Leg82.rotateAngleZ += -p16;*/
    }
}
