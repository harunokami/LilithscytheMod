package lilithscythemod.Entity.EntityModel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BloodCrossModel extends ModelBase
{
  //fields
    ModelRenderer Cross1;
    ModelRenderer Cross2;
    ModelRenderer Cross3;
    ModelRenderer Cross4;
  
  public BloodCrossModel()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Cross1 = new ModelRenderer(this, 14, 0);
      Cross1.addBox(-0.5F, -7F, 0F, 1, 15, 5);
      Cross1.setRotationPoint(0F, 0F, 0F);
      Cross1.setTextureSize(64, 64);
      Cross1.mirror = true;
      setRotation(Cross1, 0F, 0F, -0.5235988F);
      Cross2 = new ModelRenderer(this, 0, 0);
      Cross2.addBox(-0.5F, -7F, 0F, 1, 15, 5);
      Cross2.setRotationPoint(0F, 0F, 0F);
      Cross2.setTextureSize(64, 64);
      Cross2.mirror = true;
      setRotation(Cross2, 0F, 0F, 0.5235988F);
      Cross3 = new ModelRenderer(this, 14, 22);
      Cross3.addBox(0F, -6F, 0F, 0, 13, 6);
      Cross3.setRotationPoint(0F, 0F, 0F);
      Cross3.setTextureSize(64, 64);
      Cross3.mirror = true;
      setRotation(Cross3, 0F, 0F, -0.5235988F);
      Cross4 = new ModelRenderer(this, 0, 22);
      Cross4.addBox(0F, -6F, 0F, 0, 13, 6);
      Cross4.setRotationPoint(0F, 0F, 0F);
      Cross4.setTextureSize(64, 64);
      Cross4.mirror = true;
      setRotation(Cross4, 0F, 0F, 0.5235988F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
   // super.render(entity, f, f1, f2, f3, f4, f5);
   // setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    Cross1.render(f5);
    Cross2.render(f5);
    Cross3.render(f5);
    Cross4.render(f5);
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