package kw.sanbloast.screen;

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
import kw.sanbloast.group.SandActor;

/**
 * Author by tony
 * Date on 2025/7/24.
 */
public class LoadingScreen extends BaseScreen {
    private int width = 35;
    private int height = 70;
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
                for (int i = 0; i < 10; i++) {
                    SandActor actor = new SandActor();
                    int v = (int) (Math.random() * 10) - 5;
                    actor.setPosition(x+i*20,y, Align.center);
                    actor.getUpPos();
                    addActor(actor);
                    actors.add(actor);
                    actor.setColorImg(random);
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
        if (move){
            stage.addAction(Actions.delay(0.033f,Actions.run(()->{
                checkMove(actors,runnable);
            })));
        }else {
            runnable.run();
        }
    }

    public void createBoard(){
        grid = new int[height][width];
        addActor(new Table(){{
            for (int[] ints : grid) {
                for (int anInt : ints) {
                    Actor actor = new Actor();
                    actor.setSize(20,20);
                    actor.setDebug(true);
                    add(actor);
                }
                row();
            }
            pack();
        }});
    }
}
