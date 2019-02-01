package com.mygdx.game.gameLogic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {

    float randPos,x, y;
    private Texture spaceShip = new Texture("spaceShip.png");

    private final int speed = 100;
   public Rectangle enemyRect;
   public boolean remove = false;

    public Enemy(int randPos, int y) {
        if (randPos == 0) {
            x = 0;
            this.y = y;
            this.randPos=randPos;
        } else {
            x = Gdx.graphics.getWidth();
            this.y = y;
            this.randPos=randPos;
        }

        enemyRect = new Rectangle(x,Gdx.graphics.getHeight()-y-spaceShip.getHeight(),spaceShip.getWidth(),spaceShip.getHeight());

    }

    public void update(float deltaTime) {
        if(randPos==0){
            x+=speed*deltaTime;
            enemyRect.setPosition(x,Gdx.graphics.getHeight()-y-enemyRect.getHeight());
            if(x>1024){
                remove = true;
            }
        }
        else{
            x-=speed*deltaTime;
            enemyRect.setPosition(x,Gdx.graphics.getHeight()-y-enemyRect.getHeight());
            if(x<-30){
                remove = true;
            }

        }

    }
    public float getterX(){
        return x;
    }
    public float getterY(){
        return y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(spaceShip, x, y);
    }

}
