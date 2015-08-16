/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.missile;

import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.sprites.basic.MovingObject;
import au.com.rmit.tankbattle.sprites.tank.Tank;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author ricolwang
 */
public class Missile extends MovingObject
{

    Tank theTank;

    public Missile(Tank theTank)
    {
        super();

        this.theTank = theTank;

        this.setWidth(10);
        this.setHeight(10);
        this.bCustomDrawing = true;
    }

    @Override
    public void onCustomDraw(Graphics2D theGraphics2D)
    {
        super.onCustomDraw(theGraphics2D); //To change body of generated methods, choose Tools | Templates.

        theGraphics2D.setColor(Color.RED);
        theGraphics2D.fillArc(0, 0, (int) this.getWidth(), (int) this.getHeight(), 0, 360);
    }

    @Override
    public void onCollideWith(Sprite target)
    {
        super.onCollideWith(target); //To change body of generated methods, choose Tools | Templates.

        this.explode(20);
        this.setShouldDie();
    }

    @Override
    public void onDead()
    {
        this.theTank.clearMissile();
        super.onDead(); //To change body of generated methods, choose Tools | Templates.
    }
}
