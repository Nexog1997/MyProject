package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.gameLogic.Enemy;
import com.mygdx.game.gameLogic.EnemyBullet;
import com.mygdx.game.gameLogic.Laser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game implements Screen {
    private int score=0;
    BitmapFont bitmapfont = new BitmapFont();
    private String scoreString = "Points: 0" ;
    int randomElement=0;
    ArrayList<Laser> lasers;
    ArrayList<Enemy> enemys;
    ArrayList<EnemyBullet> enemyBullets;
    private Texture background,loseScreen;
    float catX = 512;
    float catY = 20;
    float spawnTime=0;
    float enemyBulletTimer;
    int catHp=8;
    private SpaceInvaders game;
    private TextureAtlas catAtlas, hpAtlas;
    private Animation<TextureRegion> catAnimation;
    private float elapsedTime;
    private Music bulletSound= Gdx.audio.newMusic(Gdx.files.internal("laser.mp3"));
    private HashMap<Laser,Enemy> delete;
    private Rectangle catRect;


    public Game(SpaceInvaders game) {
        Random rand = new Random();
        loseScreen = new Texture("loseScreen.jpg");
        this.game = game;
        lasers = new ArrayList<Laser>();
        enemys = new ArrayList<Enemy>();
        enemyBullets = new ArrayList<EnemyBullet>();
        background = new Texture("space-2.jpg");
        catAtlas = new TextureAtlas("catAtlas.atlas");
        hpAtlas = new TextureAtlas("hpAtlas.atlas");
        catAnimation = new Animation(1f / 5f, catAtlas.getRegions());
        bulletSound.setVolume(0.05f);
        catRect = new Rectangle(catX,Gdx.graphics.getHeight()-catY-catAtlas.findRegion("1").getRegionHeight(),catAtlas.findRegion("1").getRegionWidth(),catAtlas.findRegion("1").getRegionHeight());



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Random rand = new Random();
        enemyBulletTimer+=delta;
        spawnTime+=delta;
        elapsedTime += delta;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        catRect.setPosition(catX,Gdx.graphics.getHeight()-catY-catAtlas.findRegion("1").getRegionHeight());
        // ------------------------------------------

        if(catHp>0) {
            bitmapfont.setColor(Color.GOLD);
            bitmapfont.draw(game.batch,scoreString,900,720);
            movement(delta);
            game.batch.draw(hpAtlas.findRegion(Integer.toString(catHp)), 0, Gdx.graphics.getHeight() - 50);
            //-------------------------------------------
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                lasers.add(new Laser(catX + 10, catY + 70));
                bulletSound.play();

            }
            if (spawnTime > 1) {
                enemys.add(new Enemy(new Random().nextInt(2), new Random().nextInt(668) + 100));
                spawnTime = 0;


            }

            if(enemyBulletTimer>1.5 && enemys.size()>1){

                randomElement = rand.nextInt(enemys.size());
                enemys.get(randomElement);
                enemyBullets.add(new EnemyBullet(enemys.get(randomElement).getterX(),enemys.get(randomElement).getterY()));
                enemyBulletTimer=0;
            }
            ArrayList<EnemyBullet> removeEnemyBullet = new ArrayList<EnemyBullet>();
            ArrayList<Laser> removeLaser = new ArrayList<Laser>();
            ArrayList<Enemy> removeEnemy = new ArrayList<Enemy>();
            for (EnemyBullet enemyBullet : enemyBullets){
                enemyBullet.update(delta);
                enemyBullet.render(game.batch);
                if(enemyBullet.remove){
                   removeEnemyBullet.add(enemyBullet);
                }
                if(enemyBullet.rect.overlaps(catRect)){
                    removeEnemyBullet.add(enemyBullet);
                    catHp--;
                }
            }

            enemyBullets.removeAll(removeEnemyBullet);


            for (Laser laser : lasers) {
                laser.update(delta);
                laser.render(game.batch);
                if (laser.remove)
                    removeLaser.add(laser);
            }
            lasers.removeAll(removeLaser);

            //-----------------------------------------------------
            for (Enemy enemy : enemys) {
                enemy.update(delta);
                enemy.render(game.batch);
                if(enemy.remove){
                    removeEnemy.add(enemy);
                }
            }
            for (Enemy enemy : enemys) {
                for (Laser laser : lasers) {
                    if (enemy.enemyRect.overlaps(laser.rect)) {
                        removeEnemy.add(enemy);
                        removeLaser.add(laser);
                        score++;
                        scoreString = "Points:" + score;

                    }
                }
            }
            for (Enemy enemy : enemys) {

                if (enemy.enemyRect.overlaps(catRect)) {
                    removeEnemy.add(enemy);
                    catHp--;
                }
            }


            //------------------------------------------------------
            lasers.removeAll(removeLaser);
            enemys.removeAll(removeEnemy);
            //------------------------------------------------------



            game.batch.draw(catAnimation.getKeyFrame(elapsedTime, true), catX, catY);
        }
        if(catHp<=0){
            game.batch.draw(loseScreen, 0, -200);
            if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
               game.setScreen(new Game(game));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.N)) {
                Gdx.app.exit();
        }



    }
        game.batch.end();
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
        game.batch.dispose();
        catAtlas.dispose();
        background.dispose();

    }


    private void movement(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.UP)&& catY<Gdx.graphics.getHeight()-catAtlas.findRegion("1").getRegionHeight()  ) {
            catY = catY + delta*250;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&&catY>=0) {
            catY = catY - delta*250;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&catX>0) {
            catX = catX - delta*250;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&catX<Gdx.graphics.getWidth()-catAtlas.findRegion("1").getRegionWidth() ) {
            catX = catX + delta*250;
        }


    }

}
