/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.other;

import com.wang.Game2dEngine.action.AlphaToAction;
import com.wang.Game2dEngine.sprite.UI.SLabel;
import com.wang.game.tankbattle.common.Common;
import java.awt.Font;

/**
 *
 * @author ricolwang
 */
public class Score extends SLabel
{

    public Score(String text)
    {
        super(0, 0, text, new Font("TimesRoman", Font.PLAIN, 15));

        this.setVelocityY(-50);
        this.setLayer(Common.LAYER_TEXT);
        this.setRed(255);
        this.setGreen(255);
        this.setBlue(0);
        this.bTextFrame = false;

        AlphaToAction aAction = new AlphaToAction(this);
        aAction.alphaTo(0, 1.5f);
        this.addAction(aAction);

        this.bDeadIfNoActions = true;
    }
}
