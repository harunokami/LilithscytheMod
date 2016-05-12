package lilithscythemod.ItemsRenderer;

import lilithscythemod.Weapons.WeaponsModel.LilithscytheModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class LilithscytheRenderer implements IItemRenderer{
	  //変数
		private LilithscytheModel lilithscythemodel;
		private static Minecraft mc = Minecraft.getMinecraft();
		private ResourceLocation tex =new ResourceLocation("lilithscythemod:textures/items/LilithscytheModel.png");
	    public LilithscytheRenderer()
	    {
	    	lilithscythemodel = new LilithscytheModel();
	    }

		@Override
		public boolean handleRenderType(ItemStack item, ItemRenderType type) {
			
			 return type == ItemRenderType.EQUIPPED||type == ItemRenderType.EQUIPPED_FIRST_PERSON;//装備時だけ
		}
		@Override
		public boolean shouldUseRenderHelper(ItemRenderType type,
				ItemStack item, ItemRendererHelper helper) {
			
			return false;
		}
		@Override
		public void renderItem(ItemRenderType type, ItemStack item,
				Object... data) {
			mc.renderEngine.bindTexture(tex);
		    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		    GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		    GL11.glPushMatrix();
		    GL11.glScalef(1F, 1F, 1F);
		    GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);//これくらいでバニラの剣と同じ角度
		    lilithscythemodel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.07F);//かなりでかいので，縮小
		    GL11.glPopMatrix();
		    GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}
}
