/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wang.game.tankbattle.scene;

import com.wang.Game2dEngine.action.AlphaByAction;
import com.wang.Game2dEngine.action.AlphaToAction;
import com.wang.Game2dEngine.sprite.UI.SLabel;
import com.wang.Game2dEngine.sprite.Sprite;
import com.wang.math.geometry.ClosureShape;
import com.wang.math.geometry.SpecialRectangleShape;
import com.wang.game.tankbattle.common.Common;
import com.wang.game.tankbattle.other.Score;
import com.wang.game.tankbattle.sprites.tank.EnemyTank;
import com.wang.game.tankbattle.sprites.tank.FriendTank;
import com.wang.game.tankbattle.sprites.tank.KingEnemyTank;
import com.wang.game.tankbattle.sprites.tank.Tank;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.Timer;
import com.wang.Game2dEngine.Shape.Interface.IEShape;

/**
 *
 * @author ricolwang
 */
public class TankBattleScene extends WallScene
        implements ActionListener
{

    public boolean bGameRunning;
    public SLabel lblEnemyKilled;
    public SLabel lblMyLife;
    public SLabel lblScore;
    public SLabel lblHelpW;
    public SLabel lblHelpS;
    public SLabel lblHelpA;
    public SLabel lblHelpD;
    public SLabel lblHelpSpace;

    int enemyKilled = 0;
    int mylife = 3;
    int score = 0;

    FriendTank theFriendTank;

    Timer timerForEnemy = new Timer(2000, this);

    ArrayList<EnemyTank> allEnemies = new ArrayList<>();

    public TankBattleScene()
    {
        enableCollisionDetect();
    }

    private boolean isPossibleToCreateAEnemy(SpecialRectangleShape area)
    {
        boolean bCollide = false;

        for (Sprite aSprite : this.getAllSprites())
        {
            if (aSprite instanceof Tank)
            {
                Tank aTank = (Tank) aSprite;

                IEShape theShape = aTank.getTheShape();
                if (theShape instanceof ClosureShape)
                {
                    ClosureShape aClosureShape = (ClosureShape) theShape;
                    if (aClosureShape.collideWith(area))
                    {
                        bCollide = true;
                        break;
                    }
                }
            }
        }

        if (bCollide)
        {
            return false;
        }

        if (this.allEnemies.size() >= Common.MAX_ENEMY)
        {
            return false;
        }

        return true;
    }

    private String getARandomEnemyImage(boolean bKingEnemy)
    {
        String[] dataEnemy = new String[]
        {
            "tank1.png", "tank2.png", "tank3.png", "tank4.png"
        };

        String[] dataKingEnemy = new String[]
        {
            "tank5.png", "tank6.png", "tank7.png", "tank8.png"
        };

        int index = abs(theRandom.nextInt()) % (bKingEnemy ? dataKingEnemy.length : dataEnemy.length);

        return "Resource/" + (bKingEnemy ? dataKingEnemy[index] : dataEnemy[index]);
    }

    private EnemyTank addAEnemyCenter()
    {
        double width = 50;
        SpecialRectangleShape aRectangle = new SpecialRectangleShape(this.getWidth() / 2 - width / 2, this.theWallTop.getY() + this.theWallTop.getHeight() + 5, width, width);
        if (!isPossibleToCreateAEnemy(aRectangle)) return null;

        EnemyTank aEnemy = theRandom.nextInt() % 10 > 7 ? new KingEnemyTank(getARandomEnemyImage(true)) : new EnemyTank(getARandomEnemyImage(false));
        aEnemy.setX(aRectangle.left);
        aEnemy.setY(aRectangle.top);

        int result = theRandom.nextInt() % 10;
        if (result > 7)
        {
            aEnemy.movingBottom();
        } else if (result > 4)
        {
            aEnemy.movingRight();
        } else
        {
            aEnemy.movingLeft();
        }

        return aEnemy;
    }

    private EnemyTank addAEnemyLeft()
    {
        double width = 50;
        SpecialRectangleShape aRectangle = new SpecialRectangleShape(30, this.theWallTop.getY() + this.theWallTop.getHeight() + 5, width, width);
        if (!isPossibleToCreateAEnemy(aRectangle)) return null;

        EnemyTank aEnemy = theRandom.nextInt() % 10 > 7 ? new KingEnemyTank(getARandomEnemyImage(true)) : new EnemyTank(getARandomEnemyImage(false));
        aEnemy.setX(aRectangle.left);
        aEnemy.setY(aRectangle.top);

        if (theRandom.nextBoolean())
        {
            aEnemy.movingBottom();
        } else
        {
            aEnemy.movingRight();
        }

        return aEnemy;
    }

    private EnemyTank addAEnemyRight()
    {
        double width = 50;
        SpecialRectangleShape aRectangle = new SpecialRectangleShape(this.getWidth() - width - 30, this.theWallTop.getY() + this.theWallTop.getHeight() + 5, width, width);
        if (!isPossibleToCreateAEnemy(aRectangle)) return null;

        EnemyTank aEnemy = theRandom.nextInt() % 10 > 7 ? new KingEnemyTank(getARandomEnemyImage(true)) : new EnemyTank(getARandomEnemyImage(false));
        aEnemy.setX(aRectangle.left);
        aEnemy.setY(aRectangle.top);

        if (theRandom.nextBoolean())
        {
            aEnemy.movingBottom();
        } else
        {
            aEnemy.movingLeft();
        }

        return aEnemy;
    }

    public void addAEnemy()
    {
        EnemyTank aEnemy;
        int result = theRandom.nextInt() % 10;
        if (result > 7) aEnemy = addAEnemyCenter();
        else if (result > 4) aEnemy = addAEnemyLeft();
        else aEnemy = addAEnemyRight();
        if (aEnemy != null)
        {
            this.addSprite(aEnemy);
            this.addAEnemy(aEnemy);
        }
    }

    private void addLabels()
    {
        int tmpWidth = 150;
        int tmpHeight = 20;

        if (lblMyLife == null)
        {
            lblMyLife = new SLabel(0, 0, "My Life: " + this.mylife, null);

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
            lblEnemyKilled = new SLabel(0, 0, "Enemy Killed: " + this.enemyKilled, null);

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

            lblScore = new SLabel(0, 0, "Score: " + this.score, null);

            lblScore.setWidth(tmpWidth);

            lblScore.setHeight(tmpHeight);

            lblScore.setRed(
                    255);
            lblScore.bTextFrame = false;
            lblScore.setLayer(Common.LAYER_TEXT);

            addSprite(lblScore);
        }
        this.adjustLabelPos();

        int tmpBottomBase = 20;
        int tmpBottom = 25;
        int tmpMarginRight = 140;
        int tmpGap = 1;

        if (lblHelpW == null)
        {
            lblHelpW = new SLabel(this.getWidth() - tmpMarginRight, this.getHeight() - (tmpBottom + tmpGap) * 5 - tmpBottomBase, "W: UP", null);

            lblHelpW.setWidth(tmpWidth);

            lblHelpW.setHeight(tmpHeight);

            lblHelpW.setRed(
                    255);
            lblHelpW.bTextFrame = false;
            lblHelpW.setLayer(Common.LAYER_TEXT);

            addSprite(lblHelpW);
        }

        if (lblHelpA == null)
        {
            lblHelpA = new SLabel(this.getWidth() - tmpMarginRight, this.getHeight() - (tmpBottom + tmpGap) * 4 - tmpBottomBase, "A: LEFT", null);

            lblHelpA.setWidth(tmpWidth);

            lblHelpA.setHeight(tmpHeight);

            lblHelpA.setRed(
                    255);
            lblHelpA.bTextFrame = false;
            lblHelpA.setLayer(Common.LAYER_TEXT);

            addSprite(lblHelpA);
        }

        if (lblHelpS == null)
        {
            lblHelpS = new SLabel(this.getWidth() - tmpMarginRight, this.getHeight() - (tmpBottom + tmpGap) * 3 - tmpBottomBase, "S: DOWN", null);

            lblHelpS.setWidth(tmpWidth);

            lblHelpS.setHeight(tmpHeight);

            lblHelpS.setRed(
                    255);
            lblHelpS.bTextFrame = false;
            lblHelpS.setLayer(Common.LAYER_TEXT);

            addSprite(lblHelpS);
        }

        if (lblHelpD == null)
        {
            lblHelpD = new SLabel(this.getWidth() - tmpMarginRight, this.getHeight() - (tmpBottom + tmpGap) * 2 - tmpBottomBase, "D: RIGHT", null);

            lblHelpD.setWidth(tmpWidth);

            lblHelpD.setHeight(tmpHeight);

            lblHelpD.setRed(
                    255);
            lblHelpD.bTextFrame = false;
            lblHelpD.setLayer(Common.LAYER_TEXT);

            addSprite(lblHelpD);
        }

        if (lblHelpSpace == null)
        {
            lblHelpSpace = new SLabel(this.getWidth() - tmpMarginRight, this.getHeight() - (tmpBottom + tmpGap) - tmpBottomBase, "SPACE: FIRE", null);

            lblHelpSpace.setWidth(tmpWidth);

            lblHelpSpace.setHeight(tmpHeight);

            lblHelpSpace.setRed(
                    255);
            lblHelpSpace.bTextFrame = false;
            lblHelpSpace.setLayer(Common.LAYER_TEXT);

            addSprite(lblHelpSpace);
        }
    }

    public void adjustLabelPos()
    {
        int tmpY = 40;
        int tmpMarginRight = 150;
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

        int tmpBottom = 25;
        int tmpBottomBase = 20;

        if (lblHelpW != null)
        {
            lblHelpW.setX(this.getWidth() - tmpMarginRight);
            lblHelpW.setY(this.getHeight() - (tmpBottom + tmpGap) * 5 - tmpBottomBase);
        }

        if (lblHelpA != null)
        {
            lblHelpA.setX(this.getWidth() - tmpMarginRight);
            lblHelpA.setY(this.getHeight() - (tmpBottom + tmpGap) * 4 - tmpBottomBase);
        }

        if (lblHelpS != null)
        {
            lblHelpS.setX(this.getWidth() - tmpMarginRight);
            lblHelpS.setY(this.getHeight() - (tmpBottom + tmpGap) * 3 - tmpBottomBase);
        }

        if (lblHelpD != null)
        {
            lblHelpD.setX(this.getWidth() - tmpMarginRight);
            lblHelpD.setY(this.getHeight() - (tmpBottom + tmpGap) * 2 - tmpBottomBase);
        }

        if (lblHelpSpace != null)
        {
            lblHelpSpace.setX(this.getWidth() - tmpMarginRight);
            lblHelpSpace.setY(this.getHeight() - (tmpBottom + tmpGap) - tmpBottomBase);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!this.bGameRunning)
        {
            return;
        }

        if (e.getSource().equals(this.timerForEnemy))
        {
            this.addAEnemy();
        }
    }

    public void killAEnemy(EnemyTank aEnemy)
    {
        if (!this.bGameRunning)
        {
            return;
        }

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

        SLabel aLabel = new SLabel("Game Start", new Font("TimesRoman", Font.PLAIN, 30));
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
        theFriendTank.setCentreY(this.theWallBottom.getY() - theFriendTank.getHeight() / 2 - 5);

        addSprite(theFriendTank);

        addLabels();

        timerForEnemy.start();

        bGameRunning = true;
    }

    public void gameEnd()
    {
        this.timerForEnemy.stop();

        if (theFriendTank != null)
        {
            AlphaByAction aAction = new AlphaByAction();
            aAction.alphaBy(-1, 1);
            theFriendTank.addAction(aAction);
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

        SLabel aLabel = new SLabel("Game End", new Font("TimesRoman", Font.PLAIN, 30));
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

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (this.bGameRunning)
            {
                this.gameEnd();
            } else
            {
                this.gameStart();
            }
        } else if (this.bGameRunning)
        {
            switch (e.getKeyChar())
            {
                case 'a':
                    theFriendTank.movingLeft();
                    break;
                case 'd':
                    theFriendTank.movingRight();
                    break;
                case 'w':
                    theFriendTank.movingTop();
                    break;
                case 's':
                    theFriendTank.movingBottom();
                    break;
                case KeyEvent.VK_SPACE:
                    theFriendTank.fire();
                    break;
                default:
                    break;
            }
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
