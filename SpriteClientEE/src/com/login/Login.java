package com.login;

import javax.swing.JFrame;

import com.sprite.game.SpriteGame;

public class Login {
	
	public Login(Credential cred,SpriteGame s) {
		//Creating object of LoginFrame class and setting some of its properties
		LoginFrame frame=new LoginFrame(cred,s);
		frame.setTitle("Login Form");
		frame.setVisible(true);
		frame.setBounds(10,10,370,340);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}
