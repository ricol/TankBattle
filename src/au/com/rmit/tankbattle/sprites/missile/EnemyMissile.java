/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.missile;

import au.com.rmit.Game2dEngine.painter.interfaces.IEngineGraphics;
import au.com.rmit.tankbattle.common.Common;
import au.com.rmit.tankbattle.sprites.tank.Tank;
import java.awt.Color;

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
    public void onCustomDraw(IEngineGraphics theEngineGraphics)
    {
        super.onCustomDraw(theEngineGraphics); //To change body of generated methods, choose Tools | Templates.

        theEngineGraphics.setColor(Color.BLUE);
        theEngineGraphics.fillArc(0, 0, (int) this.getWidth(), (int) this.getHeight(), 0, 360);
    }

    @Override
    protected Color getExplosionColor()
    {
        return Color.blue;
    }
}
