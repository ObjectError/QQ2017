package com.QQ.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

public class Sund {
   
	public Sund(int cmd) {
		
		try {
			String filename = "sound/Global.wav";
			switch (cmd) {
			case Cmd.CMD_ADDFRI:
				filename="sound/system.wav";
	            
				break;
			case Cmd.CMD_SEND:
				filename = "sound/msg.wav";
				break;
			case Cmd.CMD_FILE:
				filename = "sound/tweet.wav";
				break;
			case Cmd.CMD_SHAKE:
				filename = "sound/shake.wav";
				break;
			}
			File file =new File(filename);
			InputStream is = new FileInputStream(file);
			int size  = is.available();
			byte b[] = new byte [size];
			is.read(b,0,size);
			AudioData aData = new AudioData(b);
			AudioDataStream ads = new AudioDataStream(aData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Sund(Cmd.CMD_ARGEE);
	}
}
