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

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class SandActor extends Group {
    private int posx;
    private int posy;
    private Image image;
    public SandActor(){
        setSize(20,20);
        setDebug(true);
        image = new Image(Asset.getAsset().getTexture("white_chess.png"));
        addActor(image);
        image.setSize(20,20);
    }

    public void getUpPos() {
        posx = (int) getX(Align.center)/20;
        posy = (int) getY(Align.center)/20;
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
            setPosition(posx * 20, posy * 20, Align.center);
        }else {
            addAction(Actions.moveToAligned(posx*20,posy*20,Align.center,0.1f));
        }
    }

    public void setColorImg(Color random) {
        image.setColor(random);
    }
}
