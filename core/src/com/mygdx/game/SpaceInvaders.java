package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenu;


public class SpaceInvaders extends com.badlogic.gdx.Game {
    public static int screenState = 0;
    public SpriteBatch batch;
    public  static int musicCounter;

    public static Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();

        setScreen(new MainMenu(this));
        music = Gdx.audio.newMusic(Gdx.files.internal("nyan.mp3"));
        music.setLooping(true);
        music.setVolume(0.01f);
        music.play();
        musicCounter=1;

    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
