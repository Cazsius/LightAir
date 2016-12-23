package com.lewismcreu.lightair;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenGuiMessage implements IMessage
{
	public OpenGuiMessage()
	{}

	@Override
	public void fromBytes(ByteBuf buf)
	{}

	@Override
	public void toBytes(ByteBuf buf)
	{}

	public static class Handler
			implements IMessageHandler<OpenGuiMessage, IMessage>
	{
		@Override
		public IMessage onMessage(OpenGuiMessage message, MessageContext ctx)
		{
			if (ctx.side.isServer())
				ctx.getServerHandler().playerEntity.openGui(LightAir.instance,
						0, ctx.getServerHandler().playerEntity.worldObj, 0, 0, 0);

			return null;
		}
	}
}
