package com.mygdx.game.gameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Laser {

    private final int speed = 200;
    public boolean remove = false;
    private Texture bulletTexture = new Texture("bullet.png");
    float x, y;
    public Rectangle rect;



    public Laser(float x, float y) {
       this.x = x;
        this.y = y;
        rect= new Rectangle(x,Gdx.graphics.getHeight()-y-bulletTexture.getHeight(),bulletTexture.getWidth(),bulletTexture.getHeight());

    }

    public void update(float deltaTime) {
        y += speed*deltaTime;
        rect.setPosition(x,Gdx.graphics.getHeight()-y-bulletTexture.getHeight());
        if (y >Gdx.graphics.getHeight())
            remove = true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(bulletTexture, x, y);

    }
}
