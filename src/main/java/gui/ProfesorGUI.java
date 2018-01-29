package gui;

import javax.swing.*;

public class ProfesorGUI extends JFrame {
    private javax.swing.JButton capturarTweetsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField sentimientosPromedioField;

    private ProfesorInterface mProfesorInterface;

    public interface ProfesorInterface {
        void capturarTweets();
        void mostrarSentimientos(String sentimiento);
    }


    public ProfesorGUI(ProfesorInterface profInterface) {
        mProfesorInterface = profInterface;
        initComponents();

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
    }

    public void showGUI() {
        this.setVisible(true);
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        capturarTweetsButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sentimientosPromedioField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Profesor");

        capturarTweetsButton.setText("Comenzar");
        capturarTweetsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capturarTweetsButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Analizar sentimientos de los estudiantes:");

        jLabel3.setText("Sentimientos promedio de la clase:");

        sentimientosPromedioField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(279, 279, 279)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(capturarTweetsButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel3))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(sentimientosPromedioField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(298, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(capturarTweetsButton)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(sentimientosPromedioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }

    private void capturarTweetsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mProfesorInterface.capturarTweets();
    }

    public JTextField getSentimientosPromedioField() {
        return sentimientosPromedioField;
    }
}
