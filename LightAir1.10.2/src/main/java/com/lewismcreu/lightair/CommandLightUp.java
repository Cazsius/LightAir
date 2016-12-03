package com.lewismcreu.lightair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class CommandLightUp extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "lightup";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return I18n.format("command.lightup.usage");
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender,
			String[] args) throws CommandException
	{
		EntityPlayer player = getCommandSenderAsPlayer(sender);
		Chunk chunk = player.getEntityWorld()
				.getChunkFromBlockCoords(player.getPosition());

		IBlockState target = CommonProxy.blockLightAir.getStateFromMeta(15);

		if (args.length > 0 && !Boolean.parseBoolean(args[0]))
		{
			target = Blocks.AIR.getDefaultState();
		}

		final IBlockState state = target;

		player.getEntityWorld().getMinecraftServer().addScheduledTask(() -> {
			for (int x = 0; x < 16; x++)
				for (int y = 0; y < 256; y++)
					for (int z = 0; z < 16; z++)
					{
						BlockPos n = new BlockPos(x, y, z).add(
								chunk.xPosition * 16, 0, chunk.zPosition * 16);
						if (player.getEntityWorld().isAirBlock(n)
								&& hasAdjacent(player.getEntityWorld(), n))
						{
							player.getEntityWorld().setBlockState(n, state, 3);
						}
					}
		});
	}

	private static boolean hasAdjacent(World world, BlockPos pos)
	{
		return !world.isAirBlock(pos.down()) || !world.isAirBlock(pos.up())
				|| !world.isAirBlock(pos.east())
				|| !world.isAirBlock(pos.north())
				|| !world.isAirBlock(pos.south())
				|| !world.isAirBlock(pos.west());
	}
}
