package lilithscythemod.Potion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lilithscythemod.ModCore;
import lilithscythemod.Enum.EnumPotionEffectResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionEventHandler
{
    /**変数*/
	public final ResourceLocation field_147001_a = new ResourceLocation("textures/gui/container/inventory.png");
    private Gui gui = new Gui();
	private GuiScreen screen = new GuiScreen();
	private Minecraft mc = FMLClientHandler.instance().getClient();
	private List<String> PotionList=new ArrayList();
	private EntityPlayer player;
	private List<String> AvtivePotionlist=new ArrayList();

	double testHP=0;
	/**Event*/
	@SubscribeEvent
	public void entityUpdate(LivingUpdateEvent event)
	{

		EntityLivingBase target = event.entityLiving;

			if (PotionNBTList.getNBT(target) != null)
			{
				PotionNBTList list = PotionNBTList.getNBT(target);
				NBTTagList potions = list.getNBTList();

				if (target.ticksExisted % 20 == 0
						&& list.getNBTList().tagCount() > 0)
				{
					for (int x = 0; x < potions.tagCount(); x++)
					{
						int powerLevel = list.getNBTInt(x, "powerLevel");
						float duration = list.getNBTFloat(x, "duration");
						String potionName = list.getNBTString(x, "name");
						applyEffect(potionName, target, powerLevel);
						if (duration != -1)
							list.setNBTFloat(x, "duration", duration - 1);
						if (duration <= 0 && duration != -1)
							potions.removeTag(x);
					}
					if(target instanceof EntityPlayer){
						player=(EntityPlayer)target;
						PotionList=PotionEffectManager.getActiveEffectNameList(player);
					}
				}
			}

	}

	@SubscribeEvent
	public void milkDrink(PlayerUseItemEvent.Finish itemUse)
	{
		PotionNBTList activeEffects = PotionNBTList.getNBT(itemUse.entityPlayer);

		if (itemUse.item.getItem() == Items.milk_bucket)
		{
			int effectActive = activeEffects.getNBTList().tagCount();
			System.out.println(effectActive);
			while (effectActive > 0)
			{
				String effectName = activeEffects.getCompoundFromList(0).getString("name");
				System.out.println(effectName);
				if (PotionEffectManager.getPotion(effectName).milkRemove() == true)
				{
					PotionEffectManager.removeEffects(itemUse.entityPlayer, effectName, false);
					effectActive--;
				}else{
					effectActive--;
				}
			}
		}

	}


	@SubscribeEvent(priority = EventPriority.HIGH)
	public void entityPotionCreate(EntityConstructing create)
	{
		if (create.entity instanceof EntityLivingBase && PotionNBTList.getNBT((EntityLivingBase) create.entity) == null)
		{
			PotionNBTList.register((EntityLivingBase) create.entity);
			if(create.entity instanceof EntityPlayer){
				PotionList.clear();
				PotionEffectManager.activeEffectNameList((EntityLivingBase)create.entity);
				
			}
		}

	}

	private void applyEffect(String potionName, EntityLivingBase target, int powerLevel)
	{
		PotionEffectManager.getPotionList().get(potionName).effect(target, powerLevel);
	}

	@SubscribeEvent
	public void entityDeathEffect(LivingDeathEvent event){

		if(event.entity instanceof EntityPlayer){
			EntityPlayer target =(EntityPlayer)event.entity;
			PotionEffectManager.removeAllEffects(target);
		}

	}


	/**アクティブなポーション効果をアイコンで表示するEvent*/
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		  public void onRenderGameOverlayEvent(RenderGameOverlayEvent.Text event)

		{
			/** The X size of the inventory window in pixels. */
		    int xSize = 176;
		   /** The Y size of the inventory window in pixels. */
		    int ySize = 166;
		    int width = event.resolution.getScaledWidth();
		    int height = event.resolution.getScaledHeight();
		   /**GUiのサイズをウィンドウにあわせて変える*/
		    int guiLeft =(width - xSize) / 2;
		    int guiTop =(height - ySize) / 2;
		    int i = guiLeft-124;
		    int j = guiTop;
		    int effectCount=0;
		    boolean flag = true;

	        /**追加ポーションのアイコン表示*/
	        String S1="";
	        String duration = "";
	        String test = "";
	        double IconBaseLocate = 0;
	        int Iconindex = 0;
		    int IconSize =16;

	        Collection collection = mc.thePlayer.getActivePotionEffects();
			AvtivePotionlist = PotionList;

	        if (!collection.isEmpty())
	        {
	            for (Iterator iterator = mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); i += IconSize)
	            {
	                PotionEffect potioneffect = (PotionEffect)iterator.next();
	                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
	                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	                mc.getTextureManager().bindTexture(field_147001_a);

	                if (potion.hasStatusIcon())
	                {
	                	int l = potion.getStatusIconIndex();
	                    gui.drawTexturedModalRect(i + guiLeft+1, 0, 0 + l % 8 * 18, 198 + l / 8 * 18, IconSize, IconSize);
	                    effectCount+=l;
	                    Iconindex++;
	                }

	                if (!potion.shouldRenderInvText(potioneffect)) continue;

	            }

	        }

	        if(!AvtivePotionlist.isEmpty())
	        {
	        	for(int count = 0;AvtivePotionlist.size()>count;count++)
	        	{
	        		ResourceLocation Location = EnumPotionEffectResourceLocation.getPotioneffectLocation(ModCore.MODID, AvtivePotionlist.get(count));


	        			IconBaseLocate = (IconSize+0.5) * Iconindex + guiLeft;
	        			Iconindex++;
	        			//duration = PotionEffectManager.getDurationString(PotionEffectManager.getPotionDuration(mc.thePlayer,AvtivePotionlist.get(count)));
	        			duration = PotionEffectManager.getDurationString(PotionEffectManager.getPotionDuration(player,AvtivePotionlist.get(count)));
	        			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        			mc.getTextureManager().bindTexture(Location);
	        			S1 =I18n.format(AvtivePotionlist.get(count), new Object[0]);

	        			//描画する
	        			Tessellator te = Tessellator.instance;
	        			te.startDrawingQuads();
	        			te.addVertexWithUV(IconBaseLocate,0, 0, 0, 0); //Bottom left texture

	        			te.addVertexWithUV(IconBaseLocate,IconSize, 0, 0, 1); //Top left

	        			te.addVertexWithUV(IconBaseLocate+IconSize,IconSize, 0, 1, 1); //Top right

	        			te.addVertexWithUV(IconBaseLocate+IconSize, 0, 0, 1, 0); //Bottom right
	        			te.draw();
	        			mc.fontRenderer.drawStringWithShadow(duration, (int)IconBaseLocate,IconSize-5, 16777215);
	        	}
	        }
	      //debug
	      /*  test = String.valueOf(mc.thePlayer.getMaxHealth());
	        mc.fontRenderer.drawStringWithShadow(test, (int)0,IconSize-5, 16777215);
	        test = String.valueOf(mc.thePlayer.getHealth());
	        mc.fontRenderer.drawStringWithShadow(test, (int)0,IconSize*3-5, 16777215);
	        test = String.valueOf(testHP);
	        mc.fontRenderer.drawStringWithShadow(test, (int)0,IconSize*5-5, 16777215);
	        test = String.valueOf(ModCore.TestState);
	        mc.fontRenderer.drawStringWithShadow(test, (int)0,IconSize*7-5, 16777215);
*/
		}




}
