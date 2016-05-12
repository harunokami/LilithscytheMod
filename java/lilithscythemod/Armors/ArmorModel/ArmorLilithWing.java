package lilithscythemod.Armors.ArmorModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ArmorLilithWing extends ModelBiped{
	
	private static Minecraft mc = FMLClientHandler.instance().getClient();

	boolean Wing=true;
	  //fields
    ModelRenderer LeftWing1;
    ModelRenderer LeftWing2;
    ModelRenderer LeftWing3;
    ModelRenderer LeftWing4;
    ModelRenderer LeftWing5;
    ModelRenderer LeftWing6;
    ModelRenderer LeftWing7;
    ModelRenderer LeftWing8;
    ModelRenderer LeftWing9;
    ModelRenderer LeftWing10;
    ModelRenderer LeftWing11;
    ModelRenderer RightWing1;
    ModelRenderer RightWing2;
    ModelRenderer RightWing3;
    ModelRenderer RightWing4;
    ModelRenderer RightWing5;
    ModelRenderer RightWing6;
    ModelRenderer RightWing7;
    ModelRenderer RightWing8;
    ModelRenderer RightWing9;
    ModelRenderer RightWing10;
    ModelRenderer RightWing11;
    ModelRenderer Piece1;
    ModelRenderer Piece2;
	 

    private ResourceLocation tex =new ResourceLocation("lilithscythemod:textures/items/armor/LilithWing.png");
    public ArmorLilithWing()
    {
     
    	textureWidth = 64;
        textureHeight = 64;
        
          LeftWing1 = new ModelRenderer(this, 0, 0);
          LeftWing1.addBox(1F, 0F, -0.8F, 4, 1, 1);
          LeftWing1.setRotationPoint(0F, 0F, 3F);
          LeftWing1.setTextureSize(64, 64);
          LeftWing1.mirror = true;
          setRotation(LeftWing1, 0F, 0F, 0.3490659F);
          LeftWing2 = new ModelRenderer(this, 0, 3);
          LeftWing2.addBox(4.4F, 1.7F, -0.8F, 3, 1, 1);
          LeftWing2.setRotationPoint(0F, 0F, 3F);
          LeftWing2.setTextureSize(64, 64);
          LeftWing2.mirror = true;
          setRotation(LeftWing2, 0F, 0F, 0F);
          LeftWing3 = new ModelRenderer(this, 0, 6);
          LeftWing3.addBox(5F, 5F, -0.8F, 3, 1, 1);
          LeftWing3.setRotationPoint(0F, 0F, 3F);
          LeftWing3.setTextureSize(64, 64);
          LeftWing3.mirror = true;
          setRotation(LeftWing3, 0F, 0F, -0.5235988F);
          LeftWing4 = new ModelRenderer(this, 0, 9);
          LeftWing4.addBox(7F, 5F, -0.8F, 1, 6, 1);
          LeftWing4.setRotationPoint(0F, 0F, 3F);
          LeftWing4.setTextureSize(64, 64);
          LeftWing4.mirror = true;
          setRotation(LeftWing4, 0F, 0F, -0.5235988F);
          LeftWing5 = new ModelRenderer(this, 5, 10);
          LeftWing5.addBox(4F, 1F, -0.8F, 1, 4, 1);
          LeftWing5.setRotationPoint(0F, 0F, 3F);
          LeftWing5.setTextureSize(64, 64);
          LeftWing5.mirror = true;
          setRotation(LeftWing5, 0F, 0F, 0.1745329F);
          LeftWing6 = new ModelRenderer(this, 0, 17);
          LeftWing6.addBox(6.1F, 3F, -0.8F, 1, 4, 1);
          LeftWing6.setRotationPoint(0F, 0F, 3F);
          LeftWing6.setTextureSize(64, 64);
          LeftWing6.mirror = true;
          setRotation(LeftWing6, 0F, 0F, -0.1745329F);
          LeftWing7 = new ModelRenderer(this, 0, 23);
          LeftWing7.addBox(1F, 0.3F, -0.2F, 4, 4, 0);
          LeftWing7.setRotationPoint(0F, 0F, 3F);
          LeftWing7.setTextureSize(64, 64);
          LeftWing7.mirror = true;
          setRotation(LeftWing7, 0F, 0F, 0.3490659F);
          LeftWing8 = new ModelRenderer(this, 0, 28);
          LeftWing8.addBox(4.4F, 2.7F, -0.2F, 3, 1, 0);
          LeftWing8.setRotationPoint(0F, 0F, 3F);
          LeftWing8.setTextureSize(64, 64);
          LeftWing8.mirror = true;
          setRotation(LeftWing8, 0F, 0F, 0F);
          LeftWing9 = new ModelRenderer(this, 0, 30);
          LeftWing9.addBox(4.2F, 3.7F, -0.2F, 3, 1, 0);
          LeftWing9.setRotationPoint(0F, 0F, 3F);
          LeftWing9.setTextureSize(64, 64);
          LeftWing9.mirror = true;
          setRotation(LeftWing9, 0F, 0F, 0F);
          LeftWing10 = new ModelRenderer(this, 0, 32);
          LeftWing10.addBox(3.8F, 4.7F, -0.2F, 4, 1, 0);
          LeftWing10.setRotationPoint(0F, 0F, 3F);
          LeftWing10.setTextureSize(64, 64);
          LeftWing10.mirror = true;
          setRotation(LeftWing10, 0F, 0F, 0F);
          LeftWing11 = new ModelRenderer(this, 0, 34);
          LeftWing11.addBox(5F, 5F, -0.2F, 3, 5, 0);
          LeftWing11.setRotationPoint(0F, 0F, 3F);
          LeftWing11.setTextureSize(64, 64);
          LeftWing11.mirror = true;
          setRotation(LeftWing11, 0F, 0F, -0.4363323F);
          RightWing1 = new ModelRenderer(this, 16, 0);
          RightWing1.addBox(-5F, 0F, -0.8F, 4, 1, 1);
          RightWing1.setRotationPoint(0F, 0F, 3F);
          RightWing1.setTextureSize(64, 64);
          RightWing1.mirror = true;
          setRotation(RightWing1, 0F, 0F, -0.3490659F);
          RightWing2 = new ModelRenderer(this, 16, 3);
          RightWing2.addBox(-7.4F, 1.6F, -0.8F, 3, 1, 1);
          RightWing2.setRotationPoint(0F, 0F, 3F);
          RightWing2.setTextureSize(64, 64);
          RightWing2.mirror = true;
          setRotation(RightWing2, 0F, 0F, 0F);
          RightWing3 = new ModelRenderer(this, 16, 6);
          RightWing3.addBox(-8.2F, 4.9F, -0.8F, 3, 1, 1);
          RightWing3.setRotationPoint(0F, 0F, 3F);
          RightWing3.setTextureSize(64, 64);
          RightWing3.mirror = true;
          setRotation(RightWing3, 0F, 0F, 0.5235988F);
          RightWing4 = new ModelRenderer(this, 16, 9);
          RightWing4.addBox(-8.2F, 4.9F, -0.8F, 1, 6, 1);
          RightWing4.setRotationPoint(0F, 0F, 3F);
          RightWing4.setTextureSize(64, 64);
          RightWing4.mirror = true;
          setRotation(RightWing4, 0F, 0F, 0.5235988F);
          RightWing5 = new ModelRenderer(this, 21, 10);
          RightWing5.addBox(-5.2F, 0.9F, -0.8F, 1, 4, 1);
          RightWing5.setRotationPoint(0F, 0F, 3F);
          RightWing5.setTextureSize(64, 64);
          RightWing5.mirror = true;
          setRotation(RightWing5, 0F, 0F, -0.1745329F);
          RightWing6 = new ModelRenderer(this, 16, 17);
          RightWing6.addBox(-7.2F, 2.9F, -0.8F, 1, 4, 1);
          RightWing6.setRotationPoint(0F, 0F, 3F);
          RightWing6.setTextureSize(64, 64);
          RightWing6.mirror = true;
          setRotation(RightWing6, 0F, 0F, 0.1745329F);
          RightWing7 = new ModelRenderer(this, 16, 23);
          RightWing7.addBox(-5F, 0F, -0.3F, 4, 4, 0);
          RightWing7.setRotationPoint(0F, 0F, 3F);
          RightWing7.setTextureSize(64, 64);
          RightWing7.mirror = true;
          setRotation(RightWing7, 0F, 0F, -0.3490659F);
          RightWing8 = new ModelRenderer(this, 16, 28);
          RightWing8.addBox(-7.4F, 2.6F, -0.3F, 3, 1, 0);
          RightWing8.setRotationPoint(0F, 0F, 3F);
          RightWing8.setTextureSize(64, 64);
          RightWing8.mirror = true;
          setRotation(RightWing8, 0F, 0F, 0F);
          RightWing9 = new ModelRenderer(this, 16, 30);
          RightWing9.addBox(-7.4F, 3.6F, -0.3F, 3, 1, 0);
          RightWing9.setRotationPoint(0F, 0F, 3F);
          RightWing9.setTextureSize(64, 64);
          RightWing9.mirror = true;
          setRotation(RightWing9, 0F, 0F, 0F);
          RightWing10 = new ModelRenderer(this, 16, 32);
          RightWing10.addBox(-7.6F, 4.6F, -0.3F, 4, 1, 0);
          RightWing10.setRotationPoint(0F, 0F, 3F);
          RightWing10.setTextureSize(64, 64);
          RightWing10.mirror = true;
          setRotation(RightWing10, 0F, 0F, 0F);
          RightWing11 = new ModelRenderer(this, 16, 34);
          RightWing11.addBox(-8.2F, 4.9F, -0.5F, 3, 5, 0);
          RightWing11.setRotationPoint(0F, 0F, 3F);
          RightWing11.setTextureSize(64, 64);
          RightWing11.mirror = true;
          setRotation(RightWing11, 0F, 0F, 0.4363323F);
          
          Piece1 = new ModelRenderer(this, "Piece1");
	      Piece1.setRotationPoint(0F, 0F, 0F);
	      setRotation(Piece1, 0F, 0F, 0F);
	      Piece1.mirror = false;
	      Piece1.addChild(LeftWing1);
	      Piece1.addChild(LeftWing2);
	      Piece1.addChild(LeftWing3);
	      Piece1.addChild(LeftWing4);
	      Piece1.addChild(LeftWing5);
	      Piece1.addChild(LeftWing6);
	      Piece1.addChild(LeftWing7);
	      Piece1.addChild(LeftWing8);
	      Piece1.addChild(LeftWing9);
	      Piece1.addChild(LeftWing10);
	      Piece1.addChild(LeftWing11);
	      
	      Piece2 = new ModelRenderer(this, "Piece2");
	      Piece2.setRotationPoint(0F, 0F, 0F);
	      setRotation(Piece1, 0F, 0F, 0F);
	      Piece2.mirror = false;
	      
	      Piece2.addChild(RightWing1);
	      Piece2.addChild(RightWing2);
	      Piece2.addChild(RightWing3);
	      Piece2.addChild(RightWing4);
	      Piece2.addChild(RightWing5);
	      Piece2.addChild(RightWing6);
	      Piece2.addChild(RightWing7);
	      Piece2.addChild(RightWing8);
	      Piece2.addChild(RightWing9);
	      Piece2.addChild(RightWing10);
	      Piece2.addChild(RightWing11);
	      
	     this.bipedBody.addChild(Piece1);
	     this.bipedBody.addChild(Piece2);
	      
    
    
    	
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      mc.renderEngine.bindTexture(tex);
      this.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
      Piece1.render(f5);
      Piece2.render(f5);

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
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
    		 float f6=0;
    		 f6 = MathHelper.cos(MathHelper.sqrt_float(p_78087_3_)*6.0F)/2;
    		 if(f6>0){
    			 f6*=-1;
    		 }
    		 Piece1.rotateAngleY=f6;
    		 Piece2.rotateAngleY=f6*-1;
    }
 
}
