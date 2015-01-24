package com.gmail.dajinchu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;

import java.io.Serializable;

/**
 * Created by Da-Jin on 12/5/2014.
 */
public class Player implements Serializable {
    private final Model model;
    Array<Integer> my_ships = new Array<Integer>();//ships under this Player's control
    int playerNumber;//For identification across devices, each number corresponds to a color
    Texture texture;

    Body dest;

    String TAG = "Player";

    boolean readyToPlay = false;

    Color color;

    private Color[] colormap = new Color[]{new Color(Color.RED), new Color(Color.BLUE)};

    public Player(int playerNumber, Model model){
        dest = model.createCircleBody(model.mapWidth/2, model.mapHeight/2, (float) InGameScreen.DEST_RADIUS, BodyDef.BodyType.StaticBody, true);
        this.model = model;
        this.playerNumber = playerNumber;
        this.color = colormap[playerNumber];
    }

    //TODO maybe you'll need this?
    /*private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        mPaint = new Paint();
        Log.i("Player", "Getting de-serialized!");
    }*/

    public void setDest(int destx, int desty){
        dest.setTransform(destx,desty,dest.getAngle());
        Gdx.app.log("Player", "settingdest "+dest.getPosition().x);

        for(int id : my_ships){
            //System.out.print(id);
            if(model.getShip(id)==null){
                continue;
            }
            model.getShip(id).calcDestWithWander();
        }
    }

}
