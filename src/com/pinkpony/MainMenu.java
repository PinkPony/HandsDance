package com.pinkpony;

import java.awt.Color;
import java.awt.Graphics;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class MainMenu extends AbstractGameState{

	Graphics gr;
	
	String appId= "596061157140744";
	String appSecret="5489721f19d79061fc9b29f0724f158f";
	String accessToken="CAAIeHU2JKQgBAF2bRCqOZC9200IPUeiBFcM0yCvq4hXepBkvqFoSZCAflJEvud20ubYDhnBh8WyV91A8FwynqT8RZAgVus7n9cLzDi79xZCwhfD4TVgHjcW8deEuBvjvwxrDMV03ZCLHZCAS9XSIzqSvZBgxH8k2ipa1kFquLDXbKG6htj8G97uOlGt2nZBiFxZCgUGxGODOEZAgZDZD";
	String permissions= "email";
	
	public void tick(Graphics g)
	{
		gr = g;
		gr.clearRect(0, 0, HandsDance.WIDTH, HandsDance.HEIGHT);
		gr.setColor(Color.PINK);
		gr.drawString("HandsDance : Pink Pony", 150, 20);
		gr.drawString("HIT ESC TO LEAVE", 10, 20);	
		gr.drawString("IN MENU SCREEN",400,400);
		
		if(HandsDance.mouse[0] != 0)
			play();
		
	}
	
	public void play()
	{
		StateChanger.gameState = GameState.PLAY;
	}
	
	/*
	public void facebookLogin() throws FacebookException
	{
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthPermissions(permissions);
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
		
		ResponseList<Account> accounts = facebook.getAccounts();
		Account yourPageAccount = accounts.get(0);  // if index 0 is your page account.
		String pageAccessToken = yourPageAccount.getAccessToken();
		facebook.postStatusMessage("Hello World from Facebook4J.");
	}
	*/
	
}
