/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.tankbattle.scene;

import au.com.rmit.Game2dEngine.action.AlphaByAction;
import au.com.rmit.Game2dEngine.action.AlphaToAction;
import au.com.rmit.Game2dEngine.sprite.LabelSprite;
import au.com.rmit.tankbattle.common.Common;
import au.com.rmit.tankbattle.other.Score;
import au.com.rmit.tankbattle.sprites.tank.EnemyTank;
import au.com.rmit.tankbattle.sprites.tank.FriendTank;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author ricolwang
 */
public class TankBattleScene extends WallScene
    implements ActionListener
{

    public boolean bGameRunning;
    public LabelSprite lblEnemyKilled;
    public LabelSprite lblMyLife;
    public LabelSprite lblScore;

    int enemyKilled = 0;
    int mylife = 3;
    int score = 0;

    FriendTank theFriendTank;

    Timer timerForEnemy = new Timer(2000, this);

    ArrayList<EnemyTank> allEnemies = new ArrayList<>();

    public TankBattleScene()
    {
        this.enableCollisionDetect();
    }

    public void addAEnemy()
    {
        if (this.allEnemies.size() >= Common.MAX_ENEMY)
            return;

        String[] data = new String[]
        {
            "Enemy.png"
        };

        int index = abs(theRandom.nextInt()) % data.length;

        EnemyTank aEnemy = new EnemyTank("Resource/" + data[index]);

        int num = theRandom.nextInt() % 3;
        aEnemy.setCentreX(((this.getWidth() - aEnemy.getWidth()) / 2.0) * num);
        aEnemy.setCentreY(aEnemy.getHeight());

        if (theRandom.nextBoolean())
            aEnemy.setVelocityY(Common.SPEED_ENEMY_TANK);
        else
            aEnemy.setVelocityX(Common.SPEED_ENEMY_TANK);

        this.addSprite(aEnemy);
        this.addAEnemy(aEnemy);
    }

    private void addLabels()
    {
        int tmpWidth = 150;
        int tmpHeight = 20;

        if (lblMyLife == null)
        {
            lblMyLife = new LabelSprite(0, 0, "My Life: " + this.mylife, null);

            lblMyLife.setWidth(tmpWidth);

            lblMyLife.setHeight(tmpHeight);

            lblMyLife.setRed(
                255);
            lblMyLife.bTextFrame = false;
            lblMyLife.setLayer(Common.LAYER_TEXT);

            addSprite(lblMyLife);
        }

        if (lblEnemyKilled == null)
        {
            lblEnemyKilled = new LabelSprite(0, 0, "Enemy Killed: " + this.enemyKilled, null);

            lblEnemyKilled.setWidth(tmpWidth);

            lblEnemyKilled.setHeight(tmpHeight);

            lblEnemyKilled.setRed(
                255);
            lblEnemyKilled.bTextFrame = false;
            lblEnemyKilled.setLayer(Common.LAYER_TEXT);

            addSprite(lblEnemyKilled);
        }

        if (lblScore == null)
        {

            lblScore = new LabelSprite(0, 0, "Score: " + this.score, null);

            lblScore.setWidth(tmpWidth);

            lblScore.setHeight(tmpHeight);

            lblScore.setRed(
                255);
            lblScore.bTextFrame = false;
            lblScore.setLayer(Common.LAYER_TEXT);

            addSprite(lblScore);
        }
        this.adjustLabelPos();
    }

    void adjustLabelPos()
    {
        int tmpY = 20;
        int tmpMarginRight = 140;
        int tmpHeight = 20;
        int tmpGap = 1;
        if (lblMyLife != null)
        {
            lblMyLife.setX(this.getWidth() - tmpMarginRight);
            lblMyLife.setY(tmpY);
        }
        if (lblEnemyKilled != null)
        {
            lblEnemyKilled.setX(this.getWidth() - tmpMarginRight);
            lblEnemyKilled.setY(tmpY + tmpHeight + tmpGap);
        }
        if (lblScore != null)
        {
            lblScore.setX(this.getWidth() - tmpMarginRight);
            lblScore.setY(tmpY + (tmpHeight + tmpGap) * 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!this.bGameRunning)
            return;

        if (e.getSource().equals(this.timerForEnemy))
            this.addAEnemy();
    }

    public void killAEnemy(EnemyTank aEnemy)
    {
        Score aScore = new Score("+" + Common.SCORE_ENEMY);
        aScore.setCentreX(aEnemy.getCentreX());
        aScore.setCentreY(aEnemy.getCentreY());
        aScore.setWidth(50);
        aScore.setHeight(15);
        this.addSprite(aScore);

        this.score += Common.SCORE_ENEMY;

        this.enemyKilled++;
        this.updateLabels();
    }

    public void lostALife()
    {
        this.mylife--;
        this.updateLabels();
    }

    public void updateLabels()
    {
        if (this.lblEnemyKilled != null)
        {
            this.lblEnemyKilled.setText("Enemy Killed: " + this.enemyKilled);
        }
        if (this.lblScore != null)
        {
            this.lblScore.setText("Score: " + this.score);
        }
        if (this.lblMyLife != null)
        {
            this.lblMyLife.setText("My Life: " + this.mylife);
        }
    }

    @Override
    public void setSize(Dimension d)
    {
        super.setSize(d); //To change body of generated methods, choose Tools | Templates.

        this.adjustLabelPos();
    }

    public void addAEnemy(EnemyTank aEnemy)
    {
        this.allEnemies.add(aEnemy);
    }

    public void deleteAEnemy(EnemyTank aEnemy)
    {
        this.allEnemies.remove(aEnemy);
    }

    public void gameStart()
    {
        this.buildWalls();

        enemyKilled = 0;
        mylife = 3;
        score = 0;
        this.updateLabels();

        LabelSprite aLabel = new LabelSprite("Game Start", new Font("TimesRoman", Font.PLAIN, 30));
        aLabel.setWidth(150);
        aLabel.setHeight(30);
        aLabel.textPosY = 25;
        aLabel.setVelocityY(-50);
        aLabel.bTextFrame = true;
        aLabel.bDeadIfNoActions = true;
        aLabel.setCentreX(this.getWidth() / 2);
        aLabel.setCentreY(this.getHeight() / 2);

        AlphaToAction aAction = new AlphaToAction(aLabel);
        aAction.alphaTo(0, 1.5f);
        aLabel.addAction(aAction);

        this.addSprite(aLabel);

        theFriendTank = new FriendTank();

        theFriendTank.setCentreX(this.getWidth() / 2.0f);
        theFriendTank.setCentreY(this.getHeight());

        addSprite(theFriendTank);

        addLabels();
        
        bGameRunning = true;

        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException ex)
                {
                }

                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        timerForEnemy.start();
                    }
                });

            }

        }).start();

    }

    public void gameEnd()
    {
        this.timerForEnemy.stop();

        if (theFriendTank != null)
        {
            AlphaByAction aAction = new AlphaByAction();
            aAction.alphaBy(-1, 1);
            theFriendTank.addAction(aAction);

            theFriendTank.setVelocityY(-500);
            theFriendTank.bDeadIfNoActions = true;
        }

        for (EnemyTank aEnemy : this.allEnemies)
        {
            AlphaByAction aAction = new AlphaByAction();
            aAction.alphaBy(-1, 1);
            aEnemy.addAction(aAction);
            aEnemy.bDeadIfNoActions = true;
        }

        enemyKilled = 0;
        mylife = 3;
        score = 0;
        this.updateLabels();

        LabelSprite aLabel = new LabelSprite("Game End", new Font("TimesRoman", Font.PLAIN, 30));
        aLabel.setWidth(150);
        aLabel.setHeight(30);
        aLabel.textPosY = 25;
        aLabel.setVelocityY(-50);
        aLabel.bTextFrame = true;
        aLabel.bDeadIfNoActions = true;
        aLabel.setCentreX(this.getWidth() / 2);
        aLabel.setCentreY(this.getHeight() / 2);

        AlphaToAction aAlphaAction = new AlphaToAction(aLabel);
        aAlphaAction.alphaTo(0, 1.5f);
        aLabel.addAction(aAlphaAction);

        this.addSprite(aLabel);
        this.bGameRunning = false;
    }

    public void gamePause()
    {
        LabelSprite aLabel = new LabelSprite("Game Pause", new Font("TimesRoman", Font.PLAIN, 20));
        aLabel.setWidth(100);
        aLabel.setHeight(20);
        aLabel.bTextFrame = false;
        aLabel.bDeadIfNoActions = true;
        aLabel.setCentreX(this.getWidth() / 2);
        aLabel.setCentreY(this.getHeight() / 2);

        AlphaToAction aAction = new AlphaToAction(aLabel);
        aAction.alphaTo(0, 1.5f);
        aLabel.addAction(aAction);

        this.addSprite(aLabel);
        this.bGameRunning = false;
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (this.bGameRunning)
                this.gameEnd();
            else
                this.gameStart();
        } else if (this.bGameRunning)
        {
            if (e.getKeyChar() == 'a')
            {
                theFriendTank.movingLeft();
            } else if (e.getKeyChar() == 'd')
            {
                theFriendTank.movingRight();
            } else if (e.getKeyChar() == 'w')
            {
                theFriendTank.movingTop();
            } else if (e.getKeyChar() == 's')
                theFriendTank.movingBottom();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if (this.bGameRunning)
        {
            theFriendTank.setVelocityX(0);
            theFriendTank.setVelocityY(0);
        }
    }

}
