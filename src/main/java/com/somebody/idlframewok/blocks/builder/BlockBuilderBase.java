package com.somebody.idlframewok.blocks.builder;

import com.somebody.idlframewok.Idealland;
import com.somebody.idlframewok.blocks.BlockBase;
import com.somebody.idlframewok.blocks.tileEntity.builder.TileEntityBuilderBase;
import com.somebody.idlframewok.init.ModCreativeTabsList;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockBuilderBase extends BlockBase implements ITileEntityProvider {

    Class<? extends TileEntityBuilderBase> tileEntity;

    public BlockBuilderBase(String name, Material material, Class<? extends TileEntityBuilderBase> tileEntity) {
        super(name, material);
        this.tileEntity = tileEntity;
        setCreativeTab(ModCreativeTabsList.IDL_MISC);
        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(15.0F);
        setHarvestLevel("pickaxe", 3);
        setLightOpacity(1);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntityBuilderBase createNewTileEntity(World worldIn, int meta) {
        TileEntityBuilderBase t = null;
        try {
            t = tileEntity.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Idealland.Log("Instantiate failed");
        }
        return t;
    }
}
