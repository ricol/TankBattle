/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.other;

import au.com.rmit.Game2dEngine.action.ExpandByAction;
import au.com.rmit.Game2dEngine.sprite.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author ricolwang
 */
public class Fire extends Sprite
{

    public Fire()
    {
        super();
        
        this.setWidth(1);
        this.setHeight(1);

        this.bCustomDrawing = true;
        this.bDeadIfNoActions = true;

        ExpandByAction aAction = new ExpandByAction();
        aAction.expandBy(10, 0.05f);
        this.addAction(aAction);
    }

    @Override
    public void onCustomDraw(Graphics2D theGraphics2D)
    {
        super.onCustomDraw(theGraphics2D); //To change body of generated methods, choose Tools | Templates.

        theGraphics2D.setColor(Color.blue);
        theGraphics2D.fillArc(0, 0, (int)getWidth(), (int)getHeight(), 0, 360);
    }
}
