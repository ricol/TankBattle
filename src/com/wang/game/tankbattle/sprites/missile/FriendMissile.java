/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.sprites.missile;

import com.wang.Game2dEngine.painter.interfaces.IEngineGraphics;
import com.wang.game.tankbattle.common.Common;
import com.wang.game.tankbattle.sprites.tank.Tank;
import java.awt.Color;

/**
 *
 * @author ricolwang
 */
public class FriendMissile extends Missile
{

    public FriendMissile(Tank theTank)
    {
        super(theTank);

        this.setCollisionCategory(Common.CATEGORY_FRIEND_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_TANK);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_ENEMY_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_ENEMY_TANK);
    }

    @Override
    public void onCustomDraw(IEngineGraphics theEngineGraphics)
    {
        super.onCustomDraw(theEngineGraphics); //To change body of generated methods, choose Tools | Templates.

        theEngineGraphics.setColor(Color.RED);
        theEngineGraphics.fillArc(0, 0, (int) this.getWidth(), (int) this.getHeight(), 0, 360);

    }

    @Override
    protected Color getExplosionColor()
    {
        return Color.red;
    }
}
