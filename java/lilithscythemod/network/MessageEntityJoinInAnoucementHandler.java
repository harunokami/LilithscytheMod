package lilithscythemod.network;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageEntityJoinInAnoucementHandler implements IMessageHandler<MessageEntityJoinInAnnouncement, MessageEntityProperties> {

	@Override
	public MessageEntityProperties onMessage(
			MessageEntityJoinInAnnouncement message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        EntityLivingBase targetEntity = message.getEntityFromId(player.worldObj);
        
		return new MessageEntityProperties(targetEntity);
	}

}
