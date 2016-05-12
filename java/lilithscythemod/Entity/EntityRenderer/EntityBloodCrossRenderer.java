package lilithscythemod.Entity.EntityRenderer;

import lilithscythemod.Entity.EntityBloodCross;
import lilithscythemod.Entity.EntityModel.BloodCrossModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityBloodCrossRenderer extends Render{
	//弾のテクスチャ登録
    private static final ResourceLocation BloodCrossTextures = new ResourceLocation("lilithscythemod:textures/entity/BloodCrossModel.png");
	//弾のRender登録
	protected ModelBase modelBullet;
	public EntityBloodCrossRenderer(ModelBase par1ModelBase){
		super();
		this.modelBullet = par1ModelBase;
		this.shadowSize = 0.0F;
	}

	public void BloodCrossRender(EntityBloodCross p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) {
		
	BloodCrossModel model = (BloodCrossModel)this.modelBullet;
	this.bindEntityTexture(p_76986_1_);
	 
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
	protected ResourceLocation getBulletTextures(EntityBloodCross par1EntityArrow)
	{
	        return BloodCrossTextures;
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		
		return this.getBulletTextures((EntityBloodCross)p_110775_1_);
	}

	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,double p_76986_4_, double p_76986_6_, float p_76986_8_,float p_76986_9_) 
	{
		this.BloodCrossRender((EntityBloodCross)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

}
