package game.scenes;

import com.google.gson.Gson;
import game.Utils;
import game.bases.GameObject;
import game.gson.MapJson;
import game.items.*;
import game.player.Player;

/**
 * Created by SNOW on 8/11/2017.
 */
public class Level1Scene extends Scene {

    MapJson readJson = new MapJson();
    @Override
    public void init() {
        addBackGround();
        addPlayer();
        readGson();
        addIteam();
    }

    public void addBackGround(){
        BackGround backGround = new BackGround();
        GameObject.add(backGround);
        Lava lavaGround = new Lava();
        lavaGround.renderer = null;
        lavaGround.getBoxCollier().width = 20000;
        lavaGround.setPosition(0,718);
        GameObject.add(lavaGround);
    }

    private void addPlayer() {
        Player malePlayer;
        Player femalePlayer;
        malePlayer = new Player().createMalePlayer();
        femalePlayer = Player.createFemalePlayer();
        GameObject.add(malePlayer.setPosition(20, 670));
        GameObject.add(femalePlayer.setPosition(100, 670));
    }

    public void readGson(){

        Gson gson = new Gson();
        readJson = gson.fromJson(Utils.loadFileContent("map.json"),MapJson.class);
    }

    public void addIteam(){
        int[][] item = Utils.convert_1D_To_2D(readJson.layers.data,25,200);
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 200; j++){
                int vt = item[i][j];
                switch (vt){
                    case 1:
                        GameObject.add(new Lava().setPosition(32*j,32*i-15));
                        break;
                    case 3:
                        GameObject.add(new Banana().setPosition(32*j,32*i-15));
                        break;
                    case 6:
                        GameObject.add(new Poop().setPosition(32*j,32*i-15));
                        break;
                    case 4:
                        GameObject.add(new Condom().setPosition(32*j,32*i-15));
                        break;
                    case 2:
                        GameObject.add(new Heart().setPosition(32*j,32*i-15));
                        break;
                    case 5:
                        GameObject.add(new Drug().setPosition(32*j,32*i-15));
                        break;
                    default: break;
                }
            }
        }
    }
}
