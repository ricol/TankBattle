/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.sprites.tank;

import com.wang.game.tankbattle.common.Common;
import com.wang.game.tankbattle.sprites.missile.FriendMissile;
import com.wang.game.tankbattle.sprites.missile.Missile;

/**
 *
 * @author ricolwang
 */
public class FriendTank extends Tank
{

    public FriendTank()
    {
        super("resource/Friend.png");

        this.life = 10000;
        totalLife = this.life;

        this.setCollisionCategory(Common.CATEGORY_FRIEND_TANK);
        this.addTargetCollisionCategory(Common.CATEGORY_ENEMY_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_ENEMY_TANK);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_TANK);
    }

    @Override
    void adjustPosition()
    {
        double v = this.getVelocityX();
        if (v != 0)
        {
            if (v > 0)
            {
                this.setAngle(Math.PI / 2);
            } else if (v < 0)
            {
                this.setAngle(Math.PI * 3.0 / 2.0);
            }
        } else
        {
            v = this.getVelocityY();
            if (v > 0)
            {
                this.setAngle(Math.PI);
            } else if (v < 0)
            {
                this.setAngle(0);
            }
        }
    }

    @Override
    Missile getAMissile()
    {
        return new FriendMissile(this);
    }

    @Override
    double getMissileVelocity()
    {
        return Common.SPEED_MISSILE_FRIEND;
    }

    @Override
    double getTankSpeed()
    {
        return Common.SPEED_FRIEND_TANK;
    }

    @Override
    public void changeMovingDirection(DIRECTION theOldDirection)
    {

    }
}
