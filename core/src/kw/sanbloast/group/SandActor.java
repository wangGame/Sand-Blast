package kw.sanbloast.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

import kw.sanbloast.SandBlost;
import kw.sanbloast.constant.ColorUtils;
import kw.sanbloast.constant.SBConstant;

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class SandActor extends Group {
    private int posx;
    private int posy;
    private Image image;
    private String nameP;
    public SandActor(){
        setSize(SBConstant.blockSize,SBConstant.blockSize);
        image = new Image(Asset.getAsset().getTexture("white.png"));
        addActor(image);
        image.setSize(SBConstant.blockSize,SBConstant.blockSize);
    }

    public void getUpPos() {
        posx = (int) getX(Align.center)/ SBConstant.blockSize;
        posy = (int) getY(Align.center)/SBConstant.blockSize;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void updatePosition() {
        updatePosition(false);
    }
    public void updatePosition(boolean isl){
        if (isl) {
            setPosition(posx * SBConstant.blockSize, posy * SBConstant.blockSize, Align.center);
        }else {
            addAction(Actions.moveToAligned(posx*SBConstant.blockSize,posy*SBConstant.blockSize,Align.center,0.1f));
        }
        setNameP("pos"+posx+"-"+posy);
    }

    public void setColorImg(Color random) {
        image.setColor(random);
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }
}
