/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.tank;

/**
 *
 * @author ricolwang
 */
public class FriendTank extends Tank
{
    public FriendTank()
    {
        super("resource/Friend.png");
    }
    
    @Override
    void adjustPosition()
    {
        double v = this.getVelocityX();
        if (v != 0)
        {
            if (v > 0)
                this.setAngle(Math.PI / 2);
            else if (v < 0)
                this.setAngle(Math.PI * 3.0 / 2.0);
        } else
        {
            v = this.getVelocityY();
            if (v > 0)
                this.setAngle(Math.PI);
            else if (v < 0)
                this.setAngle(0);
        }
    }
}
