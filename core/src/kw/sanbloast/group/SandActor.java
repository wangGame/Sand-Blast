package kw.sanbloast.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

import kw.sanbloast.SandBlost;

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class SandActor extends Group {
    private int posx;
    private int posy;
    public SandActor(){
        setSize(20,20);
        setDebug(true);
        Image image = new Image(Asset.getAsset().getTexture("white_chess.png"));
        addActor(image);
        image.setSize(20,20);
        image.setColor(Color.BLACK);
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

    public void updatePosition(){
        setPosition(posx * 20,posy*20,Align.center);
    }
}
