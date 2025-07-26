package kw.sanbloast.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;

import kw.sanbloast.constant.ColorUtils;
import kw.sanbloast.constant.SBConstant;
import kw.sanbloast.group.GameView;
import kw.sanbloast.group.SandActor;
import kw.sanbloast.move.BaseMove;
import kw.sanbloast.move.MoveDownAll;

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class LoadingScreen extends BaseScreen {



    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();

        GameView gameView = new GameView();
        addActor(gameView);
        gameView.setPosition(Constant.GAMEWIDTH/2f,Constant.GAMEHIGHT/2f,Align.center);
    }

}
