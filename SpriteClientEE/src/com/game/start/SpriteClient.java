package com.game.start;

import com.login.*;
import com.sprite.game.SpriteGame;


public class SpriteClient {
	
	public static void main(String[] args) {
		SpriteGame spriteStart=null;
		Credential  cred = new Credential();
		Login log = new Login(cred,spriteStart);
	}

}