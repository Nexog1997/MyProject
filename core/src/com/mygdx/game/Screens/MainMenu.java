package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpaceInvaders;

public class MainMenu implements Screen {

    Texture mainMenuBackground, exitBtn, startBtn,optionsBtn;
    private SpaceInvaders game;
    private int startButtonY = 600;
    private int optionsButtonY=470;
    private int exitButtonY = 300;

    public MainMenu(SpaceInvaders game) {
        this.game = game;
        mainMenuBackground = new Texture("space-2.jpg");
        startBtn = new Texture("startBtn.png");
        exitBtn = new Texture("exitBtn.png");
        optionsBtn = new Texture("optionsBtn.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(mainMenuBackground, 0, 0);
        game.batch.draw(startBtn, Gdx.graphics.getWidth()/2 - (startBtn.getWidth() / 2), startButtonY);
        game.batch.draw(exitBtn, Gdx.graphics.getWidth()/2 - (exitBtn.getWidth() / 2), exitButtonY);
        game.batch.draw(optionsBtn,Gdx.graphics.getWidth()/2-optionsBtn.getWidth()/2,optionsButtonY);
        game.batch.end();

        if (Gdx.input.getX() < Gdx.graphics.getWidth()/2 + (startBtn.getWidth() / 2) && Gdx.input.getX() > Gdx.graphics.getWidth()/2 - (startBtn.getWidth() / 2) && Gdx.input.getY() < Gdx.graphics.getHeight() - startButtonY && Gdx.input.getY() > Gdx.graphics.getHeight() - startButtonY - startBtn.getHeight()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

                game.setScreen(new Game(game));
            }
        }
        if (Gdx.input.getX() < Gdx.graphics.getWidth()/2 + (optionsBtn.getWidth() / 2) && Gdx.input.getX() > Gdx.graphics.getWidth()/2 - (optionsBtn.getWidth() / 2) && Gdx.input.getY() < Gdx.graphics.getHeight() - optionsButtonY && Gdx.input.getY() > Gdx.graphics.getHeight() - optionsButtonY - optionsBtn.getHeight()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

                game.setScreen(new Options(game));
            }
        }
        if (Gdx.input.getX() < Gdx.graphics.getWidth()/2 + exitBtn.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth()/2 - (exitBtn.getWidth() / 2) && Gdx.input.getY() < Gdx.graphics.getHeight() - exitButtonY && Gdx.input.getY() > Gdx.graphics.getHeight() - exitButtonY - exitBtn.getHeight()) {

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                Gdx.app.exit();
            }
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
        mainMenuBackground.dispose();
        startBtn.dispose();
        exitBtn.dispose();
        optionsBtn.dispose();

    }
}
