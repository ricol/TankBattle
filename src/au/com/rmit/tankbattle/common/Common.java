/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.common;

/**
 *
 * @author ricolwang
 */
public class Common
{

    public static final float SPEED_EXPLODE_PARTICLE = 300;

    public static final float SPEED_MISSILE_FRIEND = 600;
    public static final float SPEED_MISSILE_ENEMY = 400;

    public static final float SPEED_ENEMY_TANK = 150;
    public static final float SPEED_FRIEND_TANK = 200;

    public static final int SCORE_ENEMY = 5;

    public static final int LAYER_FRIEND_SHIP = 1;
    public static final int LAYER_ENEMY_SHIP = 0;
    public static final int LAYER_TEXT = 2;

    public static final int MAX_ENEMY = 10;

    public static final int CATEGORY_WALL = 1;
    public static final int CATEGORY_FRIEND_TANK = 1 << 1;
    public static final int CATEGORY_ENEMY_TANK = 1 << 2;
    public static final int CATEGORY_FRIEND_MISSILE = 1 << 3;
    public static final int CATEGORY_ENEMY_MISSILE = 1 << 4;
}
