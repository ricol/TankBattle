/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.other;

import au.com.rmit.Game2dEngine.physics.sprites.WallSprite;
import au.com.rmit.tankbattle.common.Common;
import java.awt.Color;
import java.awt.Graphics2D;

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
    public void onCustomDraw(Graphics2D theGraphics2D)
    {
        super.onCustomDraw(theGraphics2D); //To change body of generated methods, choose Tools | Templates.

        theGraphics2D.setColor(Color.green);
        theGraphics2D.fillRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
    }
}
