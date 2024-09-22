/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bingogameeee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Merve
 */
public class GamePanel extends javax.swing.JFrame {

    CardPanel cardPanel1;
    CardPanel cardPanel2;
    CircleButton circleButton;
    Set<Integer> usedNumbers = new HashSet<>(); // Kullanılan sayıların seti
    
    public GamePanel() {
        initComponents();
    }
    
    public GamePanel(Player player1, Player player2, int[][] numbers1, int[][] numbers2) {
        initComponents();

        cardPanel1 = new CardPanel(player1.cardColor, numbers1);   // new Color(51, 130, 180)
        JPanel Panel1 = cardPanel1.getPanel();

        cardPanel2 = new CardPanel(player2.cardColor, numbers2);  // new Color(0, 150, 0)
        JPanel Panel2 = cardPanel2.getPanel();

        jLabel1.setText(player1.name);
        jLabel2.setText(player2.name);

        jPanelMain.setLayout(null);
        Panel1.setBounds(30, 25, 556, 315);
        Panel2.setBounds(30, 355, 556, 315);

        jPanelMain.add(Panel1);
        jPanelMain.add(Panel2);

    }

    public int generateRandomNumber(int max, Set<Integer> usedNumbers) {

        Random rand = new Random();

        int number = getRandomUnusedNumber(rand, usedNumbers, max);
        jTextField1.setText(number + "");
        return number;
    }

    public int getRandomUnusedNumber(Random rand, Set<Integer> usedNumbers, int max) {
        int randomNum;
        do {
            randomNum = rand.nextInt(max) + 1; // Belirli aralıkta rastgele sayı üret
        } while (usedNumbers.contains(randomNum)); // Daha önce kullanılan bir sayı mı kontrol et
        usedNumbers.add(randomNum); // Kullanılan sayıları listeye ekle

        jTextField2.setText(jTextField2.getText() + " " + randomNum + " ");
        return randomNum;
    }

    void searchNumber(int number, CardPanel cardPanel, CircleButton circleButton) {
        for (int i = 0; i <= 14; i++) {
            Node node = cardPanel.linkedList.getNodeFromIndex(i);
            if (node.data == number) {
                node.isFound = true;

                circleButton = new CircleButton("1");
                circleButton.setText(number + "");
                circleButton.setForeground(new Color(255, 255, 255));
                circleButton.setBackground(new Color(220, 20, 60));
                circleButton.setFont(new Font("Arial", Font.BOLD, 14));
                circleButton.setPreferredSize(new Dimension(50, 50)); // Yuvarlak düğmenin boyutu

                cardPanel.unColoredbuttons.get(i).setText("X");
                cardPanel.unColoredbuttons.get(i).setLayout(new GridBagLayout());
                cardPanel.unColoredbuttons.get(i).add(circleButton);
            }
        }

    }

    void checkStatus(CardPanel cardPanel) {
        int firstRowNumb = 0;
        int secondRowNumb = 0;
        int thirdRowNumb = 0;
        JRadioButton radioButton3 = cardPanel.getRadioButton3();
        JRadioButton radioButton2 = cardPanel.getRadioButton2();
        JRadioButton radioButton1 = cardPanel.getRadioButton1();

        for (int i = 0; i <= 14; i++) {
            Node node = cardPanel.linkedList.getNodeFromIndex(i);
            if (i <= 4) {
                if (node.isFound == true) {
                    firstRowNumb++;
                }
            } else if (i <= 9) {
                if (node.isFound == true) {
                    secondRowNumb++;
                }
            } else if (i <= 14) {
                if (node.isFound == true) {
                    thirdRowNumb++;
                }
            }
        }

        if (firstRowNumb == 5 && secondRowNumb == 5 && thirdRowNumb == 5) {
            radioButton3.setSelected(true);
            cardPanel.cardStatus = true;
        } else if ((firstRowNumb == 5 && secondRowNumb == 5) || (firstRowNumb == 5 && thirdRowNumb == 5) || (thirdRowNumb == 5 && secondRowNumb == 5)) {
            radioButton2.setSelected(true);
            cardPanel.cardStatus = false;
        } else if (firstRowNumb == 5 || secondRowNumb == 5 || thirdRowNumb == 5) {
            radioButton1.setSelected(true);
            cardPanel.cardStatus = false;
        }

    }

    void findWinner(CardPanel cardPanel1, CardPanel cardPanel2) {

        if (cardPanel1.cardStatus == null || cardPanel2.cardStatus == null) {
            return;
        }

        if (cardPanel1.cardStatus == true && cardPanel2.cardStatus == true) {
            JOptionPane.showMessageDialog(null, "Tebrikler, maç berabere!", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } else if (cardPanel1.cardStatus == true && cardPanel2.cardStatus == false) {
            JOptionPane.showMessageDialog(null, "Tebrikler, kazandınız !" + jLabel1.getText(), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        } else if (cardPanel1.cardStatus == false && cardPanel2.cardStatus == true) {
            JOptionPane.showMessageDialog(null, "Tebrikler, kazandınız! " + jLabel2.getText(), "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMain.setBackground(new java.awt.Color(253, 251, 235));
        jPanelMain.setPreferredSize(new java.awt.Dimension(1100, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Player 1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Player 2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Selected Random Number");

        jButton1.setText("Get Number");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("All Random Numbers Selected");

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 489, Short.MAX_VALUE)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(193, 193, 193))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton1))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel2)))
                .addGap(107, 107, 107)
                .addComponent(jButton2)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int number = generateRandomNumber(90, usedNumbers);

        searchNumber(number, cardPanel1, circleButton);
        checkStatus(cardPanel1);
        searchNumber(number, cardPanel2, circleButton);
        checkStatus(cardPanel2);

        findWinner(cardPanel1, cardPanel2);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();;
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamePanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
