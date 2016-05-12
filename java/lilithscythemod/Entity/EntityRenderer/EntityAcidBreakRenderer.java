package lilithscythemod.Entity.EntityRenderer;

import lilithscythemod.Entity.EntityAcidBreak;
import lilithscythemod.Entity.EntityModel.BloodCrossModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class EntityAcidBreakRenderer extends Render{
	//弾のテクスチャ登録
    private static final ResourceLocation BloodCrossTextures = new ResourceLocation("lilithscythemod:textures/entity/BloodCrossModel.png");
	//弾のRender登録
	protected ModelBase modelBullet;
	public EntityAcidBreakRenderer(ModelBase par1ModelBase){
		super();
		this.modelBullet = par1ModelBase;
		this.shadowSize = 0.0F;
	}

	public void AcidBreakRender(EntityAcidBreak p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) {
		
	BloodCrossModel model = (BloodCrossModel)this.modelBullet;
	this.bindEntityTexture(p_76986_1_);
	int i=4;
	p_76986_1_.worldObj.spawnParticle("crit", p_76986_1_.posX + p_76986_1_.motionX * (double)i / 4.0D, p_76986_1_.posY + p_76986_1_.motionY * (double)i / 4.0D, p_76986_1_.posZ + p_76986_1_.motionZ * (double)i / 4.0D, -p_76986_1_.motionX, -p_76986_1_.motionY + 0.2D, -p_76986_1_.motionZ);
	GL11.glPushMatrix();
     GL11.glDisable(GL11.GL_LIGHTING);
     GL11.glEnable(GL12.GL_RESCALE_NORMAL);
     GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
     GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_ + 1.0F, (float)p_76986_6_);
     GL11.glRotatef(p_76986_1_.prevRotationYaw + (p_76986_1_.rotationYaw - p_76986_1_.prevRotationYaw) * p_76986_9_, 0.0F, 1.0F, 0.0F);
     GL11.glRotatef(p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
     GL11.glScalef(1.0F, -1.0F, -1.0F);
     model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
     GL11.glDisable(GL12.GL_RESCALE_NORMAL);
     GL11.glEnable(GL11.GL_LIGHTING);
     GL11.glPopMatrix();
	
	}
	protected ResourceLocation getBulletTextures(EntityAcidBreak par1EntityArrow)
	{
	        return BloodCrossTextures;
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		
		return this.getBulletTextures((EntityAcidBreak)p_110775_1_);
	}

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) 
	{
		this.AcidBreakRender((EntityAcidBreak)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}
