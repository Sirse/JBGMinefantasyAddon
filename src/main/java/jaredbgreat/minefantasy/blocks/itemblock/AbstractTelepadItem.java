package jaredbgreat.minefantasy.blocks.itemblock;

import jaredbgreat.minefantasy.blocks.AddonBlocks;
import jaredbgreat.minefantasy.blocks.special.AbstractTelepad;
import jaredbgreat.minefantasy.blocks.tileentities.TelepadBaseLogic;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AbstractTelepadItem extends ItemBlock {
	
	public AbstractTelepadItem(Block block) {
		super(block);
	}
	
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, 
			                    int x, int y, int z, int side, float hitX, float hitY, float hitZ, 
			                    int metadata) {
		if(world.getBlock(x, y-1, z).equals(AddonBlocks.landingpad)) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt == null) {
				nbt = new NBTTagCompound();
				stack.setTagCompound(nbt);
			}
			nbt.setDouble("targetX", ((double)x) + 0.5);
			nbt.setDouble("targetY", ((double)y) - AbstractTelepad.LEVEL);
			nbt.setDouble("targetZ", ((double)z) + 0.5);
			NBTTagList lore = new NBTTagList();	
			NBTTagCompound dis = nbt.getCompoundTag("display");
			dis.setTag("Lore", lore);		
			lore.appendTag(new NBTTagString("X=" + x + ", Y=" + y + ", Z=" + z));
			dis.setTag("Lore", lore);
			nbt.setTag("display", dis);
			stack.setTagCompound(nbt);
			return false;
		} else {
			boolean out = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
			TileEntity te = world.getTileEntity(x, y, z);
			if(te instanceof TelepadBaseLogic) {
				TelepadBaseLogic logic = (TelepadBaseLogic)te;
				NBTTagCompound nbt = stack.getTagCompound();
				if(nbt == null) {
					logic.setInactive();
					return out;
				}
				double targetX = nbt.getDouble("targetX");
				double targetY = nbt.getDouble("targetY");
				double targetZ = nbt.getDouble("targetZ");
				logic.setData(targetX, targetY, targetZ);
			}
			return out;
		}
	}

}
