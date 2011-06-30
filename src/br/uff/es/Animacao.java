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

    public void animar(int orientacao,int frames,int sleep) {
        acabou = false;
        final int fFrames = frames;
        final int fSleep = sleep;
        final int fOrient = orientacao;
        Runnable r = new Runnable() {

            public void run() {
                System.out.println("Dormindo de dormir");
                Point pt = new Point(btn.getLocation());
                int deltax,deltay = 0;
                if (fOrient==0) {
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
                }
                deltax = deltax/fFrames;
                deltay = deltay/fFrames;
                for (int i=0;i<fFrames;i++) {
                    btn.setLocation((int)btn.getLocation().getX()+(deltax),(int)btn.getLocation().getY()+(deltay));
                    /*if (fOrient==0)
                        btn.setLocation(250, 230);
                    else if (fOrient==1)
                        btn.setLocation(300, 180);
                    else if (fOrient==2)
                        btn.setLocation(350, 230);
                    else
                        btn.setLocation(300, 280);*/
                    try {
                        Thread.sleep(fSleep);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Animacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(btn.getLocation());
                acabou = true;
            }
        };
        Thread t = new Thread(r);
        t.start();
        //while (!acabou);
    }
}
