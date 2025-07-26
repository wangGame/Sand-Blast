package kw.sanbloast.move;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.Comparator;

import kw.sanbloast.group.Position;
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

    public Array<Array<Position>> check(){
        int v[][] = new int[grid.length][grid[0].length];
        ArrayMap<Integer,Array<Position>> positions = new ArrayMap<>();
        int line = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] != 0) {
                Array<Position> positions1 = new Array<>();
                find(v,0,i,positions1);
                if (positions1.size>0) {
                    positions.put(line++, positions1);
                }
            }
        }

        Array<Array<Position>> array = new Array<>();
        for (int i = 0; i < positions.size; i++) {
            Array<Position> value1 = positions.getValueAt(i);
            System.out.println("-------------");
            value1.sort(new Comparator<Position>() {
                @Override
                public int compare(Position o1, Position o2) {
                    return o1.getPx() - o1.getPx();
                }
            });
            Position position = value1.get(value1.size - 1);
            if (position.getPx() == grid[0].length-1) {
                array.add(value1);
            }
            System.out.println("----------------------------");
        }

        System.out.println("=======================================");
        return array;
    }

    public void find(int[][]v,int startX,int startY,Array<Position> positions){
        if (v[startY][startX] == 1)return;
        if (grid[startY][startX] == 0)return;
        v[startY][startX] = 1;
        positions.add(new Position(startX,startY));
        //上
        if (startY +1<grid.length){
            find(v,startX,startY+1,positions);
        }
        if (startY - 1>=0){
            find(v,startX,startY-1,positions);
        }
        if (startX - 1>=0){
            find(v,startX-1,startY,positions);
        }
        if (startX + 1<grid[0].length){
            find(v,startX+1,startY,positions);
        }
    }
}
