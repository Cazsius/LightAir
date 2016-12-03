package com.lewismcreu.lightair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

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

		Collection<ChunkPos> chunks = new ArrayList<>();

		Consumer<BlockPos> taskPerBlock;

		if (args.length > 0 && !Boolean.parseBoolean(args[0]))
			taskPerBlock = (BlockPos b) -> {
				if (player.getEntityWorld().getBlockState(b)
						.getBlock() == CommonProxy.blockLightAir)
					player.getEntityWorld().setBlockToAir(b);
			};
		else taskPerBlock = (BlockPos b) -> {
			if (player.getEntityWorld().isAirBlock(b)
					&& hasAdjacent(player.getEntityWorld(), b))
				player.getEntityWorld().setBlockState(b,
						CommonProxy.blockLightAir.getStateFromMeta(15), 3);
		};

		ChunkPos pos = player.getEntityWorld()
				.getChunkFromBlockCoords(player.getPosition())
				.getChunkCoordIntPair();
		
		if (args.length > 1)
		{
			int radius = parseInt(args[1], 0,
					LightAir.instance.config.getMaxChunkRadius());

			if (radius > 0)
			{
				for (int x = -radius; x < radius + 1; x++)
					for (int z = -radius; z < radius + 1; z++)
						chunks.add(new ChunkPos(pos.chunkXPos + x,
								pos.chunkZPos + z));
			}
		}
		else chunks.add(pos);

		Consumer<ChunkPos> taskPerChunk = (ChunkPos p) -> {
			for (int x = 0; x < 16; x++)
				for (int y = 0; y < 256; y++)
					for (int z = 0; z < 16; z++)
						taskPerBlock.accept(new BlockPos(x, y, z)
								.add(p.chunkXPos * 16, 0, p.chunkZPos * 16));
		};

		player.worldObj.getMinecraftServer().addScheduledTask(() -> {
			for (ChunkPos p : chunks)
				taskPerChunk.accept(p);
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
