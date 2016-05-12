package lilithscythemod.Weapons.WeaponsModel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LilithscytheModel extends ModelBase
{

    ModelRenderer tuka1;
    ModelRenderer tuka2;
    ModelRenderer tuka3;
    ModelRenderer tuka4;
    ModelRenderer tuka5;
    ModelRenderer tuka8;
    ModelRenderer tuka9;
    ModelRenderer scythe1;
    ModelRenderer scythe2;
    ModelRenderer scythe3;
    ModelRenderer scythe4;
    ModelRenderer scythe5;
    ModelRenderer scythe6;
    ModelRenderer scythe7;
    ModelRenderer scythe8;
    ModelRenderer scythe9;
    ModelRenderer scythe10;
    ModelRenderer scythe11;
    ModelRenderer scythe12;
    ModelRenderer scythe13;
    ModelRenderer scythe14;
    ModelRenderer scythe15;
    ModelRenderer scythe16;
    ModelRenderer scythe17;
    ModelRenderer scythe18;
    ModelRenderer scythe19;
    ModelRenderer scythe20;
    ModelRenderer scythe21;
    ModelRenderer scythe22;
    ModelRenderer scythe23;
    ModelRenderer scythe24;
    ModelRenderer scythe25;
    ModelRenderer scythe26;
    ModelRenderer core1;
    ModelRenderer core2;
    ModelRenderer accesry1;
    ModelRenderer accesry2;
    ModelRenderer Blade1;
    ModelRenderer Blade2;
    ModelRenderer Blade3;
    ModelRenderer Blade4;
    ModelRenderer Blade5;
    ModelRenderer Blade6;
    ModelRenderer backBlade1;
    ModelRenderer Piece1;


 public LilithscytheModel()
 {
	    textureWidth = 256;
	    textureHeight = 256;

	      Blade1 = new ModelRenderer(this, 14, 100);
	      Blade1.addBox(-18.3F, -1F, -18.2F, 4, 2, 2);
	      Blade1.setRotationPoint(0F, 0F, 0F);
	      Blade1.setTextureSize(256, 256);
	      Blade1.mirror = true;
	      setRotation(Blade1, 0F, 0.6050475F, 0F);
	      Blade2 = new ModelRenderer(this, 27, 100);
	      Blade2.addBox(15.7F, -1F, -25F, 1, 2, 6);
	      Blade2.setRotationPoint(0F, 0F, 0F);
	      Blade2.setTextureSize(256, 256);
	      Blade2.mirror = true;
	      setRotation(Blade2, 0F, 2.094395F, 0F);
	      Blade3 = new ModelRenderer(this, 0, 0);
	      Blade3.addBox(24.7F, -1F, -15.5F, 1, 2, 35);
	      Blade3.setRotationPoint(0F, 0F, 0F);
	      Blade3.setTextureSize(256, 256);
	      Blade3.mirror = true;
	      setRotation(Blade3, 0F, 2.530727F, 0F);
	      Blade4 = new ModelRenderer(this, 0, 39);
	      Blade4.addBox(20.7F, -0.5F, -10.5F, 5, 1, 30);
	      Blade4.setRotationPoint(0F, 0F, 0F);
	      Blade4.setTextureSize(256, 256);
	      Blade4.mirror = true;
	      setRotation(Blade4, 0F, 2.530727F, 0F);
	      Blade5 = new ModelRenderer(this, 0, 100);
	      Blade5.addBox(22.7F, -0.5F, -14.4F, 2, 1, 3);
	      Blade5.setRotationPoint(0F, 0F, 0F);
	      Blade5.setTextureSize(256, 256);
	      Blade5.mirror = true;
	      setRotation(Blade5, 0F, 2.478368F, 0F);
	      Blade6 = new ModelRenderer(this, 0, 71);
	      Blade6.addBox(16.7F, 0F, -7.5F, 4, 0, 27);
	      Blade6.setRotationPoint(0F, 0F, 0F);
	      Blade6.setTextureSize(256, 256);
	      Blade6.mirror = true;
	      setRotation(Blade6, 0F, 2.530727F, 0F);
	      backBlade1 = new ModelRenderer(this, 73, 0);
	      backBlade1.addBox(-26.5F, -0.5F, 4.3F, 14, 0, 10);
	      backBlade1.setRotationPoint(0F, 0F, 0F);
	      backBlade1.setTextureSize(256, 256);
	      backBlade1.mirror = false;
	      setRotation(backBlade1, 0F, 0F, 0F);
	      tuka1 = new ModelRenderer(this, 0, 110);
	      tuka1.addBox(-10F, -0.5F, 0F, 26, 1, 1);
	      tuka1.setRotationPoint(0F, 0F, 0F);
	      tuka1.setTextureSize(256, 256);
	      tuka1.mirror = true;
	      setRotation(tuka1, 0F, 0F, 0F);
	      tuka2 = new ModelRenderer(this, 55, 110);
	      tuka2.addBox(-11F, -1F, -0.5F, 1, 2, 2);
	      tuka2.setRotationPoint(0F, 0F, 0F);
	      tuka2.setTextureSize(256, 256);
	      tuka2.mirror = true;
	      setRotation(tuka2, 0F, 0F, 0F);
	      tuka3 = new ModelRenderer(this, 62, 110);
	      tuka3.addBox(-12.3F, -1F, -0.5F, 1, 2, 2);
	      tuka3.setRotationPoint(0F, 0F, 0F);
	      tuka3.setTextureSize(256, 256);
	      tuka3.mirror = true;
	      setRotation(tuka3, 0F, 0F, 0F);
	      tuka4 = new ModelRenderer(this, 69, 110);
	      tuka4.addBox(-12F, -0.5F, 0F, 2, 1, 1);
	      tuka4.setRotationPoint(0F, 0F, 0F);
	      tuka4.setTextureSize(256, 256);
	      tuka4.mirror = true;
	      setRotation(tuka4, 0F, 0F, 0F);
	      tuka5 = new ModelRenderer(this, 76, 110);
	      tuka5.addBox(-15F, -0.5F, 0F, 3, 1, 1);
	      tuka5.setRotationPoint(0F, 0F, 0F);
	      tuka5.setTextureSize(256, 256);
	      tuka5.mirror = true;
	      setRotation(tuka5, 0F, 0F, 0F);
	      tuka8 = new ModelRenderer(this, 85, 110);
	      tuka8.addBox(-13.9F, -1F, -0.5F, 1, 2, 2);
	      tuka8.setRotationPoint(0F, 0F, 0F);
	      tuka8.setTextureSize(256, 256);
	      tuka8.mirror = true;
	      setRotation(tuka8, 0F, 0F, 0F);
	      tuka9 = new ModelRenderer(this, 92, 110);
	      tuka9.addBox(-15.5F, -1.5F, -1F, 2, 3, 3);
	      tuka9.setRotationPoint(0F, 0F, 0F);
	      tuka9.setTextureSize(256, 256);
	      tuka9.mirror = true;
	      setRotation(tuka9, 0F, 0F, 0F);
	      scythe1 = new ModelRenderer(this, 12, 120);
	      scythe1.addBox(-16.5F, -1.5F, -2F, 1, 3, 4);
	      scythe1.setRotationPoint(0F, 0F, 0F);
	      scythe1.setTextureSize(256, 256);
	      scythe1.mirror = true;
	      setRotation(scythe1, 0F, 0F, 0F);
	      scythe2 = new ModelRenderer(this, 5, 120);
	      scythe2.addBox(-17.5F, -1F, -2.5F, 1, 2, 2);
	      scythe2.setRotationPoint(0F, 0F, 0F);
	      scythe2.setTextureSize(256, 256);
	      scythe2.mirror = true;
	      setRotation(scythe2, 0F, 0F, 0F);
	      scythe3 = new ModelRenderer(this, 0, 120);
	      scythe3.addBox(-17.5F, -0.5F, -2.8F, 1, 1, 1);
	      scythe3.setRotationPoint(0F, 0F, 0F);
	      scythe3.setTextureSize(256, 256);
	      scythe3.mirror = true;
	      setRotation(scythe3, 0F, 0F, 0F);
	      scythe4 = new ModelRenderer(this, 23, 120);
	      scythe4.addBox(-17F, -1.5F, 0F, 1, 3, 3);
	      scythe4.setRotationPoint(0F, 0F, 0F);
	      scythe4.setTextureSize(256, 256);
	      scythe4.mirror = true;
	      setRotation(scythe4, 0F, 0F, 0F);
	      scythe5 = new ModelRenderer(this, 32, 120);
	      scythe5.addBox(-17.5F, -1.5F, 1.5F, 1, 3, 2);
	      scythe5.setRotationPoint(0F, 0F, 0F);
	      scythe5.setTextureSize(256, 256);
	      scythe5.mirror = true;
	      setRotation(scythe5, 0F, 0F, 0F);
	      scythe6 = new ModelRenderer(this, 39, 120);
	      scythe6.addBox(-18F, -1.5F, 2F, 1, 3, 2);
	      scythe6.setRotationPoint(0F, 0F, 0F);
	      scythe6.setTextureSize(256, 256);
	      scythe6.mirror = true;
	      setRotation(scythe6, 0F, 0F, 0F);
	      scythe7 = new ModelRenderer(this, 46, 120);
	      scythe7.addBox(-18.5F, -1.5F, 2.4F, 1, 3, 2);
	      scythe7.setRotationPoint(0F, 0F, 0F);
	      scythe7.setTextureSize(256, 256);
	      scythe7.mirror = true;
	      setRotation(scythe7, 0F, 0F, 0F);
	      scythe8 = new ModelRenderer(this, 53, 120);
	      scythe8.addBox(-19F, -1.5F, 2.8F, 1, 3, 2);
	      scythe8.setRotationPoint(0F, 0F, 0F);
	      scythe8.setTextureSize(256, 256);
	      scythe8.mirror = true;
	      setRotation(scythe8, 0F, 0F, 0F);
	      scythe9 = new ModelRenderer(this, 60, 120);
	      scythe9.addBox(-19.5F, -1.5F, 3F, 1, 3, 2);
	      scythe9.setRotationPoint(0F, 0F, 0F);
	      scythe9.setTextureSize(256, 256);
	      scythe9.mirror = true;
	      setRotation(scythe9, 0F, 0F, 0F);
	      scythe10 = new ModelRenderer(this, 67, 120);
	      scythe10.addBox(-20F, -1.5F, 3.2F, 1, 3, 2);
	      scythe10.setRotationPoint(0F, 0F, 0F);
	      scythe10.setTextureSize(256, 256);
	      scythe10.mirror = true;
	      setRotation(scythe10, 0F, 0F, 0F);
	      scythe11 = new ModelRenderer(this, 74, 120);
	      scythe11.addBox(-21.5F, -1.5F, 3.3F, 2, 3, 2);
	      scythe11.setRotationPoint(0F, 0F, 0F);
	      scythe11.setTextureSize(256, 256);
	      scythe11.mirror = true;
	      setRotation(scythe11, 0F, 0F, 0F);
	      scythe12 = new ModelRenderer(this, 83, 120);
	      scythe12.addBox(-22F, -1.5F, 3.2F, 1, 3, 2);
	      scythe12.setRotationPoint(0F, 0F, 0F);
	      scythe12.setTextureSize(256, 256);
	      scythe12.mirror = true;
	      setRotation(scythe12, 0F, 0F, 0F);
	      scythe13 = new ModelRenderer(this, 90, 120);
	      scythe13.addBox(-22.5F, -1.5F, 3F, 1, 3, 2);
	      scythe13.setRotationPoint(0F, 0F, 0F);
	      scythe13.setTextureSize(256, 256);
	      scythe13.mirror = true;
	      setRotation(scythe13, 0F, 0F, 0F);
	      scythe14 = new ModelRenderer(this, 97, 120);
	      scythe14.addBox(-23F, -1.5F, 2.8F, 1, 3, 2);
	      scythe14.setRotationPoint(0F, 0F, 0F);
	      scythe14.setTextureSize(256, 256);
	      scythe14.mirror = true;
	      setRotation(scythe14, 0F, 0F, 0F);
	      scythe15 = new ModelRenderer(this, 104, 120);
	      scythe15.addBox(-23.5F, -1.5F, 2.4F, 1, 3, 2);
	      scythe15.setRotationPoint(0F, 0F, 0F);
	      scythe15.setTextureSize(256, 256);
	      scythe15.mirror = true;
	      setRotation(scythe15, 0F, 0F, 0F);
	      scythe16 = new ModelRenderer(this, 111, 120);
	      scythe16.addBox(-24.5F, -1.5F, 1.5F, 1, 3, 2);
	      scythe16.setRotationPoint(0F, 0F, 0F);
	      scythe16.setTextureSize(256, 256);
	      scythe16.mirror = true;
	      setRotation(scythe16, 0F, 0F, 0F);
	      scythe17 = new ModelRenderer(this, 118, 120);
	      scythe17.addBox(-24F, -1.5F, 2F, 1, 3, 2);
	      scythe17.setRotationPoint(0F, 0F, 0F);
	      scythe17.setTextureSize(256, 256);
	      scythe17.mirror = true;
	      setRotation(scythe17, 0F, 0F, 0F);
	      scythe18 = new ModelRenderer(this, 125, 120);
	      scythe18.addBox(-24.7F, -1.5F, -2.5F, 1, 3, 2);
	      scythe18.setRotationPoint(0F, 0F, 0F);
	      scythe18.setTextureSize(256, 256);
	      scythe18.mirror = true;
	      setRotation(scythe18, 0F, 0F, 0F);
	      scythe19 = new ModelRenderer(this, 132, 120);
	      scythe19.addBox(-25F, -1.5F, 0F, 1, 3, 3);
	      scythe19.setRotationPoint(0F, 0F, 0F);
	      scythe19.setTextureSize(256, 256);
	      scythe19.mirror = true;
	      setRotation(scythe19, 0F, 0F, 0F);
	      scythe20 = new ModelRenderer(this, 141, 120);
	      scythe20.addBox(-25.3F, -1.5F, -1F, 1, 3, 3);
	      scythe20.setRotationPoint(0F, 0F, 0F);
	      scythe20.setTextureSize(256, 256);
	      scythe20.mirror = true;
	      setRotation(scythe20, 0F, 0F, 0F);
	      scythe21 = new ModelRenderer(this, 150, 120);
	      scythe21.addBox(-25F, -1.5F, -2F, 1, 3, 2);
	      scythe21.setRotationPoint(0F, 0F, 0F);
	      scythe21.setTextureSize(256, 256);
	      scythe21.mirror = true;
	      setRotation(scythe21, 0F, 0F, 0F);
	      scythe22 = new ModelRenderer(this, 157, 120);
	      scythe22.addBox(-24.7F, -1.5F, -2.5F, 1, 3, 2);
	      scythe22.setRotationPoint(0F, 0F, 0F);
	      scythe22.setTextureSize(256, 256);
	      scythe22.mirror = true;
	      setRotation(scythe22, 0F, 0F, 0F);
	      scythe23 = new ModelRenderer(this, 164, 120);
	      scythe23.addBox(-24.4F, -1.5F, -3.5F, 1, 3, 2);
	      scythe23.setRotationPoint(0F, 0F, 0F);
	      scythe23.setTextureSize(256, 256);
	      scythe23.mirror = true;
	      setRotation(scythe23, 0F, 0F, 0F);
	      scythe24 = new ModelRenderer(this, 171, 120);
	      scythe24.addBox(-23.7F, -1.5F, -4F, 1, 3, 1);
	      scythe24.setRotationPoint(0F, 0F, 0F);
	      scythe24.setTextureSize(256, 256);
	      scythe24.mirror = true;
	      setRotation(scythe24, 0F, 0F, 0F);
	      scythe25 = new ModelRenderer(this, 176, 120);
	      scythe25.addBox(-22.7F, -1.5F, -4.5F, 1, 3, 1);
	      scythe25.setRotationPoint(0F, 0F, 0F);
	      scythe25.setTextureSize(256, 256);
	      scythe25.mirror = true;
	      setRotation(scythe25, 0F, 0F, 0F);
	      scythe26 = new ModelRenderer(this, 181, 120);
	      scythe26.addBox(-21.7F, -1.5F, -5F, 1, 3, 1);
	      scythe26.setRotationPoint(0F, 0F, 0F);
	      scythe26.setTextureSize(256, 256);
	      scythe26.mirror = true;
	      setRotation(scythe26, 0F, 0F, 0F);
	      core1 = new ModelRenderer(this, 73, 11);
	      core1.addBox(-22.5F, -0.5F, -2F, 4, 1, 4);
	      core1.setRotationPoint(0F, 0F, 0F);
	      core1.setTextureSize(256, 256);
	      core1.mirror = true;
	      setRotation(core1, 0F, 0F, 0F);
	      core2 = new ModelRenderer(this, 73, 18);
	      core2.addBox(-22F, -1.5F, -1.5F, 3, 3, 3);
	      core2.setRotationPoint(0F, 0F, 0F);
	      core2.setTextureSize(256, 256);
	      core2.mirror = true;
	      setRotation(core2, 0F, 0F, 0F);
	      core2.mirror = false;
	      accesry1 = new ModelRenderer(this, 73, 30);
	      accesry1.addBox(-28.3F, -1F, 0F, 4, 1, 1);
	      accesry1.setRotationPoint(0F, 0F, 0F);
	      accesry1.setTextureSize(256, 256);
	      accesry1.mirror = true;
	      setRotation(accesry1, 0F, 0F, 0F);
	      accesry2 = new ModelRenderer(this, 73, 35);
	      accesry2.addBox(-19.5F, -1.5F, -20F, 2, 2, 2);
	      accesry2.setRotationPoint(0F, 0F, 0F);
	      accesry2.setTextureSize(256, 256);
	      accesry2.mirror = true;
	      setRotation(accesry2, 0F, 0.8179294F, 0F);

	      Piece1 = new ModelRenderer(this, "Piece1");
	      Piece1.setRotationPoint(0F, 10F, 0F);
	      setRotation(Piece1, 1.5707F, 0F, 0F);
	      Piece1.mirror = false;

	      Piece1.addChild(scythe1);
	      Piece1.addChild(scythe2);
	      Piece1.addChild(scythe3);
	      Piece1.addChild(scythe4);
	      Piece1.addChild(scythe5);
	      Piece1.addChild(scythe6);
	      Piece1.addChild(scythe7);
	      Piece1.addChild(scythe8);
	      Piece1.addChild(scythe9);
	      Piece1.addChild(scythe10);
	      Piece1.addChild(scythe11);
	      Piece1.addChild(scythe12);
	      Piece1.addChild(scythe13);
	      Piece1.addChild(scythe14);
	      Piece1.addChild(scythe15);
	      Piece1.addChild(scythe16);
	      Piece1.addChild(scythe17);
	      Piece1.addChild(scythe18);
	      Piece1.addChild(scythe19);
	      Piece1.addChild(scythe20);
	      Piece1.addChild(scythe21);
	      Piece1.addChild(scythe22);
	      Piece1.addChild(scythe23);
	      Piece1.addChild(scythe24);
	      Piece1.addChild(scythe25);
	      Piece1.addChild(scythe26);

	      Piece1.addChild(tuka1);
	      Piece1.addChild(tuka2);
	      Piece1.addChild(tuka3);
	      Piece1.addChild(tuka4);
	      Piece1.addChild(tuka5);
	      Piece1.addChild(tuka8);
	      Piece1.addChild(tuka9);

	      Piece1.addChild(Blade1);
	      Piece1.addChild(Blade2);
	      Piece1.addChild(Blade3);
	      Piece1.addChild(Blade4);
	      Piece1.addChild(Blade5);
	      Piece1.addChild(Blade6);

	      Piece1.addChild(accesry1);
	      Piece1.addChild(accesry2);

	      Piece1.addChild(core1);
	      Piece1.addChild(core2);

	      Piece1.addChild(backBlade1);





 }

 @SideOnly(Side.CLIENT)//クラス自体につければ必要ないかな．
 public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
 {
	 super.render(entity, f, f1, f2, f3, f4, f5);
     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
     Piece1.render(f5);

 }
private void setRotation(ModelRenderer model, float x, float y, float z)
{
 model.rotateAngleX = x;
 model.rotateAngleY = y;
 model.rotateAngleZ = z;
}


public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
{
  super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);

}

}



