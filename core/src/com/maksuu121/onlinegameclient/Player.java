package com.maksuu121.onlinegameclient;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    int x;
    int y;
    int id;
    String nickname;

    Player(int x, int y, String nickname) {
        this.x = x;
        this.y = y;
        this.nickname = nickname;
    }

    void setId(int id) {
        this.id = id;
    }

    void drawPlayer(SpriteBatch batch, Texture texture) {
        batch.draw(texture, x, y);
    }

    void movePlayer(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
