/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmcoe.dbms;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nachiket
 */
public class PopUP_Table extends javax.swing.JPanel {
    ArrayList<Student> studentsList_pop;
    /**
     * Creates new form PopUP
     */
    public PopUP_Table(ArrayList<Student> studentsList, int choice) {
        studentsList_pop = studentsList;
        initComponents();
        show_students_in_JTable_new();
       this.setVisible(true);
    }
    
        public void show_students_in_JTable_new() {
        ArrayList<Student> list = studentsList_pop;
        DefaultTableModel model= (DefaultTableModel) jTable1_display_students_new.getModel();
        Object[] row = new Object[6];
        for(int i =0; i<list.size(); i++) {
         row[0] = list.get(i).getID();
         row[1] = list.get(i).getAge();
         row[2] = list.get(i).getSection();
         row[3] = list.get(i).getGpa();
         row[4] = list.get(i).getName();
         row[5] = list.get(i).getClas();
         
         model.addRow(row);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1_display_students_new = new javax.swing.JTable();
        jButton1_OK = new javax.swing.JButton();

        jTable1_display_students_new.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Age", "Section", "GPA", "Name", "Class"
            }
        ));
        jScrollPane1.setViewportView(jTable1_display_students_new);

        jButton1_OK.setText("OK");
        jButton1_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_OKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jButton1_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1_OK)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_OKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1_OKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1_OK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1_display_students_new;
    // End of variables declaration//GEN-END:variables

}
