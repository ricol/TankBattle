/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.tank;

import au.com.rmit.Game2dEngine.Shape.ESpecialRectangleShape;
import au.com.rmit.Game2dEngine.physics.collision.PhysicsCollisionProcess;
import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.other.Fire;
import au.com.rmit.tankbattle.scene.TankBattleScene;
import au.com.rmit.tankbattle.sprites.basic.MovingObject;
import au.com.rmit.tankbattle.sprites.missile.Missile;
import au.com.rmit.tankbattle.sprites.tank.Tank.DIRECTION;
import java.util.ArrayList;

/**
 *
 * @author ricolwang
 */
public class Tank extends MovingObject
{

    Missile theMissile;
    protected int life = 100;

    public static enum DIRECTION
    {

        LEFT, RIGHT, TOP, BOTTOM
    };

    DIRECTION theDirection = DIRECTION.TOP;

    public Tank(String imagename)
    {
        super(imagename);

        ESpecialRectangleShape aRectangleShape = new ESpecialRectangleShape(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.setTheShape(aRectangleShape);
    }

    @Override
    public void onCollideWith(Sprite target)
    {
        super.onCollideWith(target); //To change body of generated methods, choose Tools | Templates.

        if (target instanceof Missile)
        {
            this.life -= 10;
            if (this.life <= 0)
            {
                this.setShouldDie();
            }
        }
    }

    @Override
    public void didUpdateState()
    {
        super.didUpdateState(); //To change body of generated methods, choose Tools | Templates.

        this.checkWall();
        this.checkTank();
    }

    @Override
    public void onDead()
    {
        this.explode(50);

        super.onDead(); //To change body of generated methods, choose Tools | Templates.
    }

    private void checkWall()
    {
        if (this.theScene == null)
        {
            return;
        }

        boolean bHitWall = false;

        if (PhysicsCollisionProcess.isCollide(this, (((TankBattleScene) this.theScene).theWallTop))
                || PhysicsCollisionProcess.isCollide(this, (((TankBattleScene) this.theScene).theWallBottom))
                || PhysicsCollisionProcess.isCollide(this, (((TankBattleScene) this.theScene).theWallLeft))
                || PhysicsCollisionProcess.isCollide(this, (((TankBattleScene) this.theScene).theWallRight)))
        {
            bHitWall = true;
        }

        if (bHitWall)
        {
            this.restorePosition();
            this.changeMovingDirection(this.theDirection);
        }
    }

    private void checkTank()
    {
        if (this.theScene == null)
        {
            return;
        }

        ArrayList<Sprite> allTanks = this.theScene.getAllSprites();

        for (Sprite aSprite : allTanks)
        {
            if (aSprite instanceof Tank)
            {
                Tank aTank = (Tank) aSprite;

                this.checkTank(aTank);
            }
        }
    }

    private void checkTank(Tank aTank)
    {
        if (aTank.equals(this))
        {
            return;
        }

        if (PhysicsCollisionProcess.isCollide(this, aTank))
        {
            this.restorePosition();
//            this.changeMovingDirection(this.theDirection);
        }
    }

    void adjustPosition()
    {
        double v = this.getVelocityX();
        if (v != 0)
        {
            if (v > 0)
            {
                this.setAngle(Math.PI * 3.0 / 2.0);
            } else
            {
                this.setAngle(Math.PI / 2);
            }
        } else
        {
            v = this.getVelocityY();
            if (v > 0)
            {
                this.setAngle(0);
            } else
            {
                this.setAngle(Math.PI);
            }
        }
    }

    @Override
    public void setVelocityX(double value)
    {
        super.setVelocityX(value); //To change body of generated methods, choose Tools | Templates.
        this.adjustPosition();
    }

    @Override
    public void setVelocityY(double value)
    {
        super.setVelocityY(value); //To change body of generated methods, choose Tools | Templates.
        this.adjustPosition();
    }

    public void movingLeft()
    {
        this.setVelocityX(-this.getTankSpeed());
        this.setVelocityY(0);
        this.theDirection = DIRECTION.LEFT;
    }

    public void movingTop()
    {
        this.setVelocityX(0);
        this.setVelocityY(-this.getTankSpeed());
        this.theDirection = DIRECTION.TOP;
    }

    public void movingBottom()
    {
        this.setVelocityX(0);
        this.setVelocityY(this.getTankSpeed());
        this.theDirection = DIRECTION.BOTTOM;
    }

    public void movingRight()
    {
        this.setVelocityX(this.getTankSpeed());
        this.setVelocityY(0);
        this.theDirection = DIRECTION.RIGHT;
    }

    public void fire()
    {
        if (this.theMissile != null)
        {
            return;
        }

        if (this.theScene == null)
        {
            return;
        }

        if (this.theDirection == DIRECTION.TOP)
        {
            Fire aFire = new Fire();
            aFire.setCentreX(this.getCentreX());
            aFire.setCentreY(this.getY() + aFire.getHeight() / 2);
            this.theScene.addSprite(aFire);

        } else if (this.theDirection == DIRECTION.BOTTOM)
        {
            Fire aFire = new Fire();
            aFire.setCentreX(this.getCentreX());
            aFire.setCentreY(this.getY() + this.getHeight() - aFire.getHeight() / 2);
            this.theScene.addSprite(aFire);
        } else if (this.theDirection == DIRECTION.LEFT)
        {
            Fire aFire = new Fire();
            aFire.setCentreX(this.getX() + aFire.getWidth() / 2);
            aFire.setCentreY(this.getCentreY());
            this.theScene.addSprite(aFire);
        } else if (this.theDirection == DIRECTION.RIGHT)
        {
            Fire aFire = new Fire();
            aFire.setCentreX(this.getX() + this.getWidth() - aFire.getWidth() / 2);
            aFire.setCentreY(this.getCentreY());
            this.theScene.addSprite(aFire);
        }

        Missile aMissile = this.getAMissile();
        this.theScene.addSprite(aMissile);

        if (this.theDirection == DIRECTION.TOP)
        {
            aMissile.setCentreX(this.getCentreX());
            aMissile.setY(this.getY());
            aMissile.setVelocityY(-this.getMissileVelocity());
        } else if (this.theDirection == DIRECTION.BOTTOM)
        {
            aMissile.setCentreX(this.getCentreX());
            aMissile.setY(this.getY() + this.getHeight() - aMissile.getHeight());
            aMissile.setVelocityY(this.getMissileVelocity());
        } else if (this.theDirection == DIRECTION.LEFT)
        {
            aMissile.setX(this.getX());
            aMissile.setCentreY(this.getCentreY());
            aMissile.setVelocityX(-this.getMissileVelocity());
        } else if (this.theDirection == DIRECTION.RIGHT)
        {
            aMissile.setX(this.getX() + this.getWidth() - aMissile.getWidth());
            aMissile.setCentreY(this.getCentreY());
            aMissile.setVelocityX(this.getMissileVelocity());
        }

        this.theMissile = aMissile;
    }

    Missile getAMissile()
    {
        return null;
    }

    double getTankSpeed()
    {
        return 0;
    }

    double getMissileVelocity()
    {
        return 0;
    }

    public void clearMissile()
    {
        this.theMissile = null;
    }

    public void changeMovingDirection(Tank.DIRECTION theOldDirection)
    {
        Tank.DIRECTION newDirection = theOldDirection;
        while (newDirection == theOldDirection)
        {
            int num = theRandom.nextInt() % 4;
            if (num == 0)
            {
                newDirection = DIRECTION.TOP;
            } else if (num == 1)
            {
                newDirection = DIRECTION.BOTTOM;
            } else if (num == 2)
            {
                newDirection = DIRECTION.LEFT;
            } else
            {
                newDirection = DIRECTION.RIGHT;
            }
        }

        if (newDirection == DIRECTION.TOP)
        {
            this.movingTop();
        } else if (newDirection == DIRECTION.BOTTOM)
        {
            this.movingBottom();
        } else if (newDirection == DIRECTION.LEFT)
        {
            this.movingLeft();
        } else
        {
            this.movingRight();
        }
    }
}
