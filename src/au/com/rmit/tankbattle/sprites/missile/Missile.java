/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.missile;

import au.com.rmit.Game2dEngine.painter.interfaces.IEngineGraphics;
import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.sprites.basic.MovingObject;
import au.com.rmit.tankbattle.sprites.tank.Tank;
import java.awt.Color;

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
    
    public Tank getTank()
    {
        return theTank;
    }

    @Override
    public void onCustomDraw(IEngineGraphics theEngineGraphics)
    {
        super.onCustomDraw(theEngineGraphics); //To change body of generated methods, choose Tools | Templates.

        theEngineGraphics.setColor(Color.RED);
        theEngineGraphics.fillArc(0, 0, (int) this.getWidth(), (int) this.getHeight(), 0, 360);
    }

    @Override
    public void onCollideWith(Sprite target)
    {
        super.onCollideWith(target); //To change body of generated methods, choose Tools | Templates.

        if (this.theTank != target)
        {
            this.explode(20);
            this.setShouldDie();
        }
    }

    @Override
    public void onDead()
    {
        this.theTank.clearMissile();
        super.onDead(); //To change body of generated methods, choose Tools | Templates.
    }
}
