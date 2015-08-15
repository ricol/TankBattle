/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.sprites.tank;

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

    public EnemyTank(String imagename)
    {
        super(imagename);
        theTimerAdjust.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.theTimerAdjust))
        {
            this.changeDirection();
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
}
