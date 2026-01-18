package me.cocos.ignite.template.java;

import me.cocos.ignite.model.ProjectConfig;

public class CoreGameTemplate implements JavaTemplate {

    @Override
    public String generate(ProjectConfig config) {
        return """
               package %s;
               
               import com.badlogic.gdx.Game;
               import com.badlogic.gdx.Gdx;
               import com.badlogic.gdx.graphics.Color;
               import com.badlogic.gdx.graphics.g2d.BitmapFont;
               import com.badlogic.gdx.graphics.g2d.SpriteBatch;
               import com.badlogic.gdx.utils.ScreenUtils;
               
               public class %s extends Game {
                   
                   private SpriteBatch batch;
                   private BitmapFont font;
               
                   @Override
                   public void create() {
                       this.batch = new SpriteBatch();
                       this.font = new BitmapFont();
                       font.setColor(Color.WHITE);
                   }
               
                   @Override
                   public void render() {
                       ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
                       
                       batch.begin();
                       font.draw(batch, "Welcome to LibGDX!", 100, 150);
                       font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 100, 100);
                       batch.end();
                       
                       super.render();
                   }
               
                   @Override
                   public void resize(int width, int height) {
                       super.resize(width, height);
                   }
               
                   @Override
                   public void dispose() {
                       batch.dispose();
                       font.dispose();
                   }
               }
               """.formatted(config.packageName(), config.mainClass());
    }
}