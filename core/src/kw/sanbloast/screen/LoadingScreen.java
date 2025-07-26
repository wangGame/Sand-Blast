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
import kw.sanbloast.group.SandActor;
import kw.sanbloast.move.BaseMove;
import kw.sanbloast.move.MoveDownAll;

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class LoadingScreen extends BaseScreen {
    private int width = 100;
    private int height = 900;
    private int[][] grid;
    private BaseMove baseMove;

    public LoadingScreen(BaseGame game) {
        super(game);
        createBoard();
        baseMove = new MoveDownAll(stage.getRoot());
        baseMove.setGrid(grid);
        stage.addListener(new OrdinaryButtonListener(1){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Array<SandActor> actors = new Array<>();
                Color random = ColorUtils.random();
                for (int j = 0; j < 10; j++) {
                    for (int i = 0; i < 10; i++) {
                        SandActor actor = new SandActor();
                        actor.setPosition(x + i * SBConstant.blockSize+0*SBConstant.blockSize * 2, y+j*SBConstant.blockSize, Align.center);
                        actor.getUpPos();
                        addActor(actor);
                        actors.add(actor);
                        actor.setColorImg(random);
                    }
                }
                baseMove.moveDown(actors, new Runnable() {
                    @Override
                    public void run() {

                    }
                });


            }
        });
    }


    public void createBoard(){
        grid = new int[height][width];
        addActor(new Table(){{
            for (int[] ints : grid) {
                for (int anInt : ints) {
                    Actor actor = new Actor();
                    actor.setSize(SBConstant.blockSize,SBConstant.blockSize);
                    add(actor);
                }
                row();
            }
            pack();
            setPosition(Constant.GAMEWIDTH/2f,Constant.GAMEHIGHT/2,Align.center);
        }});
    }
}
