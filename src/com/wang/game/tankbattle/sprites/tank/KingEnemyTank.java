/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.sprites.tank;

/**
 *
 * @author ricolwang
 */
public class KingEnemyTank extends EnemyTank
{

    public KingEnemyTank(String imagename)
    {
        super(imagename);
        
        this.life = 50;
        totalLife = this.life;
    }

}
