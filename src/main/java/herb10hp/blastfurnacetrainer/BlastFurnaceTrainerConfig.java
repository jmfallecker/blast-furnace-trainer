/*
 * Copyright (c) 2022, UIM Herb10HP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package herb10hp.blastfurnacetrainer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("BlastFurnaceTrainer")
public interface BlastFurnaceTrainerConfig extends Config
{

	@ConfigSection(
			position = 0,
			name = "Object Highlights",
			description = "Enable/disable highlighted objects"
	)
	String objectHightlights = "objectHighlights";

	@ConfigSection(
			position = 1,
			name = "Notification Settings",
			description = "Enable/disable notifications"
	)
	String notifications = "notifications";

	@ConfigItem(
			keyName = "showCrafting",
			name = "Highlight Crafting",
			description = "Configures whether or not to display red/green clickboxes on pipes, drive belt and cogs.",
			section = "objectHighlights",
			position = 2
	)
	default boolean showCrafting()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showStove",
			name = "Highlight Stove",
			description = "Configures whether or not to display red/yellow/green clickboxes on the stove.",
			section = "objectHighlights",
			position = 0
	)
	default boolean showStove()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showPump",
			name = "Highlight Pump",
			description = "Configures whether or not to display green clickboxes on the pump when it is available.",
			section = "objectHighlights",
			position = 1
	)
	default boolean showPump()
	{
		return true;
	}

	@ConfigItem(
			keyName = "notifyEmptyStove",
			name = "Notify on Low Coke",
			description = "Shows a notification when the stove is low on coke.",
			section = "notifications"
	)
	default boolean notifyEmptyStove() { return true; }
}
