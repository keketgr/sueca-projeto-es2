/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uff.es;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Goop
 */
public class PanelCards extends JPanel implements ActionListener {

    public PanelCards() {
        initialize();
    }

    private void initialize() {
        JButton jBtn = new JButton();
        jBtn.setIcon(new javax.swing.ImageIcon("M:\\Meus documentos\\Facul\\es2\\projeto sueca\\b2fv.png"));
        jBtn.setContentAreaFilled(false);
        this.add(jBtn);
        jBtn.setBounds(20, 100, 71, 97);
    }



    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
