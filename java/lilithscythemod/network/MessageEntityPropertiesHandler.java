package lilithscythemod.network;

import lilithscythemod.Skill.SkillNBTData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEntityPropertiesHandler implements IMessageHandler<MessageEntityProperties,IMessage>{

	@Override
	public IMessage onMessage(MessageEntityProperties message,
			MessageContext ctx) {
		  EntityPlayer player = ctx.getServerHandler().playerEntity;
	      EntityLivingBase targetEntity = message.getEntityFromId(player.worldObj);
	      SkillNBTData.get(targetEntity).loadNBTData(message.data);
		return null;
	}

}
