/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.missile;

import au.com.rmit.tankbattle.common.Common;
import au.com.rmit.tankbattle.sprites.tank.Tank;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author ricolwang
 */
public class EnemyMissile extends Missile
{

    public EnemyMissile(Tank theTank)
    {
        super(theTank);

        this.setCollisionCategory(Common.CATEGORY_ENEMY_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_TANK);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_MISSILE);
    }

    @Override
    public void onCustomDraw(Graphics2D theGraphics2D)
    {
        super.onCustomDraw(theGraphics2D); //To change body of generated methods, choose Tools | Templates.

        theGraphics2D.setColor(Color.BLUE);
        theGraphics2D.fillArc(0, 0, (int) this.getWidth(), (int) this.getHeight(), 0, 360);
    }
}
