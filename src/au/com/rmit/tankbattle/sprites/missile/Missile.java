/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.missile;

import au.com.rmit.Game2dEngine.action.AlphaToAction;
import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.common.Common;
import au.com.rmit.tankbattle.other.ExpodeParticle;
import au.com.rmit.tankbattle.sprites.basic.MovingObject;
import au.com.rmit.tankbattle.sprites.tank.Tank;
import java.awt.Color;
import java.awt.Graphics2D;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

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

        this.explode();
        this.setShouldDie();
    }

    @Override
    public void onDead()
    {
        this.theTank.clearMissile();
        super.onDead(); //To change body of generated methods, choose Tools | Templates.
    }

    protected void explode()
    {
        int number = abs(theRandom.nextInt()) % 10 + 10;

        for (int i = 0; i < number; i++)
        {
            double tmpX = pow(-1, theRandom.nextInt() % 10) * theRandom.nextFloat() * Common.SPEED_EXPLODE_PARTICLE;
            double tmpY = pow(-1, theRandom.nextInt() % 10) * theRandom.nextFloat() * Common.SPEED_EXPLODE_PARTICLE;

            ExpodeParticle aFire = new ExpodeParticle();
            aFire.setX(this.getCentreX());
            aFire.setY(this.getCentreY());
            aFire.setVelocityX(tmpX);
            aFire.setVelocityY(tmpY);
            aFire.setRed(255);
            aFire.setGreen(255);
            aFire.setBlue(255);
            aFire.bDeadIfNoActions = true;

            AlphaToAction aAction = new AlphaToAction(aFire);
            aAction.alphaTo(0, 0.1f);
            aFire.addAction(aAction);

            if (this.theScene == null)
            {
                break;
            }
            this.theScene.addSprite(aFire);
        }
    }
}
