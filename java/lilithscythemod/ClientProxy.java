package lilithscythemod;

import java.util.List;

import lilithscythemod.Armors.ArmorModel.ArmorLilithWing;
import lilithscythemod.Armors.ArmorModel.ArtemisEarModel;
import lilithscythemod.Entity.EntityAcidBreak;
import lilithscythemod.Entity.EntityBloodCross;
import lilithscythemod.Entity.EntityDreamyNight;
import lilithscythemod.Entity.EntityLoveHeart;
import lilithscythemod.Entity.EntityModel.BloodCrossModel;
import lilithscythemod.Entity.EntityModel.LoveHeartModel;
import lilithscythemod.ItemsRenderer.ArtemisBookRenderer;
import lilithscythemod.ItemsRenderer.LilithscytheRenderer;
import lilithscythemod.Weapons.LilithscytheWeapons;
import lilithscythemod.network.MessageExtendedReachAttack;
import lilithscythemod.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class ClientProxy extends CommonProxy{

	public static ArmorLilithWing ModelArmorLilithWing = new ArmorLilithWing();
	public static ArtemisEarModel ModelArtemisEar = new ArtemisEarModel();
	public static ModelBiped biped = new ModelBiped();
	public static Minecraft mc = Minecraft.getMinecraft();
	private static Timer timer = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, 16);

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	@Override
	 
	public EntityPlayer getEntityPlayerInstance() {
	 
	return Minecraft.getMinecraft().thePlayer;
	 
	}
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.Lilithscythe,new LilithscytheRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemRegistry.ArtemisBook,new ArtemisBookRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityLoveHeart.class, new lilithscythemod.Entity.EntityRenderer.EntityLoveHeartRenderer(new LoveHeartModel()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDreamyNight.class, new lilithscythemod.Entity.EntityRenderer.EntityDreamyNightRenderer(new LoveHeartModel()));
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidBreak.class,new lilithscythemod.Entity.EntityRenderer.EntityAcidBreakRenderer(new BloodCrossModel()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodCross.class,new lilithscythemod.Entity.EntityRenderer.EntityBloodCrossRenderer(new BloodCrossModel()));
	}
	
	    
	    @SubscribeEvent
	    public void mouseHandlingEvent(InputEvent.MouseInputEvent event) {
	        if (mc.gameSettings.keyBindAttack.getIsKeyPressed() && mc.thePlayer != null) {
	            changeObjectMoouseOver(mc.thePlayer);
	        }
	    }
	    
	    private void changeObjectMoouseOver(EntityPlayer player) 
	    {
	        ItemStack heldItem = player.getCurrentEquippedItem();
	        if (heldItem != null && heldItem.getItem() instanceof LilithscytheWeapons) {
	        	double extendedReach = ((LilithscytheWeapons)heldItem.getItem()).getExtendedReach(heldItem);
	            MovingObjectPosition MOP = getMouseOverSpecialReach(player, extendedReach, timer.renderPartialTicks);
	            if (MOP != null && MOP.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
	                Entity pointedEntity = MOP.entityHit;
	                if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
	                    PacketHandler.INSTANCE.sendToServer(new MessageExtendedReachAttack(pointedEntity));
	                }
	            }
	        }
	    }
	    
	    private MovingObjectPosition getMouseOverSpecialReach(EntityLivingBase viewingEntity, double reach, float partialTicks) 
	    {
	        MovingObjectPosition MOP = null;
	        if (viewingEntity != null) {
	            if (viewingEntity.worldObj != null) {
	                MOP = viewingEntity.rayTrace(reach, partialTicks);
	                Vec3 viewPosition = viewingEntity.getPosition(partialTicks);
	                double d1 = 0;

	                if (MOP != null) {
	                    d1 = MOP.hitVec.distanceTo(viewPosition);
	                }

	                Vec3 lookVector = viewingEntity.getLook(partialTicks);
	                Vec3 reachVector = viewPosition.addVector(lookVector.xCoord * reach, lookVector.yCoord * reach, lookVector.zCoord * reach);
	                Vec3 vec33 = null;
	                float f1 = 1.0F;
	                @SuppressWarnings("unchecked")
	                List<Entity> list = viewingEntity.worldObj.getEntitiesWithinAABBExcludingEntity(viewingEntity, viewingEntity.boundingBox.addCoord(lookVector.xCoord * reach, lookVector.yCoord * reach, lookVector.zCoord * reach).expand(f1, f1, f1));
	                double d2 = d1;
	                Entity pointedEntity = null;
	                for (Entity entity : list) {
	                    if (entity.canBeCollidedWith())  {
	                        float collisionSize = entity.getCollisionBorderSize();
	                        AxisAlignedBB axisalignedbb = entity.boundingBox.expand(collisionSize, collisionSize, collisionSize);
	                        MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(viewPosition, reachVector);

	                        if (axisalignedbb.isVecInside(viewPosition)) {
	                            if (0.0D < d2 || d2 == 0.0D) {
	                                pointedEntity = entity;
	                                vec33 = movingobjectposition == null ? viewPosition : movingobjectposition.hitVec;
	                                d2 = 0.0D;
	                            }
	                        } else if (movingobjectposition != null)  {
	                            double d3 = viewPosition.distanceTo(movingobjectposition.hitVec);

	                            if (d3 < d2 || d2 == 0.0D) {
	                                if (entity == viewingEntity.ridingEntity && !entity.canRiderInteract()) {
	                                    if (d2 == 0.0D) {
	                                        pointedEntity = entity;
	                                        vec33 = movingobjectposition.hitVec;
	                                    }
	                                } else {
	                                    pointedEntity = entity;
	                                    vec33 = movingobjectposition.hitVec;
	                                    d2 = d3;
	                                }
	                            }
	                        }
	                    }
	                }

	                if (pointedEntity != null && (d2 < d1 || MOP == null)) {
	                    MOP = new MovingObjectPosition(pointedEntity, vec33);
	                }
	            }
	        }
	        return MOP;
	   }

}
