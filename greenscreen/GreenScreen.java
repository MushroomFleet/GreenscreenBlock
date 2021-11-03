package me.tomino.greenscreen;

import api.config.BlockConfig;
import api.mod.StarMod;
import api.utils.textures.StarLoaderTexture;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.FactoryResource;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenScreen extends StarMod
{
    int greenScreenTexture;

    public void onEnable()
    {
        String textureRoot = "me/tomino/greenscreen/textures/";
        try
        {
            greenScreenTexture = StarLoaderTexture.newBlockTexture(
                    ImageIO.read(getJarResource(textureRoot + "green.png"))).getTextureId();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void onBlockConfigLoad(BlockConfig blockConfig)
    {
        ElementInformation greenScreenBlock = createBlock(
                (short) greenScreenTexture, blockID.carvedVaris, "GreenScreen", "Special Effects",
                0.1f, 0.1f, false, 1000);
        FactoryResource[] customRecipe = new FactoryResource[]
                {
                        new FactoryResource(1, blockID.greenPaint),
                        new FactoryResource(1, blockID.varis)
                };
        BlockConfig.addRecipe(greenScreenBlock, factoryID.basicFactory,5,customRecipe);
        BlockConfig.add(greenScreenBlock);
    }

    //Basic stuff for creating a block
    public ElementInformation createBlock(short textureID, short iconID,
                                          String blockName, String blockDescription,
                                          float blockMass, float blockVolume,
                                          boolean craftOnly, int blockPrice) {
        ElementInformation block = BlockConfig.newElement(this, blockName, textureID);
        block.setBuildIconNum(iconID);
        block.description = blockDescription;
        block.mass = blockMass;
        block.volume = blockVolume;
        block.setShoppable(!craftOnly);
        block.setPrice(blockPrice);

        return block;
    }

}

class factoryID
{
    public static final short basicFactory = 3;
}

class blockID
{
    public static final short varis = 94;
    public static final short greenPaint = 183;
    public static final short carvedVaris = 96;
}