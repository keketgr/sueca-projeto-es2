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

import br.uff.es.ctrl.CardsController;
import br.uff.es.pkg.sueca.factory.Factory;
import br.uff.es.pkg.sueca.factory.IGame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Goop
 */
public class PanelCardsUI extends javax.swing.JPanel {

    Map<String,JButton> cardsUiMap;
    List<JLabel> playersUiLabel;
    private IGame game;
    private List<String> jogadores;

    /** Creates new form PanelCardsUI */
    public PanelCardsUI() {
        initComponents();
        rodadaPanel.setVisible(false);
        trunfoLabel.setVisible(false);
        trunfoPanel.setVisible(false);
        cardsCtrl = new CardsController();
        game = Factory.getGame();
        jogadores = game.iniciaJogo();
        createUiPlayersLabel();
        makeCards();
    }

    private void giveCardToPlayer(int player,int numCard,String card) {
        JButton jBtn = cardsUiMap.get(card);
        if (player==0) 
            jBtn.setLocation(20, 400-(numCard*35));
        else if (player==1) 
            jBtn.setLocation(130+(350-(numCard*35)), 20);
        else if (player==2)
            jBtn.setLocation(620, 400-(numCard*35));
        else if (player==3)
            jBtn.setLocation(130+(350-(numCard*35)), 450);
        if (player>0) {
            escondeCarta(jBtn);
        }
        //jBtn.setEnabled(player==0);
        jBtn.setVisible(true);
        remove(jBtn);
        add(jBtn);
    }

    private Point calculaCoordenadaCartaJogadas(int player) {
        //System.out.println("PLAYER: "+player);
        if (player==0) {
            return new Point(250, 230);
        } else if (player==1) {
            return new Point(300,180);//return new Point(305,100);
        } else if (player==2) {
            return new Point(350,230);
        } else {
            return new Point(300,280);
        }
        //return null;
    }

    public void giveCards() {
        p0Val.setText("");
        p1Val.setText("");
        cardsCtrl.novaPartida();
        showUiPlayersLabel();
        giveCards(cardsCtrl.raffleCards());
    }

    private void giveCards(List<String> cardsRaffled) {

        for (int i=0;i<cardsRaffled.size();i++) {
            int player = i % 4;
            cardsCtrl.entregaCarta(player, cardsRaffled.get(i));
        }
        cardsCtrl.ordenaCartas();
        for (int player=0;player<4;player++) {
            List<String> cartasJogador = cardsCtrl.getCartasJogadorNaMesa(player);
            for(int i=0;i<cartasJogador.size();i++) {
                giveCardToPlayer(player, i, cartasJogador.get(i));
                game.entregaCarta(cartasJogador.get(i), player);
            }
        }
        String trunfo = cardsRaffled.get(cardsRaffled.size()-1);
        cardsCtrl.setTrunfo(trunfo);
        showUiTrunfo(trunfo);
        game.iniciaNovaRodada(trunfo);
        updateCartas();
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

    private void joga(String cardKey) {

        int player = jogadores.indexOf(game.getProximoJogador());
        //System.out.println(game.getProximoJogador()+ " - "+game.getNumeroJogador(game.getProximoJogador()));
        String carta = game.joga(cardKey);
        mostraCarta(carta);
        JButton btn = cardsUiMap.get(carta);
        

        Point p = calculaCoordenadaCartaJogadas(player);
        new Animacao(btn).animar(player,(int)p.getX(),(int)p.getY(),4,100);
        if (game.isFimRodada()) {
            String campeaoRodada = game.contabilizaPontos();
            List<String> cartasJogadas = game.getCartasJogadas();
            game.iniciaNovaRodada();
            p0Val.setText(String.valueOf(game.getPontosDupla1()));
            p1Val.setText(String.valueOf(game.getPontosDupla2()));
            JOptionPane.showMessageDialog(null, campeaoRodada+ " levou a rodada", "Rodada", 1);
            for (String cartaJogada : cartasJogadas)
                cardsUiMap.get(cartaJogada).setVisible(false);
            if (game.isFimJogo()) {
                int dupla = game.calculaDuplaVencedora();
                JOptionPane.showMessageDialog(null, "Vencedor da partida: Dupla "+dupla, "Vitória", 1);
                this.firePropertyChange("vencedor", 0, 1);
            }
        }
    }

    private List<String> getCartasDisponiveisParaJogo(List<String> cartasJogador) {
        List<String> cartasDoNaipe = new ArrayList<String>();
        String naipe = game.getNaipeRodada();
        if (naipe==null)
            return new ArrayList<String>(cartasJogador);
        for (String cartaJogador : cartasJogador) {
            if (cartaJogador.contains(naipe))
                cartasDoNaipe.add(cartaJogador);
        }
        if (cartasDoNaipe.isEmpty())
            return new ArrayList<String>(cartasJogador);
        return cartasDoNaipe;
    }

    private void updateCartas() {
        //int player = cardsCtrl.getPartida().getJogadorNaMesa();
        //List<String> cartasJogador = cardsCtrl.getPartida().getCartasJogadorNaMesa(player);
        String jogador = game.getProximoJogador();
        while (jogador.contains("CPU")&&!game.isFimJogo()) {
            jogador = game.getProximoJogador();
            if (jogador.contains("CPU"))
                joga(null);
            
        }
    }



    private void registerCardAction(JButton card) {
        card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton btn = (JButton)evt.getSource();
                String cardKey = getKey(btn);
                String jogador = game.getProximoJogador();
                List<String> cartasJogador = game.getMaoJogador(jogador);
                List<String> cartasParaJogar = getCartasDisponiveisParaJogo(cartasJogador);
                System.out.println("Cartas para jogar:"+cartasParaJogar);
                if (!cartasParaJogar.contains(cardKey)) {
                    JOptionPane.showMessageDialog(null, " Jogue uma carta do naipe da mesa ", "Opss", 1);
                } else if (cartasJogador.contains(cardKey)) {
                    joga(cardKey);
                    updateCartas();
                }
            }
        });
    }

    public void makeCards() {
        cardsUiMap = new HashMap<String, JButton>();
        for (String naipe : cardsCtrl.getCards().keySet()) {
            for (String card : cardsCtrl.getCards().get(naipe)) {
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
        rodadaPanel.setVisible(true);
        rodadaPanel.repaint();

    }

    private void showUiPlayersLabel() {
        for (JLabel lbl : playersUiLabel)
            lbl.setVisible(true);
    }

    private void createUiPlayersLabel() {
        playersUiLabel = new ArrayList<JLabel>();
        for (int player=0;player<jogadores.size();player++) {
            JLabel jLbl = null;
            if (player==0 || player==2) {
                jLbl = new JRotatedLabel();
                jLbl.setSize(77, 104);
            } else {
                jLbl = new JLabel();
                jLbl.setSize(77, 10);
            }
            jLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLbl.setText(jogadores.get(player));
            jLbl.setVisible(false);
            if (player==0)
                jLbl.setLocation(5, 400-(5*35));
            else if (player==1)
                jLbl.setLocation(130+(350-(5*35)), 5);
            else if (player==2)
                jLbl.setLocation(700, 400-(5*35));
            else if (player==3)
                jLbl.setLocation(130+(350-(5*35)), 560);
            playersUiLabel.add(jLbl);
            this.add(jLbl);
        }
        p0Label.setText(jogadores.get(0)+"/"+jogadores.get(2)+" :");
        p1Label.setText(jogadores.get(1)+"/"+jogadores.get(3)+" :");
    }

    private JButton createUiCard(String card,String naipe) {
        JButton jBtn = new JButton();
        jBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/"+naipe+"/"+card+".png")));
        jBtn.setContentAreaFilled(false);
        jBtn.setSize(77, 104);
        jBtn.setVisible(false);
        return jBtn;
    }

    private void escondeCarta(JButton btn) {
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/deck.png")));
    }

    private void mostraCarta(String carta) {
        String naipe = carta.substring(carta.indexOf(" de ")+4, carta.length());
        String valor = carta.substring(0,carta.indexOf(" de "));
        cardsUiMap.get(carta).setIcon(new javax.swing.ImageIcon(getClass().getResource("/"+naipe+"/"+valor+".png")));
    }

    public IGame getGame() {
        return game;
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
        p0Val = new javax.swing.JLabel();
        p1Val = new javax.swing.JLabel();

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

        rodadaPanel.setBackground(new java.awt.Color(204, 255, 204));

        p0Label.setText("Dupla 1:");

        p1Label.setText("Dupla 2:");

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
                        .addComponent(p1Val, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rodadaPanelLayout.setVerticalGroup(
            rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p0Label)
                    .addComponent(p0Val, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rodadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p1Label)
                    .addComponent(p1Val, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rodadaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
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


    private CardsController cardsCtrl;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel p0Label;
    private javax.swing.JLabel p0Val;
    private javax.swing.JLabel p1Label;
    private javax.swing.JLabel p1Val;
    private javax.swing.JPanel rodadaPanel;
    private javax.swing.JLabel trunfoLabel;
    private javax.swing.JPanel trunfoPanel;
    // End of variables declaration//GEN-END:variables

    private class JRotatedLabel extends JLabel  {
        public JRotatedLabel( ) {
            super();
            setPreferredSize( new Dimension( 30, 90 ) );
            setMinimumSize( new Dimension( 30, 90 ) );
        }

         public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(10.0, 50.0);
            g2d.rotate( 300 );
            g2d.drawString(getText(), 0, 0);
         }
    }
}
