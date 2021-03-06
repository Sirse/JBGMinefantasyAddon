package jaredbgreat.minefantasy.herbs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jaredbgreat.minefantasy.Info;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class AloeVeraPlant extends BlockHerb {

	protected AloeVeraPlant() {
		setBlockName(Info.ID + "-AloeVera");
		setBlockBounds(0.25f, 0f, 0.25f, 0.75f, 0.5f, 0.75f); // ???
        setBlockTextureName(Info.ID + ":Herb/AloeVera");
        setLightOpacity(0);
	}
    

    public void setBlockBoundsForItemRender()  {
    	setBlockBounds(0.25f, 0f, 0.25f, 0.75f, 0.5f, 0.75f);
    }


    protected void func_150089_b(int number) {
    	// This would be called instead of duplicated if Java had inlining.
        setBlockBounds(0.25f, 0f, 0.25f, 0.75f, 0.5f, 0.75f);
    }
    
    
    public boolean onBlockActivated(World world, int x, int y, int z, 
    								EntityPlayer player, int side, 
    								float fx, float fy, float fz) {
    	return this.beGathered(world, x, y, z, player, Herbs.aloe, Items.shears, 1, false);
    }
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ico) {
    	icons = new IIcon[2];
    	icons[0] = ico.registerIcon(Info.ID + ":Herb/AloeVera");
    	icons[1] = ico.registerIcon(Info.ID + ":Herb/AloeVera_harvested");
    }	
    
    
    @Override
    public boolean isRightSoil(Block ground) {
    	return ((ground == Blocks.sand) || (ground == Blocks.dirt));
    }
    
    
    @Override
    public boolean canPlaceBlockOn(Block ground) {
    	return ((ground == Blocks.sand) || (ground == Blocks.dirt)
    		    || (ground == Blocks.farmland));
    }
    
    
    @Override
    public int getSizeFactor() {
    	return 4;
    }
    
    
    @Override
    public boolean isGoodBiome(BiomeGenBase biome) {
    	return ((biome.rainfall < 0.1f) && (biome.temperature > 0.5f));
    }

}
