package kw.sanbloast.move;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

import kw.sanbloast.group.SandActor;

public class MoveDownAll extends BaseMove{
    public MoveDownAll(Group rootView){
        super(rootView);
    }

    @Override
    public void moveDown(Array<SandActor> sandActors,Runnable nextRunnable) {
        boolean move = false;
        for (SandActor actor : sandActors) {
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
                    if (posx - 1 >= 0 && grid[posy - 1][posx - 1] == 0 && grid[posy][posx - 1] == 0) {
                        if (posy - 2 > 0) {
                            if (grid[posy - 2][posx - 1] == 0) {
                                left = false;
                            } else {
                                left = true;
                            }
                        } else {
                            left = true;
                        }
                    }
                    if (posx + 1 < grid[0].length && grid[posy - 1][posx + 1] == 0 && grid[posy][posx + 1] == 0) {
                        right = true;
                    }

                    if (left) {
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
            downAllBt.addAction(Actions.delay(delayTime,Actions.run(()->{
                Gdx.app.postRunnable(()->{
                    moveDown(sandActors,nextRunnable);
                });
            })));
        }else {
            downAllBt.addAction(Actions.delay(delayTime,Actions.run(()->{
                nextRunnable.run();
            })));
        }
    }


}
