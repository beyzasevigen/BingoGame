package com.mycompany.bingogame1;

import java.awt.Color;
import java.awt.Component;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Beyza
 */
public class MainGamePanel extends javax.swing.JFrame {

    LinkedList linkedList1;
    LinkedList linkedList2;

    ArrayList<JButton> buttonList1 = new ArrayList<>();
    ArrayList<JButton> buttonList2 = new ArrayList<>();
    Set<Integer> usedNumbers = new HashSet<>();
    Component[] buttons1;
    Component[] buttons2;
    
    private int[][] player1Matrix = {
        { 1, -1, 21, -1, -1, 52, 66, 78, -1 },
        { 6, -1, -1, -1, -1, 55, 60, 72, 89 },
        { 7, -1, 23, 34, 48, -1, -1, -1, 81 }
    };

    private int[][] player2Matrix = {
        { -1, -1, -1, -1, 45, 52, 64, 70, 82 },
        { 5, 16, -1, -1, 42, -1, 66, -1, 88 },
        { 1, -1, 23, -1, 40, 56, -1, 77, -1 }
    };
            
            
    public MainGamePanel() {
        initComponents();
        
        setFirstPlayerTable(player1Matrix);
        setSecondPlayerTable(player2Matrix);
    }

    public static int generatePermutation() {
        Random random = new Random();
        int randomNumber = random.nextInt(90) + 1;
        txtNumPanel.setText(String.valueOf(randomNumber));
        return randomNumber;
    }

    public static int[][] generateMatrix() {
        Random r = new Random();
        int[][] matrix = new int[3][9];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int num = r.nextInt(10) +1+ 10 * j;
                matrix[i][j] = num;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int blank;
                do {
                    blank = r.nextInt(9);
                } while (matrix[i][blank] == -1);
                matrix[i][blank] = -1;
            }
        }

        return matrix;
    }

    public static LinkedList convertToLinkedList(int[][] matrix) {

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != -1) {
                    linkedList.add(matrix[i][j]);
                }
            }
        }

        for (int i = 0; i < linkedList.size() - 1; i++) {
            System.out.print(linkedList.get(i) + ", ");
        }
        System.out.print(linkedList.get(linkedList.size() - 1));

        return linkedList;
    }

//    static int[][] permutationMatrixe;
//    static LinkedList[] linkedLists;

    public void setFirstPlayerTable(int[][] permutationMatrix) {
//        int[][] permutationMatrix = generateMatrix();
        linkedList1 = convertToLinkedList(permutationMatrix);
        buttons1 = panelPlayer1.getComponents();
        for (Component button : buttons1) {
            if (button instanceof JButton jbutton) {
                buttonList1.add(jbutton);
            }
        }

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (linkedList1.get(index) / 10 == j) {
                    buttonList1.get((i * 9) + j).setText(String.valueOf(linkedList1.get(index)));
                    index++;
                } else {
                    buttonList1.get((i * 9) + j).setBackground(Color.DARK_GRAY);
                }
            }

        }
    }

    public void setSecondPlayerTable(int[][] permutationMatrix) {
//        int[][] permutationMatrix = generateMatrix();
        linkedList2 = convertToLinkedList(permutationMatrix);
        buttons2 = panelPlayer2.getComponents();
        for (Component button : buttons2) {
            if (button instanceof JButton jbutton) {
                buttonList2.add(jbutton);
            }
        }

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (linkedList2.get(index) / 10 == j) {
                    buttonList2.get((i * 9) + j).setText(String.valueOf(linkedList2.get(index)));
                    index++;
                } else {
                    buttonList2.get((i * 9) + j).setBackground(Color.DARK_GRAY);
                }
            }
        }
    }

    public void checkBingo() {
        int newCinkoCount1 = linkedList1.checkBingo();
        int newCinkoCount2 = linkedList2.checkBingo();

        switch (newCinkoCount1) {
            case 1 ->
                p1checkbox1st.setSelected(true);
            case 2 ->
                p1checkbox2nd.setSelected(true);
            case 3 ->
                p1checkbox3rd.setSelected(true);
            default -> {
            }
        }

        switch (newCinkoCount2) {
            case 1 ->
                p2checkbox1st.setSelected(true);
            case 2 ->
                p2checkbox2nd.setSelected(true);
            case 3 ->
                p2checkbox3rd.setSelected(true);
            default -> {
            }
        }
        if (newCinkoCount1 == 3) {
            JOptionPane.showMessageDialog(this, "Player 1 won!!", "Bingo Winner", JOptionPane.INFORMATION_MESSAGE);
        }

        if (newCinkoCount2 == 3) {
            JOptionPane.showMessageDialog(this, "Player 2 won!!", "Bingo Winner", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void markNumber() {

        if (usedNumbers.size() == 90) {
            return;
        }

        int randomNum = generatePermutation();
        while (usedNumbers.contains(randomNum)) {
            randomNum = generatePermutation();
        }
        usedNumbers.add(randomNum);

        linkedList1.markNumber(randomNum);
        linkedList2.markNumber(randomNum);

        for (int i = 0; i < buttonList1.size(); i++) {
            String buttonText = buttonList1.get(i).getText();
            if (!buttonText.isEmpty()) {
                int buttonNumber = Integer.parseInt(buttonText);
                if (buttonNumber == randomNum) {
                    buttonList1.get(i).setBackground(Color.RED);
                }
            }
        }
        for (int i = 0; i < buttonList2.size(); i++) {
            String buttonText = buttonList2.get(i).getText();
            if (!buttonText.isEmpty()) {
                int buttonNumber = Integer.parseInt(buttonText);
                if (buttonNumber == randomNum) {
                    buttonList2.get(i).setBackground(Color.RED);
                }
            }
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

        panelBackground = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNumPanel = new javax.swing.JTextPane();
        p2checkbox3rd = new javax.swing.JCheckBox();
        p1checkbox1st = new javax.swing.JCheckBox();
        p1checkbox2nd = new javax.swing.JCheckBox();
        p1checkbox3rd = new javax.swing.JCheckBox();
        p2checkbox1st = new javax.swing.JCheckBox();
        p2checkbox2nd = new javax.swing.JCheckBox();
        btnEditNumP1 = new javax.swing.JButton();
        panelPlayer1 = new javax.swing.JPanel();
        p1r1c1 = new javax.swing.JButton();
        p1r1c2 = new javax.swing.JButton();
        p1r1c3 = new javax.swing.JButton();
        p1r1c4 = new javax.swing.JButton();
        p1r1c5 = new javax.swing.JButton();
        p1r1c6 = new javax.swing.JButton();
        p1r1c7 = new javax.swing.JButton();
        p1r1c8 = new javax.swing.JButton();
        p1r1c9 = new javax.swing.JButton();
        p1r2c1 = new javax.swing.JButton();
        p1r2c2 = new javax.swing.JButton();
        p1r2c3 = new javax.swing.JButton();
        p1r2c4 = new javax.swing.JButton();
        p1r2c5 = new javax.swing.JButton();
        p1r2c6 = new javax.swing.JButton();
        p1r2c7 = new javax.swing.JButton();
        p1r2c8 = new javax.swing.JButton();
        p1r2c9 = new javax.swing.JButton();
        p1r3c1 = new javax.swing.JButton();
        p1r3c2 = new javax.swing.JButton();
        p1r3c3 = new javax.swing.JButton();
        p1r3c4 = new javax.swing.JButton();
        p1r3c5 = new javax.swing.JButton();
        p1r3c6 = new javax.swing.JButton();
        p1r3c7 = new javax.swing.JButton();
        p1r3c8 = new javax.swing.JButton();
        p1r3c9 = new javax.swing.JButton();
        panelPlayer1Background = new javax.swing.JPanel();
        panelPlayer2Background = new javax.swing.JPanel();
        panelPlayer2 = new javax.swing.JPanel();
        p2r1c1 = new javax.swing.JButton();
        p2r1c2 = new javax.swing.JButton();
        p2r1c3 = new javax.swing.JButton();
        p2r1c4 = new javax.swing.JButton();
        p2r1c5 = new javax.swing.JButton();
        p2r1c6 = new javax.swing.JButton();
        p2r1c7 = new javax.swing.JButton();
        p2r1c8 = new javax.swing.JButton();
        p2r1c9 = new javax.swing.JButton();
        p2r2c1 = new javax.swing.JButton();
        p2r2c2 = new javax.swing.JButton();
        p2r2c3 = new javax.swing.JButton();
        p2r2c4 = new javax.swing.JButton();
        p2r2c5 = new javax.swing.JButton();
        p2r2c6 = new javax.swing.JButton();
        p2r2c7 = new javax.swing.JButton();
        p2r2c8 = new javax.swing.JButton();
        p2r2c9 = new javax.swing.JButton();
        p2r3c1 = new javax.swing.JButton();
        p2r3c2 = new javax.swing.JButton();
        p2r3c3 = new javax.swing.JButton();
        p2r3c4 = new javax.swing.JButton();
        p2r3c5 = new javax.swing.JButton();
        p2r3c6 = new javax.swing.JButton();
        p2r3c7 = new javax.swing.JButton();
        p2r3c8 = new javax.swing.JButton();
        p2r3c9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBackground.setBackground(new java.awt.Color(204, 204, 204));
        panelBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label2.setBackground(new java.awt.Color(153, 153, 225));
        label2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(51, 51, 51));
        label2.setText("  Player 2");
        panelBackground.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 180, -1));

        label1.setBackground(new java.awt.Color(204, 255, 204));
        label1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(51, 51, 51));
        label1.setText("  Player 1");
        panelBackground.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 180, -1));

        txtNumPanel.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        txtNumPanel.setEnabled(false);
        txtNumPanel.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtNumPanel);

        panelBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 110, 100));

        p2checkbox3rd.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        p2checkbox3rd.setForeground(new java.awt.Color(51, 51, 51));
        p2checkbox3rd.setText("3rd Bingo");
        p2checkbox3rd.setEnabled(false);
        panelBackground.add(p2checkbox3rd, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, -1, -1));

        p1checkbox1st.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        p1checkbox1st.setForeground(new java.awt.Color(51, 51, 51));
        p1checkbox1st.setText("1st Bingo");
        p1checkbox1st.setEnabled(false);
        panelBackground.add(p1checkbox1st, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        p1checkbox2nd.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        p1checkbox2nd.setForeground(new java.awt.Color(51, 51, 51));
        p1checkbox2nd.setText("2nd Bingo");
        p1checkbox2nd.setEnabled(false);
        panelBackground.add(p1checkbox2nd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        p1checkbox3rd.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        p1checkbox3rd.setForeground(new java.awt.Color(51, 51, 51));
        p1checkbox3rd.setText("3rd Bingo");
        p1checkbox3rd.setEnabled(false);
        panelBackground.add(p1checkbox3rd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        p2checkbox1st.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        p2checkbox1st.setForeground(new java.awt.Color(51, 51, 51));
        p2checkbox1st.setText("1st Bingo");
        p2checkbox1st.setEnabled(false);
        panelBackground.add(p2checkbox1st, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, -1, -1));

        p2checkbox2nd.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        p2checkbox2nd.setForeground(new java.awt.Color(51, 51, 51));
        p2checkbox2nd.setText("2nd Bingo");
        p2checkbox2nd.setEnabled(false);
        panelBackground.add(p2checkbox2nd, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, -1, -1));

        btnEditNumP1.setBackground(new java.awt.Color(51, 51, 51));
        btnEditNumP1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnEditNumP1.setForeground(new java.awt.Color(204, 204, 204));
        btnEditNumP1.setText("new number");
        btnEditNumP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNumP1ActionPerformed(evt);
            }
        });
        panelBackground.add(btnEditNumP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, 30));

        panelPlayer1.setBackground(new java.awt.Color(0, 204, 153));
        panelPlayer1.setToolTipText("");
        panelPlayer1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelPlayer1.setName(""); // NOI18N
        panelPlayer1.setLayout(new java.awt.GridLayout(3, 9, 5, 5));

        p1r1c1.setBackground(new java.awt.Color(204, 255, 204));
        p1r1c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1r1c1ActionPerformed(evt);
            }
        });
        panelPlayer1.add(p1r1c1);

        p1r1c2.setBackground(new java.awt.Color(204, 255, 204));
        p1r1c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1r1c2ActionPerformed(evt);
            }
        });
        panelPlayer1.add(p1r1c2);

        p1r1c3.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r1c3);

        p1r1c4.setBackground(new java.awt.Color(204, 255, 204));
        p1r1c4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1r1c4ActionPerformed(evt);
            }
        });
        panelPlayer1.add(p1r1c4);

        p1r1c5.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r1c5);

        p1r1c6.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r1c6);

        p1r1c7.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r1c7);

        p1r1c8.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r1c8);

        p1r1c9.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r1c9);

        p1r2c1.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c1);

        p1r2c2.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c2);

        p1r2c3.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c3);

        p1r2c4.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c4);

        p1r2c5.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c5);

        p1r2c6.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c6);

        p1r2c7.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c7);

        p1r2c8.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c8);

        p1r2c9.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r2c9);

        p1r3c1.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c1);

        p1r3c2.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c2);

        p1r3c3.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c3);

        p1r3c4.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c4);

        p1r3c5.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c5);

        p1r3c6.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c6);

        p1r3c7.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c7);

        p1r3c8.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c8);

        p1r3c9.setBackground(new java.awt.Color(204, 255, 204));
        panelPlayer1.add(p1r3c9);

        panelBackground.add(panelPlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 700, 180));

        panelPlayer1Background.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelPlayer1BackgroundLayout = new javax.swing.GroupLayout(panelPlayer1Background);
        panelPlayer1Background.setLayout(panelPlayer1BackgroundLayout);
        panelPlayer1BackgroundLayout.setHorizontalGroup(
            panelPlayer1BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        panelPlayer1BackgroundLayout.setVerticalGroup(
            panelPlayer1BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        panelBackground.add(panelPlayer1Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        panelPlayer2Background.setBackground(new java.awt.Color(51, 0, 102));

        panelPlayer2.setBackground(new java.awt.Color(153, 153, 255));
        panelPlayer2.setLayout(new java.awt.GridLayout(3, 9, 5, 5));

        p2r1c1.setBackground(new java.awt.Color(204, 204, 255));
        p2r1c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p2r1c1ActionPerformed(evt);
            }
        });
        panelPlayer2.add(p2r1c1);

        p2r1c2.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c2);

        p2r1c3.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c3);

        p2r1c4.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c4);

        p2r1c5.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c5);

        p2r1c6.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c6);

        p2r1c7.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c7);

        p2r1c8.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c8);

        p2r1c9.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r1c9);

        p2r2c1.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c1);

        p2r2c2.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c2);

        p2r2c3.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c3);

        p2r2c4.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c4);

        p2r2c5.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c5);

        p2r2c6.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c6);

        p2r2c7.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c7);

        p2r2c8.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c8);

        p2r2c9.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r2c9);

        p2r3c1.setBackground(new java.awt.Color(204, 204, 255));
        p2r3c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p2r3c1ActionPerformed(evt);
            }
        });
        panelPlayer2.add(p2r3c1);

        p2r3c2.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c2);

        p2r3c3.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c3);

        p2r3c4.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c4);

        p2r3c5.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c5);

        p2r3c6.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c6);

        p2r3c7.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c7);

        p2r3c8.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c8);

        p2r3c9.setBackground(new java.awt.Color(204, 204, 255));
        panelPlayer2.add(p2r3c9);

        javax.swing.GroupLayout panelPlayer2BackgroundLayout = new javax.swing.GroupLayout(panelPlayer2Background);
        panelPlayer2Background.setLayout(panelPlayer2BackgroundLayout);
        panelPlayer2BackgroundLayout.setHorizontalGroup(
            panelPlayer2BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayer2BackgroundLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panelPlayer2BackgroundLayout.setVerticalGroup(
            panelPlayer2BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayer2BackgroundLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelBackground.add(panelPlayer2Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        getContentPane().add(panelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 610));

        getAccessibleContext().setAccessibleName("BingoGame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void p1r1c4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1r1c4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p1r1c4ActionPerformed

    private void p2r3c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p2r3c1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p2r3c1ActionPerformed

    private void p2r1c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p2r1c1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_p2r1c1ActionPerformed

    private void p1r1c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1r1c2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p1r1c2ActionPerformed

    private void p1r1c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1r1c1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p1r1c1ActionPerformed

    private void btnEditNumP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNumP1ActionPerformed
        // TODO add your handling code here:
        markNumber();
        checkBingo();
    }//GEN-LAST:event_btnEditNumP1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainGamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGamePanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditNumP1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JCheckBox p1checkbox1st;
    private javax.swing.JCheckBox p1checkbox2nd;
    private javax.swing.JCheckBox p1checkbox3rd;
    private static javax.swing.JButton p1r1c1;
    private static javax.swing.JButton p1r1c2;
    private static javax.swing.JButton p1r1c3;
    private static javax.swing.JButton p1r1c4;
    private static javax.swing.JButton p1r1c5;
    private static javax.swing.JButton p1r1c6;
    private static javax.swing.JButton p1r1c7;
    private static javax.swing.JButton p1r1c8;
    private static javax.swing.JButton p1r1c9;
    private static javax.swing.JButton p1r2c1;
    private static javax.swing.JButton p1r2c2;
    private static javax.swing.JButton p1r2c3;
    private static javax.swing.JButton p1r2c4;
    private static javax.swing.JButton p1r2c5;
    private static javax.swing.JButton p1r2c6;
    private static javax.swing.JButton p1r2c7;
    private static javax.swing.JButton p1r2c8;
    private static javax.swing.JButton p1r2c9;
    private static javax.swing.JButton p1r3c1;
    private static javax.swing.JButton p1r3c2;
    private static javax.swing.JButton p1r3c3;
    private static javax.swing.JButton p1r3c4;
    private static javax.swing.JButton p1r3c5;
    private static javax.swing.JButton p1r3c6;
    private static javax.swing.JButton p1r3c7;
    private static javax.swing.JButton p1r3c8;
    private static javax.swing.JButton p1r3c9;
    private javax.swing.JCheckBox p2checkbox1st;
    private javax.swing.JCheckBox p2checkbox2nd;
    private javax.swing.JCheckBox p2checkbox3rd;
    private static javax.swing.JButton p2r1c1;
    private static javax.swing.JButton p2r1c2;
    private static javax.swing.JButton p2r1c3;
    private static javax.swing.JButton p2r1c4;
    private static javax.swing.JButton p2r1c5;
    private static javax.swing.JButton p2r1c6;
    private static javax.swing.JButton p2r1c7;
    private static javax.swing.JButton p2r1c8;
    private static javax.swing.JButton p2r1c9;
    private static javax.swing.JButton p2r2c1;
    private static javax.swing.JButton p2r2c2;
    private static javax.swing.JButton p2r2c3;
    private static javax.swing.JButton p2r2c4;
    private static javax.swing.JButton p2r2c5;
    private static javax.swing.JButton p2r2c6;
    private static javax.swing.JButton p2r2c7;
    private static javax.swing.JButton p2r2c8;
    private static javax.swing.JButton p2r2c9;
    private static javax.swing.JButton p2r3c1;
    private static javax.swing.JButton p2r3c2;
    private static javax.swing.JButton p2r3c3;
    private static javax.swing.JButton p2r3c4;
    private static javax.swing.JButton p2r3c5;
    private static javax.swing.JButton p2r3c6;
    private static javax.swing.JButton p2r3c7;
    private static javax.swing.JButton p2r3c8;
    private static javax.swing.JButton p2r3c9;
    private static javax.swing.JPanel panelBackground;
    private javax.swing.JPanel panelPlayer1;
    private javax.swing.JPanel panelPlayer1Background;
    private javax.swing.JPanel panelPlayer2;
    private javax.swing.JPanel panelPlayer2Background;
    private static javax.swing.JTextPane txtNumPanel;
    // End of variables declaration//GEN-END:variables
}
