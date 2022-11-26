package com.sprite.game;

//%W%	%G%
/*
 This app bounces a blue ball inside a JPanel.  The ball is created and begins
 moving with a mousePressed event.  When the ball hits the edge of
 the JPanel, it bounces off the edge and continues in the opposite
 direction.  
*/
import java.awt.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.login.Credential;

import cst8218.hoan0105.game.*;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SpriteGame {
	private String usr;
	private String hash;
	
    	public SpriteGame(Credential credential){
    		usr = credential.getUsrpwd();
    		StartGame();
    	}
    	
    	public void StartGame() {
    		JFrame frame = new JFrame("Bouncing Sprites");
    		SpriteSessionRemote session = null;
    		System.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
    		System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
    	
    			try {
    				System.out.println("about to try for a session...");
    				session = 
    				(SpriteSessionRemote) new InitialContext().lookup("java:global/SpriteJohn/SpriteJohn-ejb/SpriteSession");
    				//(SpriteSessionRemote) new InitialContext().lookup("java:global/SpriteJohn/SpriteJohn-ejb/SpriteSession");
                System.out.println("I got a session");
                System.out.println("This is the height: " + session.getHeight());
    					} catch (NamingException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (RemoteException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    		if (session != null){
    		   System.out.println("I got game");
    		}else{
    			System.err.println("Could not contact game server");
    			System.exit(1);
    		}
    		try {
    			frame.setSize(session.getHeight()+50, session.getWidth()+5);
    		} catch (RemoteException e) {
    			System.err.println("Could not get one or both of HEIGHT, WIDTH for this game");
    			e.printStackTrace();
    		}
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
    		SpritePanel panel = new SpritePanel(session);
            frame.add(panel);
            frame.validate();
            new Thread(panel).start();
    	}
}

