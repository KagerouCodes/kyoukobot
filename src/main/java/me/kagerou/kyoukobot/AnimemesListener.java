package me.kagerou.kyoukobot;

import java.lang.reflect.Method;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageAttachment;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import de.btobastian.sdcf4j.Command;
import me.kagerou.kyoukobot.MemeBase.DownloadResult;

public class AnimemesListener implements MessageCreateListener {
	String[] uploadPrefixes = {};
	
	AnimemesListener()
	{
		try {
			Method onCommand = null;
			for (Method meth: UploadCommand.class.getMethods())
				if (meth.getName().equals("onCommand"))
					onCommand = meth;
			uploadPrefixes = onCommand.getAnnotation(Command.class).aliases();
		}
		catch (Exception e)
		{
			System.out.println("fial");
		}
	}
	
	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		if (message.isPrivateMessage() || message.getAuthor().isBot() || !message.getChannelReceiver().getName().equals("animemes"))
			return;
		String args[] = message.getContent().split(" |\\r\\n|\\n|\\r");
		if (args.length > 0) 
			for (String prefix: uploadPrefixes)
				if (args[0].equalsIgnoreCase(prefix))
					return;
		int uploaded = 0, dupes = 0;
		for (String arg: args)
		{
			DownloadResult result = KyoukoBot.memeBase.DownloadImage(arg);
			if (result == DownloadResult.DR_OK)
				uploaded++;
			if (result == DownloadResult.DR_DUPE)
				dupes++;
		}
		for (MessageAttachment attachment: message.getAttachments())
		{
			DownloadResult result = KyoukoBot.memeBase.DownloadImage(attachment.getUrl());
			if (result == DownloadResult.DR_OK)
				uploaded++;
			if (result == DownloadResult.DR_DUPE)
				dupes++;
		}
		if (uploaded > 0)
			System.out.println("Uploaded " + uploaded + " image(s)!");
		if (dupes > 0)
			System.out.println("Found " + dupes + " duplicate(s)!");
	}
}
