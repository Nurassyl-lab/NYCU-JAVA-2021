//package com.adventure.game.states;

//import com.adventure.game.util.KeyHandler;
//import com.adventure.game.util.MouseHandler;
/*
import KeyHandler;
import MouseHandler;*/

import java.awt.*;

public abstract class GameState {
    private GameStateManager gsm;

    public GameState(GameStateManager gsm)
    {
        this.gsm = gsm;
    }
    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
