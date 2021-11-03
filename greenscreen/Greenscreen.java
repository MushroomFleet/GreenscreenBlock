package me.tomino.greenscreen;

import api.config.BlockConfig;
import api.mod.StarMod;
import api.utils.textures.StarLoaderTexture;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.FactoryResource;

import javax.imageio.ImageIO;
import java.io.IOException;



public class Greenscreen extends StarMod {
    //Global Variables
    StarLoaderTexture greenscreenTexture;

    public void onEnable() {
        //Create Material
        try {
            String textureRoot = "me/tomino/greenscreen/textures/";
            //BufferedImage bump = ImageIO.read(getJarResource(textureRoot + "green.png"));
            greenscreenTexture = StarLoaderTexture.newBlockTexture(
                    ImageIO.read(getJarResource(textureRoot + "green.png"))/*,bump, 2044*/);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBlockConfigLoad(BlockConfig blockConfig) {
        //greenscreen block
        ElementInformation greenScreen = createBlock((short) greenscreenTexture.getTextureId(), me.tomino.greenscreen.blockID.carvedVaris,
                "Greenscreen", "special effects",
                0.1f, 0.1f,
                true, -1);

        FactoryResource[] customRecipe = new FactoryResource[]
                {
                        new FactoryResource(1, me.tomino.greenscreen.blockID.varis),
                        new FactoryResource(1, me.tomino.greenscreen.blockID.greenPaint)
                };
        BlockConfig.addRecipe(greenScreen, me.tomino.greenscreen.factoryID.basicFactory, 5/*Bake Time*/, customRecipe);


        //Add Blocks
        BlockConfig.add(greenScreen);

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

