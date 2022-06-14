package herb10hp.blastfurnacetrainer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BlastFurnaceTrainerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BlastFurnaceTrainerPlugin.class);
		RuneLite.main(args);
	}
}