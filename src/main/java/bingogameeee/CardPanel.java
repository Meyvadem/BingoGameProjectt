/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bingogameeee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Merve
 */
public class CardPanel extends javax.swing.JFrame {

    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<JButton> unColoredbuttons = new ArrayList<>();
    private static final int ROWS = 3;
    private static final int COLS = 9;
    MultiLinkedList linkedList = new MultiLinkedList();
    Boolean cardStatus = null;

    public CardPanel() {
        initComponents();
    }
    
     public CardPanel(Color foreGColor, int numbers[][]) {
        initComponents();
        setSize(600, 500);

        jPanel1.setLayout(null);
        jPanel1.setBackground(foreGColor);
        gridPanel.setBounds(8, 8, 540, 180);
        jPanel1.add(gridPanel);  // Verilen size ve kordinatlara göre Absolute layout kullanarak jPanel1'a gridPanel ekle

        jPanel2.setLayout(null);
        jPanel1.setBounds(0, 119, 556, 196);
        jPanel2.add(jPanel1);    // Verilen size ve kordinatlara göre Absolute layout kullanarak jPanel2'e jPanel1 ekle
        linkedList.createMultilinkedList();   // cardPanel için default değerler ile multi link list oluştur

        cardProporties(foreGColor);  // Verilen renkte cardPanel oluştur

        randomSelectButton(buttons, foreGColor, numbers);  // Her satırda rastgele dört adet butonu ayırma ve verilen rengi(foreGColor) butonun arkaplanı yapma
        unColoredbuttons = getButtonList();   // Sayıların yazılı olduğu 15 butonu al

        createNumbers(buttons, numbers);   // Multilinkliste ve buttonlara random sayı atama

        linkedList.printMultilinkedList();   // Multilinklisti yazdırma

    }

    public JPanel getPanel() {
        return jPanel2; // backPanel'i döndüren get metodu
    }

    public JRadioButton getRadioButton1() {
        return jRadioButton1; // jRadioButton1'i döndüren get metodu
    }

    public JRadioButton getRadioButton2() {
        return jRadioButton2; // jRadioButton2'i döndüren get metodu
    }

    public JRadioButton getRadioButton3() {
        return jRadioButton3; // jRadioButton3'ü döndüren get metodu
    }

    public ArrayList<JButton> getButtonList() {
        for (int i = 0; i < 27; i++) {
            if (buttons.get(i).getBackground() == Color.WHITE) {
                unColoredbuttons.add(buttons.get(i));
            }
        }
        return unColoredbuttons;
    }

    // --> Verilen button özellikleri ile gridPanel'a yirmi yedi adet button koyma 
    public void cardProporties(Color foreGColor) {

        GridLayout gridLayout = new GridLayout(3, 9, 0, 0);
        gridPanel.setLayout(gridLayout);

        for (int i = 0; i < 27; i++) {
            JButton button = new JButton("");
            Font font = new Font("Arial Black", Font.BOLD, 23);
            button.setFont(font);
            button.setBackground(Color.WHITE);
            button.setForeground(foreGColor);
            button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1)); // Kenar çizgisini ekleyin
            button.setFocusPainted(true); // Buton odaklandığında oluşan efekti kaldırır
            button.setContentAreaFilled(false); // Butonun içeriğini doldurma efektini kaldırır
            button.setOpaque(true);

            // Butonun boyutunu ayarlayın
            Dimension buttonSize = new Dimension(60, 60); // Örneğin 50x50 boyutunda
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);

            buttons.add(button);
            gridPanel.add(button);
        }

    }

    // --> Her satırda rastgele dört adet butonu ayırma metodu
    void randomSelectButton(ArrayList<JButton> buttons, Color buttonColor, int numbers[][]) {

        Random random = new Random();
        int origin = 0;
        int bound = 9;

        if (numbers != null) {  // numbers null değil ise bu koşulu çalıştır
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    if (numbers[i][j] == -1) {
                        buttons.get(j + 9 * i).setBackground(buttonColor);
                        buttons.get(j + 9 * i).setText(""); // İçini boş bırakın
                    }
                }
            }

        } else {    // numbers null ise bu koşulu çalıştır
            for (int i = 0; i < 3; i++) {
                ArrayList<Integer> selectedIndices = new ArrayList<>();
                // Seçilen random indexlerin size'ı dörtten küçükse dönü devam eder ve random indexdeki buttonlar farklı renk olur. 
                // Bu döngü üç kez tekrarlanır ve 12 buton farklı renk olmuş olur.
                while (selectedIndices.size() < 4) {
                    int randomIndex = random.nextInt(origin, bound); // Dıştaki for döngüsü sırası ile 0 ile 9 / 9 ile 18 / 18 ile 27 arasında rastgele bir indeks seçin
                    if (!selectedIndices.contains(randomIndex)) {
                        selectedIndices.add(randomIndex);

                        buttons.get(randomIndex).setBackground(buttonColor);
                        buttons.get(randomIndex).setText(""); // İçini boş bırakın
                    }
                }
                origin += 9;
                bound += 9;
            }
        }
    }

    // -->  Multilinkliste ve buttonlara random sayı atama
    void createNumbers(ArrayList<JButton> buttons, int numbers[][]) {
        Set<Integer> usedNumbers = new HashSet<>(); // Kullanılan sayıların seti
        Random rand = new Random();
        int randomNumber = 0; // number değişkenini başlatıyoruz

        if (numbers != null) {  // numbers null değil ise bu koşulu çalıştır
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    if (numbers[i][j] != -1) {
                        if (buttons.get(j + 9 * i).getBackground() == Color.WHITE) {
                            buttons.get(j + 9 * i).setText(Integer.toString(numbers[i][j]));
                            linkedList.getNodeFromIndex(index).data = numbers[i][j];
                            index++;
                        }
                    }
                }
            }

        } else {    // numbers null ise bu koşulu çalıştır
            int index1 = 0;
            for (int i = 0; i < 27; i++) {
                if (buttons.get(i).getBackground() == Color.WHITE) {

                    if (i % 9 == 0 || i % 9 == 8) {
                        // İlk ve son sütunlar için
                        randomNumber = (i % 9 == 0) ? getRandomUnusedNumber(rand, usedNumbers, 1, 9) : getRandomUnusedNumber(rand, usedNumbers, 80, 90);
                    } else {
                        // Diğer sütunlar için
                        for (int k = 1; k < 8; k++) {
                            if (i % 9 == k) {
                                randomNumber = getRandomUnusedNumber(rand, usedNumbers, 10 * k, (10 * k) + 9);
                                break; // Doğru numarayı bulduktan sonra döngüden çık
                            }
                        }
                    }
                    buttons.get(i).setText(Integer.toString(randomNumber));
                    linkedList.getNodeFromIndex(index1).data = randomNumber;
                    index1++;
                }
            }
        }
    }

    public int getRandomUnusedNumber(Random rand, Set<Integer> usedNumbers, int min, int max) {
        int randomNum;
        do {
            randomNum = rand.nextInt(max - min + 1) + min; // Belirli aralıkta rastgele sayı üret
        } while (usedNumbers.contains(randomNum)); // Daha önce kullanılan bir sayı mı kontrol et
        usedNumbers.add(randomNum); // Kullanılan sayıları listeye ekle
        return randomNum;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        gridPanel = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(253, 251, 235));
        jPanel2.setPreferredSize(new java.awt.Dimension(556, 315));

        jPanel1.setBackground(new java.awt.Color(51, 120, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(556, 196));

        gridPanel.setPreferredSize(new java.awt.Dimension(300, 150));

        javax.swing.GroupLayout gridPanelLayout = new javax.swing.GroupLayout(gridPanel);
        gridPanel.setLayout(gridPanelLayout);
        gridPanelLayout.setHorizontalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );
        gridPanelLayout.setVerticalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jRadioButton1.setBackground(new java.awt.Color(253, 251, 235));
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButton1.setText("1. Çinko");

        jRadioButton2.setBackground(new java.awt.Color(253, 251, 235));
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButton2.setText("2. Çinko");

        jRadioButton3.setBackground(new java.awt.Color(253, 251, 235));
        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jRadioButton3.setText("BİNGO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CardPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CardPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gridPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    // End of variables declaration//GEN-END:variables
}
