package lilithscythemod.Entity.EntityModel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LoveHeartModel extends ModelBase
{
	//fields
    ModelRenderer hyoukai1;
    ModelRenderer hyoukai2;
    ModelRenderer hyoukai3;
    ModelRenderer hyoukai4;
  
  public LoveHeartModel()
  {
	  
	  textureWidth = 64;
	    textureHeight = 64;
	    
	      hyoukai1 = new ModelRenderer(this, 0, 0);
	      hyoukai1.addBox(-4F, -5F, -4F, 6, 6, 6);
	      hyoukai1.setRotationPoint(0F, -1F, 0F);
	      hyoukai1.setTextureSize(64, 64);
	      hyoukai1.mirror = true;
	      setRotation(hyoukai1, 0.7853982F, 0.7853982F, 0.7853982F);
	      hyoukai2 = new ModelRenderer(this, 26, 0);
	      hyoukai2.addBox(9F, 0F, 0F, 3, 3, 3);
	      hyoukai2.setRotationPoint(0F, 0F, 0F);
	      hyoukai2.setTextureSize(64, 64);
	      hyoukai2.mirror = true;
	      setRotation(hyoukai2, 0.7853982F, 0.7853982F, 0.7853982F);
	      hyoukai3 = new ModelRenderer(this, 0, 14);
	      hyoukai3.addBox(-8F, -1F, -9F, 2, 2, 2);
	      hyoukai3.setRotationPoint(0F, 0F, 0F);
	      hyoukai3.setTextureSize(64, 64);
	      hyoukai3.mirror = true;
	      setRotation(hyoukai3, 0.7853982F, 0.7853982F, 0.7853982F);
	      hyoukai4 = new ModelRenderer(this, 11, 14);
	      hyoukai4.addBox(-12F, 4F, -1F, 3, 3, 3);
	      hyoukai4.setRotationPoint(0F, 0F, 0F);
	      hyoukai4.setTextureSize(64, 64);
	      hyoukai4.mirror = true;
	      setRotation(hyoukai4, 0.7853982F, 0.7853982F, 0.7853982F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
		  super.render(entity, f, f1, f2, f3, f4, f5);
		    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		    hyoukai1.render(f5);
		    hyoukai2.render(f5);
		    hyoukai3.render(f5);
		    hyoukai4.render(f5);
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
  //飛ばしたエンティティが回転しながら飛んで行く。
    
    this.hyoukai1.rotateAngleZ += 0.7F / (180F / (float)Math.PI);
    this.hyoukai2.rotateAngleZ += 0.7F / (180F / (float)Math.PI);
    this.hyoukai3.rotateAngleZ += 0.7F / (180F / (float)Math.PI);
    this.hyoukai4.rotateAngleZ += 0.7F / (180F / (float)Math.PI);
    this.hyoukai1.rotateAngleX += 0.7F / (180F / (float)Math.PI);
    this.hyoukai2.rotateAngleX += 0.7F / (180F / (float)Math.PI);
    this.hyoukai3.rotateAngleX += 0.7F / (180F / (float)Math.PI);
    this.hyoukai4.rotateAngleX += 0.7F / (180F / (float)Math.PI);
  }
}
