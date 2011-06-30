/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Goop
 */
public class Animacao {

    private boolean acabou;
    private JButton btn;

    public Animacao(JButton btn) {
        this.btn = btn;
    }

    public void animar(int player,int x, int y,int frames,int sleep) {
        acabou = false;
        final int fFrames = frames;
        final int fSleep = sleep;
        final int cx = x;
        final int cy = y;
        final int p = player;
        Runnable r = new Runnable() {

            public void run() {
                Point pt = new Point(btn.getLocation());
                int deltax,deltay = 0;
                /*if (fOrient==0) {
                   deltax = 250-(int)pt.getX();
                   deltay = 230-(int)pt.getY();
                } else if (fOrient==1) {
                   deltax = 300-(int)pt.getX();
                   deltay = 180-(int)pt.getY();
                } else if (fOrient==2) {
                   deltax = 350-(int)pt.getX();
                   deltay = 230-(int)pt.getY();
                } else {
                   deltax = 300-(int)pt.getX();
                   deltay = 280-(int)pt.getY();
                }*/
                deltax = cx-(int)pt.getX();
                deltay = cy-(int)pt.getY();
                deltax = deltax/fFrames;
                deltay = deltay/fFrames;
                for (int i=0;i<fFrames;i++) {
                    btn.setLocation((int)btn.getLocation().getX()+(deltax),(int)btn.getLocation().getY()+(deltay));
                    
                    try {
                        Thread.sleep(fSleep);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Animacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Player "+p+" - "+btn.getLocation());
                acabou = true;
            }
        };
        Thread t = new Thread(r);
        t.start();
        //while (!acabou);
    }
}
