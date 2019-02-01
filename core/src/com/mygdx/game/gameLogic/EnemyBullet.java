package com.mygdx.game.gameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyBullet {
    public boolean remove = false;

    private final int speed =200;
    private Texture enemyBulletTexture = new Texture("bulletEnemy.png");
    float x,y;
    public Rectangle rect;



    public EnemyBullet(float x,float y){
        this.x=x;
        this.y=y;
        rect = new Rectangle(x+10, Gdx.graphics.getHeight()-y-enemyBulletTexture.getHeight()+10, enemyBulletTexture.getWidth()-20,enemyBulletTexture.getHeight()-20);
    }

    public void update(float deltaTime){
        y-=speed*deltaTime;
        rect.setPosition(x+10,Gdx.graphics.getHeight()-y-enemyBulletTexture.getHeight()+10);

        if(y<-50){
            remove = true;
        }



    }




    public void render(SpriteBatch batch){
        batch.draw(enemyBulletTexture,x,y);
    }

}
