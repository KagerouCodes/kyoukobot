package me.kagerou.kyoukobot;

import java.util.Random;

import de.btobastian.javacord.entities.message.Message;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
//posts a random picture from the imgur album with emilia, maybe there should be an ImgurCommand super class for this type of commands
public class EMTCommand implements CommandExecutor {
    @Command(aliases = {"k!EMT", "k!Emilia", "k!Emilia-tan"}, description = "Posts a picture of Emilia-tan.")
    public void onCommand(Message message) {
    	message.getReceiver().type();
    	String EMT = KyoukoBot.AllEMTs.get(new Random().nextInt(KyoukoBot.AllEMTs.size()));
    	//message.reply("", new EmbedBuilder().setImage(EMT));
    	KyoukoBot.postFile(message, EMT, "Emilia-tan", "image");
    }
}
