package kw.sanbloast.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;

import kw.sanbloast.constant.ColorUtils;
import kw.sanbloast.constant.SBConstant;
import kw.sanbloast.move.BaseMove;
import kw.sanbloast.move.MoveDownAll;

public class GameView extends Group {
    private BaseMove baseMove;
    private int gridW;
    private int gridH;
    private int[][] grid;
    public GameView(){
        setSize(500,500);
        setDebug(true);
        this.gridW = (int) (getWidth()/SBConstant.blockSize);
        this.gridH = (int) (getHeight()/SBConstant.blockSize);
        createBoard();
        viewAddListener();
    }

    public void viewAddListener(){
        addListener(new OrdinaryButtonListener(1){
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
                        Array<Array<Position>> check = baseMove.check();
                        for (Array<Position> positions : check) {
                            for (Position position : positions) {
                                Actor actor = findActor("pos" + position.getPx() + "-" + position.getPy());
                                grid[position.getPy()][position.getPx()] = 0;
                                if (actor!=null) {

                                    actor.addAction(Actions.sequence(
                                            Actions.fadeOut(0.2f),Actions.removeActor()));
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    public void createBoard(){
        baseMove = new MoveDownAll(this);
        grid = new int[gridH][gridW];
        baseMove.setGrid(grid);
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
            setDebug(true);
            setPosition(Constant.GAMEWIDTH/2f,Constant.GAMEHIGHT/2, Align.center);
        }});
    }
}
