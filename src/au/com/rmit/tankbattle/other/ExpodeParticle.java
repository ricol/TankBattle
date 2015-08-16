/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.other;

import au.com.rmit.Game2dEngine.sprite.Sprite;
import java.awt.Graphics2D;

/**
 *
 * @author ricolwang
 */
public class ExpodeParticle extends Sprite
{

    public ExpodeParticle(double x, double y, double width, double height, double mass, double velocityX, double velocityY)
    {
        super(x, y, width, height, mass, velocityX, velocityY);

        this.bCustomDrawing = true;
    }

    public ExpodeParticle()
    {
        this(0, 0, 7, 7, 0, 0, 0);
    }

    @Override
    public void onCustomDraw(Graphics2D theGraphics2D)
    {
        theGraphics2D.setColor(this.getColor());
        theGraphics2D.fillOval(0, 0, (int) getWidth() - 1, (int) getHeight() - 1);
    }

}
