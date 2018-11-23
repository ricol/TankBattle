/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.sprites.basic;

import com.wang.Game2dEngine.action.AlphaToAction;
import com.wang.Game2dEngine.sprite.Sprite;
import com.wang.game.tankbattle.common.Common;
import com.wang.game.tankbattle.other.ExpodeParticle;
import java.awt.Color;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 *
 * @author ricolwang
 */
public class MovingObject extends Sprite
{

    public MovingObject(String image)
    {
        super(image);

        this.init();
    }

    public MovingObject()
    {
        super();

        this.init();
    }

    final void init()
    {
        this.bCollisionDetect = true;
        this.addTargetCollisionCategory(Common.CATEGORY_WALL);
    }

    protected void explode(int total)
    {
        int number = abs(theRandom.nextInt()) % 10 + total;

        for (int i = 0; i < number; i++)
        {
            double tmpX = pow(-1, theRandom.nextInt() % 10) * theRandom.nextFloat() * Common.SPEED_EXPLODE_PARTICLE;
            double tmpY = pow(-1, theRandom.nextInt() % 10) * theRandom.nextFloat() * Common.SPEED_EXPLODE_PARTICLE;

            ExpodeParticle aFire = new ExpodeParticle();
            aFire.setX(this.getCentreX());
            aFire.setY(this.getCentreY());
            aFire.setVelocityX(tmpX);
            aFire.setVelocityY(tmpY);
            Color theColor = this.getExplosionColor();
            aFire.setRed(theColor.getRed());
            aFire.setGreen(theColor.getGreen());
            aFire.setBlue(theColor.getBlue());
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

    protected Color getExplosionColor()
    {
        return Color.white;
    }
}
