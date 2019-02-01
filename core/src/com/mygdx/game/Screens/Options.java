package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.SpaceInvaders;



public class Options implements Screen {

    private SpaceInvaders game;
    private Texture musicButton, mainMenuBackground, backButton, onButton, offButton;
    private Rectangle rect;


    public Options(SpaceInvaders game) {
        this.game = game;


        musicButton = new Texture("musicButton.png");
        mainMenuBackground = new Texture("MainMenu.jpg");
        backButton = new Texture("back.png");
        onButton = new Texture("on.png");
        offButton = new Texture("off.png");
        rect = new Rectangle(Gdx.graphics.getWidth()/2-backButton.getWidth()/2,Gdx.graphics.getHeight()/2,backButton.getWidth(),backButton.getHeight());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.batch.draw(mainMenuBackground, 0, 0);
        game.batch.draw(musicButton, Gdx.graphics.getWidth() / 2 - musicButton.getWidth(), Gdx.graphics.getHeight() / 2);
        if (SpaceInvaders.musicCounter == 1) {
            game.batch.draw(onButton, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        } else game.batch.draw(offButton, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        game.batch.draw(backButton, Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - musicButton.getHeight());

        game.batch.end();
        if (Gdx.input.justTouched()&&Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + offButton.getWidth() && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 && Gdx.input.getY() < Gdx.graphics.getHeight() / 2 && Gdx.input.getY() > Gdx.graphics.getHeight() / 2 - offButton.getHeight()) {

            if(SpaceInvaders.musicCounter==1){
                SpaceInvaders.musicCounter=0;
                SpaceInvaders.music.stop();
            }
            else{
                SpaceInvaders.musicCounter=1;
                SpaceInvaders.music.play();
            }

        }

       if(rect.contains(Gdx.input.getX(),Gdx.input.getY())&&Gdx.input.justTouched()){
            game.setScreen(new MainMenu(game));
           dispose();
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        musicButton.dispose();
        mainMenuBackground.dispose();
        backButton.dispose();
        onButton.dispose();
        offButton.dispose();

    }
}
