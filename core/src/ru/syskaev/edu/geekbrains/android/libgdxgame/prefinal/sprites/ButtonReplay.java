package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.ScaledTouchUpButton;

public class ButtonReplay extends ScaledTouchUpButton {

    public ButtonReplay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("button_new_game"), actionListener, 0.9f);
        setHeightProportion(0.03f);
        setBottom(-0.1f);
    }

}
