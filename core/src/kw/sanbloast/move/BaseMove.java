package kw.sanbloast.move;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import kw.sanbloast.group.SandActor;

public abstract class BaseMove {
    protected int [][] grid;
    protected Actor downAllBt;
    protected float delayTime = 0.016667f;

    public BaseMove(Group rootView){
        downAllBt = new Actor();
        rootView.addActor(downAllBt);
    }

    public abstract void moveDown(Array<SandActor> sandActors,Runnable runnable);

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void check(){

    }
}
