package kw.sanbloast;

import com.badlogic.gdx.graphics.Color;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;

import kw.sanbloast.screen.LoadingScreen;


/**
 * 使用的画笔
 */
@GameInfo(width = 720,height = 1280,batch = Constant.COUPOLYGONBATCH)
public class SandBlost extends BaseGame {
    public SandBlost(){
        Constant.viewColor = Color.WHITE;
    }

    @Override
    public void create() {
        super.create();
        setScreen(new LoadingScreen(this));
    }
}
