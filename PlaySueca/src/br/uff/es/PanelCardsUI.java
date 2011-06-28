/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelCardsUI.java
 *
 * Created on 26/03/2011, 12:15:02
 */

package br.uff.es;

import br.uff.es.ctrl.PanelCardsController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;

/**
 *
 * @author Goop
 */
public class PanelCardsUI extends javax.swing.JPanel {

    Map<String,JButton> cardsUiMap;

    /** Creates new form PanelCardsUI */
    public PanelCardsUI() {
        initComponents();
        rodadaPanel.setVisible(false);
        trunfoLabel.setVisible(false);
        trunfoPanel.setVisible(false);
        cardsCtrl = new PanelCardsController();
        makeCards();
    }

    private void giveCardToPlayer(int player,int numCard,String card) {
        JButton jBtn = cardsUiMap.get(card);
        if (player==0) 
            jBtn.setLocation(20, 380-(numCard*35));
        else if (player==1) 
            jBtn.setLocation(130+(350-(numCard*35)), 0);
        else if (player==2)
            jBtn.setLocation(620, 380-(numCard*35));
        else if (player==3)
            jBtn.setLocation(130+(350-(numCard*35)), 430);
        jBtn.setEnabled(player==0);
        jBtn.setVisible(true);
        remove(jBtn);
        add(jBtn);
    }

    public void giveCards() {
        cardsCtrl.resetPartida();
        giveCards(cardsCtrl.raffleCards());
    }

    private void giveCards(List<String> cardsRaffled) {
        for (int i=0;i<cardsRaffled.size();i++) {
            int player = i % 4;
            cardsCtrl.getPartida().entregaCarta(player, cardsRaffled.get(i));
        }
        cardsCtrl.getPartida().ordenaCartas();
        for (int player=0;player<4;player++) {
            List<String> cartasJogador = cardsCtrl.getPartida().getCartasJogadorNaMesa(player);
            for(int i=0;i<cartasJogador.size();i++) {
                giveCardToPlayer(player, i, cartasJogador.get(i));
            }
        }
        String trunfo = cardsRaffled.get(cardsRaffled.size()-1);
        cardsCtrl.getPartida().setTrunfo(trunfo);
        showUiTrunfo(trunfo);
        //cardsCtrl.getPartida().setCartasNaMesa(new ArrayList<String>(cardsRaffled));
    }

    private String getKey(JButton card) {
        for (String cardKey : cardsUiMap.keySet()) {
            JButton cardSeached = cardsUiMap.get(cardKey);
            if (cardSeached.equals(card))
                return cardKey;
        }
        return null;
    }

    private void updateCartas() {
        int player = cardsCtrl.getPartida().getJogadorNaMesa();
        List<String> cartasJogador = cardsCtrl.getPartida().getCartasJogadorNaMesa(player);
        for (String key : cardsUiMap.keySet()) {
            JButton cardUi = cardsUiMap.get(key);
            cardUi.setEnabled(cartasJogador.contains(key));
        }
    }

    private void registerCardAction(JButton card) {
        card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton btn = (JButton)evt.getSource();
                String cardKey = getKey(btn);
                int player = cardsCtrl.getPartida().getJogadorNaMesa();
                if (player==0)
                    btn.setLocation(btn.getX()+20, btn.getY());
                else if (player==1)
                    btn.setLocation(btn.getX(), btn.getY()+20);
                else if (player==2)
                    btn.setLocation(btn.getX()-20, btn.getY());
                else
                    btn.setLocation(btn.getX(), btn.getY()-20);
                cardsCtrl.getPartida().jogaCarta(cardKey);
                updateCartas();
            }
        });
    }

    public void makeCards() {
        cardsUiMap = new HashMap<String, JButton>();
        for (String naipe : cardsCtrl.getCards().keySet()) {
            for (String card : cardsCtrl.getCards().get(naipe).keySet()) {
                JButton jBtn = createUiCard(card, naipe);
                registerCardAction(jBtn);
                this.add(jBtn);
                cardsUiMap.put(cardsCtrl.generateCardId(naipe, card), jBtn);
            }
        }
    }

    private void showUiTrunfo(String trunfo) {
        String[] map = trunfo.split(" de ");
        JButton trunfoUi = createUiCard(map[0], map[1]);
        trunfoUi.setVisible(true);
        if (trunfoPanel.getComponentCount()==0) {
            trunfoLabel.setVisible(true);
            trunfoPanel.setVisible(true);
        } else {
            trunfoPanel.remove(0);
        }
        trunfoPanel.repaint();
        trunfoPanel.add(trunfoUi);

    }

    private JButton createUiCard(String card,String naipe) {
        JButton jBtn = new JButton();
        jBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/"+naipe+"/"+card+".png")));
        jBtn.setContentAreaFilled(false);
        jBtn.setSize(77, 104);
        jBtn.setVisible(false);
        return jBtn;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trunfoPanel = new javax.swing.JPanel();
        trunfoLabel = new javax.swing.JLabel();
        rodadaPanel = new javax.swing.JPanel();
        p0Label = new javax.swing.JLabel();
        p1Label = new javax.swing.JLabel();
        p2Label = new javax.swing.JLabel();
        p3Label = new javax.swing.JLabel();
        p0Val = new javax.swing.JLabel();
        p1Val = new javax.swing.JLabel();
        p2Val = new javax.swing.JLabel();
        p3Val = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 102));

        trunfoPanel.setPreferredSize(new java.awt.Dimension(77, 104));

        javax.swing.GroupLayout trunfoPanelLayout = new javax.swing.GroupLayout(trunfoPanel);
        trunfoPanel.setLayout(trunfoPanelLayout);
        trunfoPanelLayout.setHorizontalGroup(
            trunfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );
        trunfoPanelLayout.setVerticalGroup(
            trunfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        trunfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trunfoLabel.setText("Trunfo");

        p0Label.setText("Jog. 1:");

        p1Label.setText("Jog. 2:");

        p2Label.setText("Jog. 3:");

        p3Label.setText("Jog. 4:");

        javax.swing.GroupLayout rodadaPanelLayout = new javax.swing.GroupLayout(rodadaPanel);
        rodadaPanel.setLayout(rodadaPanelLayout);
        rodadaPanelLayout.setHorizontalGroup(
            rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rodadaPanelLayout.createSequentialGroup()
                        .addComponent(p0Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p0Val, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rodadaPanelLayout.createSequentialGroup()
                        .addComponent(p1Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p1Val, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(rodadaPanelLayout.createSequentialGroup()
                        .addComponent(p2Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p2Val, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(rodadaPanelLayout.createSequentialGroup()
                        .addComponent(p3Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p3Val, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        rodadaPanelLayout.setVerticalGroup(
            rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p0Label)
                    .addComponent(p0Val))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p1Label)
                    .addComponent(p1Val))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p2Label)
                    .addComponent(p2Val))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p3Label)
                    .addComponent(p3Val))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rodadaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(trunfoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trunfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(299, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rodadaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(trunfoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trunfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private PanelCardsController cardsCtrl;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel p0Label;
    private javax.swing.JLabel p0Val;
    private javax.swing.JLabel p1Label;
    private javax.swing.JLabel p1Val;
    private javax.swing.JLabel p2Label;
    private javax.swing.JLabel p2Val;
    private javax.swing.JLabel p3Label;
    private javax.swing.JLabel p3Val;
    private javax.swing.JPanel rodadaPanel;
    private javax.swing.JLabel trunfoLabel;
    private javax.swing.JPanel trunfoPanel;
    // End of variables declaration//GEN-END:variables

}
