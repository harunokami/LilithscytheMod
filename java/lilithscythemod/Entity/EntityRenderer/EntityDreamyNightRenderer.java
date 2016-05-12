package lilithscythemod.Entity.EntityRenderer;

import lilithscythemod.Entity.EntityDreamyNight;
import lilithscythemod.Entity.EntityModel.LoveHeartModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDreamyNightRenderer extends Render{
	//弾のテクスチャ登録
		 private static final ResourceLocation LoveHeartTextures = new ResourceLocation("lilithscythemod:textures/entity/LoveHeartModel.png");
		//弾のRender登録
		protected ModelBase modelBullet;
		

	    public EntityDreamyNightRenderer(ModelBase par1ModelBase)
	    {
			super();
			this.modelBullet = par1ModelBase;
			this.shadowSize = 0.0F;
		}


		public void renderArrow(EntityDreamyNight par1Entity, double par2, double par4, double par6, float par8, float par9)
	    {
			LoveHeartModel model = (LoveHeartModel)this.modelBullet;

			this.bindEntityTexture(par1Entity);
			
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
	    }

		
		protected ResourceLocation getArrowTextures(EntityDreamyNight par1EntityArrow)
		{
		        return LoveHeartTextures;
		}
		
		protected ResourceLocation getEntityTexture(Entity p_110775_1_)
		{
			return this.getArrowTextures((lilithscythemod.Entity.EntityDreamyNight)p_110775_1_);
		}
		
		public void doRender(Entity p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_)
		{
			this.renderArrow((lilithscythemod.Entity.EntityDreamyNight)p_76986_1_,p_76986_2_,p_76986_4_, p_76986_6_, p_76986_8_,p_76986_9_);

		}
		
}
