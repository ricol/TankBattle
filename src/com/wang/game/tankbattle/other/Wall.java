/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.other;

import com.wang.Game2dEngine.painter.interfaces.IEngineGraphics;
import com.wang.Game2dEngine.physics.sprites.WallSprite;
import com.wang.game.tankbattle.common.Common;
import java.awt.Color;

/**
 *
 * @author ricolwang
 */
public class Wall extends WallSprite
{

    public Wall()
    {
        super();
        this.bCustomDrawing = true;

        this.bCollisionDetect = true;
        this.setCollisionCategory(Common.CATEGORY_WALL);
    }

    @Override
    public void onCustomDraw(IEngineGraphics theGraphics2D)
    {
        super.onCustomDraw(theGraphics2D); //To change body of generated methods, choose Tools | Templates.

        theGraphics2D.setColor(Color.green);
        theGraphics2D.fillRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
    }
}
