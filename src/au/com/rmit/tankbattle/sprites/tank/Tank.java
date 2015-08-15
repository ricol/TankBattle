/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.tank;

import au.com.rmit.Game2dEngine.geometry.shape.RectangleShape;
import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.common.Common;
import au.com.rmit.tankbattle.other.Fire;
import au.com.rmit.tankbattle.other.Wall;
import au.com.rmit.tankbattle.scene.TankBattleScene;
import au.com.rmit.tankbattle.sprites.missile.Missile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author ricolwang
 */
public class Tank extends Sprite
{

    public static enum DIRECTION
    {

        LEFT, RIGHT, TOP, BOTTOM
    };

    DIRECTION theDirection = DIRECTION.TOP;

    public Tank(String imagename)
    {
        super(0, 0, 0, 0, 0, 0, 0);

        BufferedImage aImage;

        try
        {
            aImage = ImageIO.read(new File(imagename));
            this.setWidth(aImage.getWidth());
            this.setHeight(aImage.getHeight());

            this.setImage(imagename);

            RectangleShape aRectangleShape = new RectangleShape(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.setTheShape(aRectangleShape);

        } catch (IOException e)
        {

        }

//        this.bDrawShape = true;
//        this.theColorOfTheShape = Color.red;
//        this.bDrawFrame = true;
        this.bCollisionArbitrary = true;
    }

    @Override
    public void onCollideWith(Sprite target)
    {
        super.onCollideWith(target); //To change body of generated methods, choose Tools | Templates.

        System.out.println("Tank.onCollideWith: " + target);
    }

    @Override
    public void afterCollisionProcess(double currentTime)
    {
        super.afterCollisionProcess(currentTime); //To change body of generated methods, choose Tools | Templates.

        this.checkWall();
        this.checkTank();
    }

    void checkWall()
    {
        if (this.theScene == null)
            return;

        Wall theWall = ((TankBattleScene) this.theScene).theWallTop;
        while (this.getY() < theWall.getY() + theWall.getHeight())
            this.setY(this.getY() + 1);

        theWall = ((TankBattleScene) this.theScene).theWallBottom;
        while (this.getY() + this.getHeight() > theWall.getY())
            this.setY(this.getY() - 1);

        theWall = ((TankBattleScene) this.theScene).theWallLeft;
        while (this.getX() < theWall.getX() + theWall.getWidth())
            this.setX(this.getX() + 1);

        theWall = ((TankBattleScene) this.theScene).theWallRight;
        while (this.getX() + this.getWidth() > theWall.getX())
            this.setX(this.getX() - 1);
    }

    void checkTank()
    {
        if (this.theScene == null)
            return;

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

    void checkTank(Tank aTank)
    {
        if (aTank.equals(this))
            return;

        RectangleShape theShape = (RectangleShape) this.getTheShape();
        RectangleShape theTarget = (RectangleShape) aTank.getTheShape();

        if (theShape.collideWith(theTarget))
        {
            this.restoreX();
            this.restoreY();

            aTank.restoreX();
            aTank.restoreY();
        }
    }

    void adjustPosition()
    {
        double v = this.getVelocityX();
        if (v != 0)
        {
            if (v > 0)
                this.setAngle(Math.PI * 3.0 / 2.0);
            else
                this.setAngle(Math.PI / 2);
        } else
        {
            v = this.getVelocityY();
            if (v > 0)
                this.setAngle(0);
            else
                this.setAngle(Math.PI);
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
        this.setVelocityX(-Common.SPEED_FRIEND_TANK);
        this.setVelocityY(0);
        this.theDirection = DIRECTION.LEFT;
    }

    public void movingTop()
    {
        this.setVelocityX(0);
        this.setVelocityY(-Common.SPEED_FRIEND_TANK);
        this.theDirection = DIRECTION.TOP;
    }

    public void movingBottom()
    {
        this.setVelocityX(0);
        this.setVelocityY(Common.SPEED_FRIEND_TANK);
        this.theDirection = DIRECTION.BOTTOM;
    }

    public void movingRight()
    {
        this.setVelocityX(Common.SPEED_FRIEND_TANK);
        this.setVelocityY(0);
        this.theDirection = DIRECTION.RIGHT;
    }

    public void fire()
    {
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
    }
    
    Missile getAMissile()
    {
        return null;
    }
    
    double getMissileVelocity()
    {
        return 0;
    }
}
