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
import com.kw.gdx.constant.Configuration;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;

import kw.sanbloast.constant.ColorUtils;
import kw.sanbloast.constant.SBConstant;
import kw.sanbloast.group.SandActor;

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class LoadingScreen extends BaseScreen {
    private int width = 350;
    private int height = 700;
    private int[][] grid;
    public LoadingScreen(BaseGame game) {
        super(game);
        createBoard();
        down();

        stage.addListener(new OrdinaryButtonListener(1){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Array<SandActor> actors = new Array<>();
                Color random = ColorUtils.random();
                for (int j = 0; j < 15; j++) {
                    for (int i = 0; i < 10; i++) {
                        SandActor actor = new SandActor();
                        actor.setPosition(x + i * SBConstant.blockSize+0*SBConstant.blockSize * 2, y+j*SBConstant.blockSize, Align.center);
                        actor.getUpPos();
                        addActor(actor);
                        actors.add(actor);
                        actor.setColorImg(random);
                    }
                }

                checkMove(actors, new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    private int times;
    private void down() {




    }

    public void checkMove(Array<SandActor> actors, Runnable runnable){
        boolean move = false;
        for (int i = 0; i < 1; i++) {

            for (SandActor actor : actors) {
                int posx = actor.getPosx();
                int posy = actor.getPosy();
                if (posy - 1 > 0) {
                    if (grid[posy - 1][posx] == 0) {
                        grid[posy][posx] = 0;
                        grid[posy - 1][posx] = 1;
                        actor.setPosy(posy - 1);
                        actor.updatePosition();
                        move = true;
                    } else {

//
//                        // 情况2：尝试向下推顶
//                        SandActor belowActor = findActorAt( actors,posx, posy - 1);
//                        if (belowActor != null && posy + 1 < grid.length && grid[posy + 1][posx] == 0) {
//                            // 向上顶起下面的沙子
//                            grid[posy - 1][posx] = 0;
//                            grid[posy][posx] = 1;
//                            belowActor.setPosy(posy);
//                            belowActor.updatePosition();
//                            move = true;
//                            continue; // 本轮 actor 不动，下轮再动
//                        }


                        boolean left = false;
                        boolean right = false;
                        if (posx - 1 >= 0 && grid[posy - 1][posx - 1] == 0) {
                            left = true;
                        }
                        if (posx + 1 < grid[0].length && grid[posy - 1][posx + 1] == 0) {
                            right = true;
                        }

                        if (left && right) {
                            if (Math.random() >= 0.5) {
                                grid[posy][posx] = 0;
                                grid[posy - 1][posx - 1] = 1;
                                actor.setPosy(posy - 1);
                                actor.setPosx(posx - 1);
                                actor.updatePosition();
                                move = true;
                            } else {
                                grid[posy][posx] = 0;
                                grid[posy - 1][posx + 1] = 1;
                                actor.setPosy(posy - 1);
                                actor.setPosx(posx + 1);
                                actor.updatePosition();
                                move = true;
                            }
                        } else if (left) {
                            grid[posy][posx] = 0;
                            grid[posy - 1][posx - 1] = 1;
                            actor.setPosy(posy - 1);
                            actor.setPosx(posx - 1);
                            actor.updatePosition();
                            move = true;
                        } else if (right) {
                            grid[posy][posx] = 0;
                            grid[posy - 1][posx + 1] = 1;
                            actor.setPosy(posy - 1);
                            actor.setPosx(posx + 1);
                            actor.updatePosition();
                            move = true;
                        }
                    }
                }
            }
        }
        if (move){
            stage.addAction(Actions.delay(0.05f,Actions.run(()->{
                checkMove(actors,runnable);

            })));
        }else {
            runnable.run();
        }
    }

    public SandActor findActorAt(Array<SandActor> actors,int pox,int poy){
        Array<SandActor> array = new Array<>(actors);
        for (SandActor actor : array) {
//            setName("pos"+posx+"-"+posy);
            if (actor.getNameP().equals("pos"+pox+"-"+poy)) {
                return actor;
            }
        }
        return null;
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
        }});
    }
}
