package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ScaledTouchUpButton;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.math.Rect;

public class ButtonReplay extends ScaledTouchUpButton {

    public ButtonReplay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("button_new_game"), actionListener, 0.9f);
        setHeightProportion(0.03f);
        setBottom(-0.1f);
    }

}
