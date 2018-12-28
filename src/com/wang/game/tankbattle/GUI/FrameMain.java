package com.wang.game.tankbattle.GUI;

import com.wang.Game2dEngine.director.Director;
import com.wang.game.tankbattle.scene.TankBattleScene;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import static java.lang.System.exit;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ricolwang
 */
public class FrameMain extends javax.swing.JFrame implements KeyListener

{

    TankBattleScene theScene;
    boolean bAlreadyRun;
    MouseEvent mouseEvent;
    Random theRandom = new Random();

    public FrameMain()
    {
        initComponents();

        this.addKeyListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelGame = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuGame = new javax.swing.JMenu();
        menuStart = new javax.swing.JMenuItem();
        menuPause = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        menuOption = new javax.swing.JMenu();
        menuSettings = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowActivated(java.awt.event.WindowEvent evt)
            {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
        });

        panelGame.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout panelGameLayout = new javax.swing.GroupLayout(panelGame);
        panelGame.setLayout(panelGameLayout);
        panelGameLayout.setHorizontalGroup(
            panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 756, Short.MAX_VALUE)
        );
        panelGameLayout.setVerticalGroup(
            panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        getContentPane().add(panelGame, java.awt.BorderLayout.CENTER);

        menuGame.setText("Game");

        menuStart.setText("Start");
        menuStart.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuStartActionPerformed(evt);
            }
        });
        menuGame.add(menuStart);

        menuPause.setText("Pause");
        menuPause.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuPauseActionPerformed(evt);
            }
        });
        menuGame.add(menuPause);
        menuGame.add(jSeparator1);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuExitActionPerformed(evt);
            }
        });
        menuGame.add(menuExit);

        jMenuBar1.add(menuGame);

        menuOption.setText("Option");

        menuSettings.setText("Settings");
        menuOption.add(menuSettings);

        jMenuBar1.add(menuOption);

        menuHelp.setText("Help");

        menuAbout.setText("About");
        menuHelp.add(menuAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        Director.getSharedInstance().setParent(this.panelGame);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowActivated
    {//GEN-HEADEREND:event_formWindowActivated
    }//GEN-LAST:event_formWindowActivated

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        if (theScene != null)
        {
            theScene.adjustLabelPos();
        }
    }//GEN-LAST:event_formComponentResized

    private void menuStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuStartActionPerformed
    {//GEN-HEADEREND:event_menuStartActionPerformed
        if (theScene == null)
        {
            theScene = new TankBattleScene();
            Director.getSharedInstance().showScene(theScene);
            theScene.gameStart();
        }

        this.requestFocus();
    }//GEN-LAST:event_menuStartActionPerformed

    private void menuPauseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuPauseActionPerformed
    {//GEN-HEADEREND:event_menuPauseActionPerformed
        if (theScene != null) theScene.pause();
    }//GEN-LAST:event_menuPauseActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuExitActionPerformed
    {//GEN-HEADEREND:event_menuExitActionPerformed
        exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenu menuGame;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuOption;
    private javax.swing.JMenuItem menuPause;
    private javax.swing.JMenuItem menuSettings;
    private javax.swing.JMenuItem menuStart;
    private javax.swing.JPanel panelGame;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (this.theScene != null)
        {
            this.theScene.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (this.theScene != null)
        {
            this.theScene.keyReleased(e);
        }
    }

}
