package lilithscythemod.Armors.ArmorModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;

public class ArtemisEarModel extends ModelBiped
{
	
	private static Minecraft mc = FMLClientHandler.instance().getClient();
	private ResourceLocation tex =new ResourceLocation("lilithscythemod:textures/items/armor/ArtemisEar.png");
	  //fields
    ModelRenderer RightEar1;
    ModelRenderer RightEar2;
    ModelRenderer RightEar3;
    ModelRenderer RightEar4;
    ModelRenderer LeftEar1;
    ModelRenderer LeftEar2;
    ModelRenderer LeftEar3;
    ModelRenderer LeftEar4;
    ModelRenderer Accessory1;
    ModelRenderer Accessory2;
    ModelRenderer Accessory3;
    ModelRenderer Accessory4;
    ModelRenderer Accessory5;
    ModelRenderer Accessory6;
    ModelRenderer Accessory7;
    ModelRenderer Piece1;
  public ArtemisEarModel()
  {
	    textureWidth = 64;
	    textureHeight = 64;
	  
	    
	      RightEar1 = new ModelRenderer(this, 0, 0);
	      RightEar1.addBox(-0.5F, -14F, 0F, 3, 7, 1);
	      RightEar1.setRotationPoint(0F, 0F, 0F);
	      RightEar1.setTextureSize(64, 64);
	      RightEar1.mirror = false;
	      setRotation(RightEar1, 0F, 0F, -0.3665191F);
	      RightEar2 = new ModelRenderer(this, 0, 9);
	      RightEar2.addBox(0F, -14F, -0.5F, 2, 6, 1);
	      RightEar2.setRotationPoint(0F, 0F, 0F);
	      RightEar2.setTextureSize(64, 64);
	      RightEar2.mirror = false;
	      setRotation(RightEar2, 0F, 0F, -0.3490659F);
	      RightEar3 = new ModelRenderer(this, 0, 17);
	      RightEar3.addBox(-0.5F, -14F, -4F, 3, 1, 5);
	      RightEar3.setRotationPoint(0F, 0F, 0F);
	      RightEar3.setTextureSize(64, 64);
	      RightEar3.mirror = false;
	      setRotation(RightEar3, 0F, 0F, -0.3490659F);
	      RightEar4 = new ModelRenderer(this, 0, 24);
	      RightEar4.addBox(0F, -13.5F, -3F, 2, 1, 4);
	      RightEar4.setRotationPoint(0F, 0F, 0F);
	      RightEar4.setTextureSize(64, 64);
	      RightEar4.mirror = false;
	      setRotation(RightEar4, 0F, 0F, -0.3490659F);
	      LeftEar1 = new ModelRenderer(this, 16, 0);
	      LeftEar1.addBox(-1F, -16F, 0F, 3, 8, 1);
	      LeftEar1.setRotationPoint(0F, 0F, 0F);
	      LeftEar1.setTextureSize(64, 64);
	      LeftEar1.mirror = false;
	      setRotation(LeftEar1, 0F, -0F, 0.1745329F);
	      LeftEar2 = new ModelRenderer(this, 16, 9);
	      LeftEar2.addBox(-0.54F, -16F, -0.5F, 2, 8, 1);
	      LeftEar2.setRotationPoint(0F, 0F, 0F);
	      LeftEar2.setTextureSize(64, 64);
	      LeftEar2.mirror = false;
	      setRotation(LeftEar2, 0F, 0F, 0.1745329F);
	      LeftEar3 = new ModelRenderer(this, 16, 19);
	      LeftEar3.addBox(-1F, -16F, -8.5F, 3, 1, 4);
	      LeftEar3.setRotationPoint(0F, 0F, 0F);
	      LeftEar3.setTextureSize(64, 64);
	      LeftEar3.mirror = false;
	      setRotation(LeftEar3, -0.3490659F, 0F, 0.1745329F);
	      LeftEar4 = new ModelRenderer(this, 16, 25);
	      LeftEar4.addBox(-0.5F, -15.5F, -7.5F, 2, 1, 3);
	      LeftEar4.setRotationPoint(0F, 0F, 0F);
	      LeftEar4.setTextureSize(64, 64);
	      LeftEar4.mirror = false;
	      setRotation(LeftEar4, -0.3490659F, 0F, 0.1745329F);
	      Accessory1 = new ModelRenderer(this, 0, 31);
	      Accessory1.addBox(-1.5F, -12F, -1.5F, 4, 1, 3);
	      Accessory1.setRotationPoint(0F, 0F, 0F);
	      Accessory1.setTextureSize(64, 32);
	      Accessory1.mirror = true;
	      setRotation(Accessory1, 0F, 0F, 0.1745329F);
	      Accessory2 = new ModelRenderer(this, 16, 31);
	      Accessory2.addBox(-1F, -11F, -1F, 3, 1, 2);
	      Accessory2.setRotationPoint(0F, 0F, 0F);
	      Accessory2.setTextureSize(64, 32);
	      Accessory2.mirror = true;
	      setRotation(Accessory2, 0F, 0F, 0.1745329F);
	      Accessory3 = new ModelRenderer(this, 27, 31);
	      Accessory3.addBox(-1.5F, -10.5F, -1.5F, 4, 1, 3);
	      Accessory3.setRotationPoint(0F, 0F, 0F);
	      Accessory3.setTextureSize(64, 32);
	      Accessory3.mirror = true;
	      setRotation(Accessory3, 0F, 0F, 0.1745329F);
	      Accessory4 = new ModelRenderer(this, 3, 40);
	      Accessory4.addBox(0.5F, -9.5F, -2F, 1, 5, 0);
	      Accessory4.setRotationPoint(0F, 0F, 0F);
	      Accessory4.setTextureSize(64, 32);
	      Accessory4.mirror = true;
	      setRotation(Accessory4, 0F, 0F, 0.1745329F);
	      Accessory5 = new ModelRenderer(this, 0, 40);
	      Accessory5.addBox(-5F, -9.5F, -2F, 1, 3, 0);
	      Accessory5.setRotationPoint(0F, 0F, 0F);
	      Accessory5.setTextureSize(64, 32);
	      Accessory5.mirror = true;
	      setRotation(Accessory5, 0F, 0F, 0.6632251F);
	      Accessory6 = new ModelRenderer(this, 6, 40);
	      Accessory6.addBox(4F, -9.5F, -2F, 1, 3, 0);
	      Accessory6.setRotationPoint(0F, 0F, 0F);
	      Accessory6.setTextureSize(64, 32);
	      Accessory6.mirror = true;
	      setRotation(Accessory6, 0F, 0F, -0.1047198F);
	      Accessory7 = new ModelRenderer(this, 9, 40);
	      Accessory7.addBox(0F, -11.5F, -2.2F, 2, 2, 2);
	      Accessory7.setRotationPoint(0F, 0F, 0F);
	      Accessory7.setTextureSize(64, 32);
	      Accessory7.mirror = true;
	      setRotation(Accessory7, 0F, 0F, 0.1745329F);
	      Piece1 = new ModelRenderer(this, "Piece1");
	      Piece1.setRotationPoint(0F, 0F, 0F);
	      setRotation(Piece1, 0F, 0F, 0F);
	      Piece1.mirror = true;
	      this.Piece1.addChild(LeftEar1);
	      this.Piece1.addChild(LeftEar2);
	      this.Piece1.addChild(LeftEar3);
	      this.Piece1.addChild(LeftEar4);
	      this.Piece1.addChild(RightEar1);
	      this.Piece1.addChild(RightEar2);
	      this.Piece1.addChild(RightEar3);
	      this.Piece1.addChild(RightEar4);
	      this.Piece1.addChild(Accessory1);
	      this.Piece1.addChild(Accessory2);
	      this.Piece1.addChild(Accessory3);
	      this.Piece1.addChild(Accessory4);
	      this.Piece1.addChild(Accessory5);
	      this.Piece1.addChild(Accessory6);
	      this.Piece1.addChild(Accessory7);
	      this.bipedHead.addChild(Piece1);
    
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {     
	   mc.renderEngine.bindTexture(tex);
	   this.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		this.Piece1.render(f5);	


  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
	  this.Piece1.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
      this.Piece1.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
  }
  


}

