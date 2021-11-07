package com.maksuu121.onlinegameclient;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OnlineGameClient extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Socket socket;
	Player basePlayer;

	@Override
	public void create () {
		basePlayer = new Player(0, 0, "Maksuu121");
		try {
			socket = new Socket("localhost", 2115);
			System.out.println("Connected");
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			Scanner in = new Scanner(socket.getInputStream());

			out.println(basePlayer.nickname);
			System.out.println("Nickname sent");
			if(in.hasNext()) {
				basePlayer.setId(in.nextInt());
				in.nextLine();
				if(in.nextLine().equals("x y")) {
					out.println(basePlayer.x + " " + basePlayer.y);
					System.out.println("Sent");
				}
			}
		} catch (IOException e) {

		}
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void update() {

	}

	private void disconnect(Socket socket) throws IOException {
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		printWriter.println("DISCONNECT");
		socket.close();
	}
}
