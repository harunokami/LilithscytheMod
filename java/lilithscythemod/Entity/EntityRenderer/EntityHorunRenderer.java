package lilithscythemod.Entity.EntityRenderer;

import lilithscythemod.Entity.EntityModel.EntityHorunModel;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityHorunRenderer extends RenderLiving {
	
	public EntityHorunRenderer() {
		 super(new EntityHorunModel(), 1.0F);
		 this.setRenderPassModel(new EntityHorunModel());
    }
	public static final ResourceLocation texture = new ResourceLocation("lilithscythemod:textures/entitymob/SmollHolnn2.png");
	@Override
	public void doRender(Entity p_76986_1_, double p_76986_2_,
			double p_76986_4_, double p_76986_6_, float p_76986_8_,
			float p_76986_9_) {
        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
	
		return texture;
	}

}
