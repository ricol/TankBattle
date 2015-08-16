/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.tank;

import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.common.Common;
import au.com.rmit.tankbattle.other.Wall;
import au.com.rmit.tankbattle.scene.TankBattleScene;
import au.com.rmit.tankbattle.sprites.missile.EnemyMissile;
import au.com.rmit.tankbattle.sprites.missile.Missile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author ricolwang
 */
public class EnemyTank extends Tank implements ActionListener
{

    Timer theTimerAdjust = new Timer(3000, this);
    Timer theTimerForFire = new Timer(2000, this);

    public EnemyTank(String imagename)
    {
        super(imagename);

        this.life = 20;
        theTimerAdjust.start();
        theTimerForFire.start();
        this.theDirection = DIRECTION.BOTTOM;

        this.setCollisionCategory(Common.CATEGORY_ENEMY_TANK);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_MISSILE);
        this.addTargetCollisionCategory(Common.CATEGORY_FRIEND_TANK);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.theTimerAdjust))
        {
            this.changeDirection();
        } else if (e.getSource().equals(this.theTimerForFire))
        {
            if (theRandom.nextBoolean())
                this.fire();
        }
    }

    public void changeDirection()
    {
        int num = theRandom.nextInt() % 4;
        if (num == 0)
            this.movingLeft();
        else if (num == 1)
            this.movingRight();
        else if (num == 2)
            this.movingTop();
        else
            this.movingBottom();
    }

    @Override
    public void onWillDead()
    {
        super.onWillDead(); //To change body of generated methods, choose Tools | Templates.

        ((TankBattleScene) this.theScene).deleteAEnemy(this);
        ((TankBattleScene) this.theScene).killAEnemy(this);
    }

    @Override
    Missile getAMissile()
    {
        return new EnemyMissile(this);
    }

    @Override
    double getMissileVelocity()
    {
        return Common.SPEED_MISSILE_ENEMY;
    }

    @Override
    double getTankSpeed()
    {
        return Common.SPEED_ENEMY_TANK;
    }

    @Override
    public void onCollideWith(Sprite target)
    {
        super.onCollideWith(target); //To change body of generated methods, choose Tools | Templates.

        if (target instanceof Wall)
        {
            this.changeMovingDirection(this.theDirection);
        }
    }
}
