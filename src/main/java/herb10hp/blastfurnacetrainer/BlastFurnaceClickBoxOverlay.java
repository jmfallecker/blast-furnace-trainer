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

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

class BlastFurnaceClickBoxOverlay extends Overlay
{
    private static final int MAX_DISTANCE = 2350;

    private final Client client;
    private final BlastFurnaceTrainerPlugin plugin;
    private final BlastFurnaceTrainerConfig config;

    @Inject
    private BlastFurnaceClickBoxOverlay(Client client, BlastFurnaceTrainerPlugin plugin, BlastFurnaceTrainerConfig config)
    {
        setPosition(OverlayPosition.DYNAMIC);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (config.showCrafting())
        {
            // west pipes
            if (plugin.getWestPipesFixed() != null)
                renderObject(plugin.getWestPipesFixed(), graphics, Color.GREEN);
            if (plugin.getWestPipesBroken() != null)
                renderObject(plugin.getWestPipesBroken(), graphics, Color.RED);

            // east pipes
            if (plugin.getEastPipesFixed() != null)
                renderObject(plugin.getEastPipesFixed(), graphics, Color.GREEN);
            if (plugin.getEastPipesBroken() != null)
                renderObject(plugin.getEastPipesBroken(), graphics, Color.RED);

            // drive belt
            if (plugin.getDriveBeltFixed() != null)
                renderObject(plugin.getDriveBeltFixed(), graphics, Color.GREEN);
            if (plugin.getDriveBeltBroken() != null)
                renderObject(plugin.getDriveBeltBroken(), graphics, Color.RED);

            // cogs
            if (plugin.getCogsFixed() != null)
                renderObject(plugin.getCogsFixed(), graphics, Color.GREEN);
            if (plugin.getCogsBroken() != null)
                renderObject(plugin.getCogsBroken(), graphics, Color.RED);
        }

        if (config.showStove())
        {
            if (plugin.getStoveEmpty() != null)
                renderObject(plugin.getStoveEmpty(), graphics, Color.RED);

            if (plugin.getStovePartial() != null)
                renderObject(plugin.getStovePartial(), graphics, Color.YELLOW);

            if (plugin.getStoveFull() != null)
                renderObject(plugin.getStoveFull(), graphics, Color.GREEN);
        }

        if (config.showPump() && plugin.getPump() != null)
        {
            renderObject(plugin.getPump(), graphics, Color.GREEN);
        }

        return null;
    }

    private void renderObject(GameObject object, Graphics2D graphics, Color color)
    {
        LocalPoint localLocation = client.getLocalPlayer().getLocalLocation();
        Point mousePosition = client.getMouseCanvasPosition();

        LocalPoint location = object.getLocalLocation();

        if (localLocation.distanceTo(location) <= MAX_DISTANCE)
        {
            Shape objectClickbox = object.getClickbox();
            if (objectClickbox != null)
            {
                if (objectClickbox.contains(mousePosition.getX(), mousePosition.getY()))
                {
                    graphics.setColor(color.darker());
                }
                else
                {
                    graphics.setColor(color);
                }
                graphics.draw(objectClickbox);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
                graphics.fill(objectClickbox);
            }
        }
    }
}
