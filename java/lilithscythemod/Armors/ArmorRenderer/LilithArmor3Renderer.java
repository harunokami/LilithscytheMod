package lilithscythemod.Armors.ArmorRenderer;

import lilithscythemod.Armors.ArmorModel.ArmorLilithWing;
import lilithscythemod.Entity.EntityLoveHeart;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class LilithArmor3Renderer extends RenderPlayer{

	//テクスチャ登録
	 private static final ResourceLocation LilithWingTextures = new ResourceLocation("lilithscythemod:textures/Armor/LilithWing3.png");
	//Render登録
	protected ModelBase modelWing;
	

	public void renderArmor(AbstractClientPlayer par1Entity, double par2, double par4, double par6, float par8, float par9)
   {
		 ArmorLilithWing model =  (ArmorLilithWing)this.modelWing;

		this.bindEntityTexture(par1Entity);
		this.setRenderPassModel(model);
		this.shouldRenderPass(par1Entity,1,1.0F);
		
       GL11.glPushMatrix();
       GL11.glDisable(GL11.GL_LIGHTING);
       GL11.glEnable(GL12.GL_RESCALE_NORMAL);
       GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
       GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6);
       GL11.glRotatef(par1Entity.prevRotationYaw + (par1Entity.rotationYaw - par1Entity.prevRotationYaw) * par9, 0.0F, 1.0F, 0.0F);
       GL11.glRotatef(par1Entity.prevRotationPitch + (par1Entity.rotationPitch - par1Entity.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
       GL11.glScalef(1.0F, -1.0F, -1.0F);
       model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
       GL11.glDisable(GL12.GL_RESCALE_NORMAL);
       GL11.glEnable(GL11.GL_LIGHTING);
       GL11.glPopMatrix();
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
   }

	
	protected ResourceLocation getArrowTextures(EntityLoveHeart par1EntityArrow)
	{
	        return LilithWingTextures;
	}
	
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return this.getArrowTextures((lilithscythemod.Entity.EntityLoveHeart)p_110775_1_);
	}
	
	public void doRender(AbstractClientPlayer p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_)
	{
		this.renderArmor(p_76986_1_,p_76986_2_,p_76986_4_, p_76986_6_, p_76986_8_,p_76986_9_);

	}


}
