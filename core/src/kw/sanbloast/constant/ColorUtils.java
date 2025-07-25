package kw.sanbloast.constant;

import com.badlogic.gdx.graphics.Color;

/**
 * Author by tony
 * Date on 2025/7/25.
 */
public class ColorUtils {
    public static Color[]  c = new Color[]{
      Color.BLACK,
            Color.BLUE,
            Color.BROWN,
            Color.CYAN,
            Color.GOLD
    };

    public static Color random (){
        int x  = (int) (Math.random() * c.length);
        return c[x];
    }
}
