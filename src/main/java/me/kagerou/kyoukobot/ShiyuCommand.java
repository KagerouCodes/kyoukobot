package me.kagerou.kyoukobot;

import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
//posts le funny Shiyu face
public class ShiyuCommand implements CommandExecutor {
	final static String ShiyuFace = "http://i.imgur.com/qAMpJam.png";
    @Command(aliases = {"k!shiyu"}, description = "Posts a picture of baby Shiyu's face.")
    public void onCommand(Message message) {
    	message.getReceiver().type();
    	KyoukoBot.postFile(message, ShiyuFace, "shiyu"); //TODO add a Shiyu emote to it 
    }
}