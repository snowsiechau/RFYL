package game;


import com.google.gson.Gson;
import game.bases.Contraints;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bird.BirdSpawner;
import game.gson.MapJson;
import game.items.*;
import game.scenes.BackGround;
import game.scenes.MenuScene;
import game.scenes.Scene;
import game.scenes.SceneManager;
import game.viewports.ViewPort;
import inputs.InputManager;
import game.player.FemalePlayer;
import game.player.MalePlayer;
import game.player.Player;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


/**
 * Created by Nttung PC on 8/1/2017.
 */
public class GameWindow extends JFrame {
    JFXPanel fxPanel = new JFXPanel();

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphic2D;

    InputManager inputManager = InputManager.instance;
    float distance;

    ViewPort maleViewPort;
    ViewPort femaleViewPort;
    ViewPort mainViewPort;

    Scene startScene;



    private BufferedImage leftBufferImage;
    private BufferedImage rightBufferImage;
    private Graphics2D leftG2d;
    private Graphics2D rightG2d;

    public GameWindow() {
        setupWindow();
        setupInput();
        addBird();
        setupStartScene();
        addViewPorts();
        setupBackBuffer();
        this.setVisible(true);
    }

    private void setupStartScene() {
        startScene = new MenuScene();
        startScene.init();
    }

    private void addBird() {
        GameObject.add(new BirdSpawner());
    }



    private void setupWindow(){
        this.setSize(1600,800);
        this.setResizable(false);
        this.setTitle("Run for your life");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                super.windowClosing(e);
            }
        });
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    private void setupBackBuffer() {
        leftBufferImage = new BufferedImage(this.getWidth() / 2, this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        rightBufferImage = new BufferedImage(this.getWidth() / 2, this.getHeight(), BufferedImage.TYPE_INT_ARGB);

        leftG2d = (Graphics2D) leftBufferImage.getGraphics();
        rightG2d = (Graphics2D) rightBufferImage.getGraphics();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();
    }

    private void addViewPorts() {
            maleViewPort = new ViewPort();
            maleViewPort.getCamera().follow(MalePlayer.instanceMale);
            maleViewPort.getCamera().getOffset().set(getWidth() / 4, 0);

            femaleViewPort = new ViewPort();
            femaleViewPort.getCamera().follow(FemalePlayer.instanceFemale);
            femaleViewPort.getCamera().getOffset().set(getWidth() / 4, 0);

            mainViewPort = new ViewPort();
            mainViewPort.getCamera().followedObject = new GameObject();
            mainViewPort.getCamera().getOffset().set(getWidth() / 4, 0);


            GameObject.add(maleViewPort.getCamera());
            GameObject.add(femaleViewPort.getCamera());
            GameObject.add(mainViewPort.getCamera());
    }

    long lastUpdateTime;
    public void loop(){
        while(true){
            long curentTime = System.currentTimeMillis();
            if (curentTime - lastUpdateTime > 17){
                lastUpdateTime = curentTime;
                run();
                render();
            }
        }
    }

    private void run() {
        GameObject.runAll();
        SceneManager.instance.changeSceneIfNeeded();
    }

    private void render() {
        backBufferGraphic2D.setColor(Color.BLACK);
        backBufferGraphic2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        distance = Math.abs(maleViewPort.getCamera().screenPosition.x - femaleViewPort.getCamera().screenPosition.x);
        if (distance > 750){
            maleViewPort.render(leftG2d, GameObject.getGameObjects());
            femaleViewPort.render(rightG2d, GameObject.getGameObjects());
            backBufferGraphic2D.drawImage(leftBufferImage, 0, 0, null);
            backBufferGraphic2D.drawImage(rightBufferImage, getWidth() / 2, 0, null);
            backBufferGraphic2D.drawLine(getWidth()/2,0,getWidth()/2,800);
        }else{
            mainViewPort.render(backBufferGraphic2D,GameObject.getGameObjects());
            mainViewPort.getCamera().followedObject.position.x = (MalePlayer.instanceMale.position.x + FemalePlayer.instanceFemale.position.x)/2;
            backBufferGraphic2D.drawImage(backBufferImage,0,0,null);
        }
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.drawImage(backBufferImage,0,0,null);
    }

}
