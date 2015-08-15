/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.basic;

import au.com.rmit.Game2dEngine.sprite.Sprite;
import au.com.rmit.tankbattle.common.Common;

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
}
