/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmcoe.dbms;
import com.mmcoe.dbms.PopUP_Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nachiket
 */
public class DBMS_GUI extends javax.swing.JFrame {

    /**
     * Creates new form DBMS_GUI
     */
    public DBMS_GUI() {
        
        initComponents();
        show_students_in_JTable(getStudentsList());
    }

    
    public Connection getConnection() {
        Connection con;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "password");
            return con;
        }catch(Exception E) {
            E.printStackTrace();
            return null;
        }
    }
    public ArrayList<Student> getStudentsList() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        Connection connection = getConnection();
        String query = "select * from student";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;

            while(rs.next()) {
                String clas = rs.getString("class");
                int section = rs.getInt("section");
                student = new Student(rs.getInt("id"),rs.getInt("age"),section,rs.getFloat("gpa"),rs.getString("name"),clas);
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }

        public ArrayList<Student> getStudentsList_below() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        Connection connection = getConnection();
        String query = "select * from student where gpa<'"+Float.parseFloat(jTextField_threshold.getText())+"'";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;

            while(rs.next()) {
                String clas = rs.getString("class");
                int section = rs.getInt("section");
                student = new Student(rs.getInt("id"),rs.getInt("age"),section,rs.getFloat("gpa"),rs.getString("name"),clas);
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    public ArrayList<Student> getStudentsList_above() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        Connection connection = getConnection();
        String query = "select * from student where gpa>'"+Float.parseFloat(jTextField_threshold.getText())+"'";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;

            while(rs.next()) {
                String clas = rs.getString("class");
                int section = rs.getInt("section");
                student = new Student(rs.getInt("id"),rs.getInt("age"),section,rs.getFloat("gpa"),rs.getString("name"),clas);
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }
 public ArrayList<Student> getStudentsList_searched() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        Connection connection = getConnection();
        String query = "select * from student where name='"+jTextField2_Name_Search.getText()+"'";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;

            while(rs.next()) {
                String clas = rs.getString("class");
                int section = rs.getInt("section");
                student = new Student(rs.getInt("id"),rs.getInt("age"),section,rs.getFloat("gpa"),rs.getString("name"),clas);
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }
public ArrayList<Student> getStudentsList_max() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        Connection connection = getConnection();
        String query = "select id,name,age,class,section,gpa from student where gpa=(select max(gpa) from student)";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;

            while(rs.next()) {
                String clas = rs.getString("class");
                int section = rs.getInt("section");
                student = new Student(rs.getInt("id"),rs.getInt("age"),section,rs.getFloat("gpa"),rs.getString("name"),clas);
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }
public ArrayList<Student> getStudentsList_min() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        Connection connection = getConnection();
        String query = "select id,name,age,class,section,gpa from student where gpa=(select min(gpa) from student)";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;

            while(rs.next()) {
                String clas = rs.getString("class");
                int section = rs.getInt("section");
                student = new Student(rs.getInt("id"),rs.getInt("age"),section,rs.getFloat("gpa"),rs.getString("name"),clas);
                studentsList.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    public void show_students_in_JTable(ArrayList<Student> list) {
        
        DefaultTableModel model= (DefaultTableModel) jTable1_display_students.getModel();
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
    public void executeSqlQuery(String query, String message) {
        Connection con = getConnection();
        Statement st;
        try {
            st = con.createStatement();
            if(st.executeUpdate(query)==1) {
                DefaultTableModel model = (DefaultTableModel) jTable1_display_students.getModel();
                model.setRowCount(0);
                show_students_in_JTable(getStudentsList());
                JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not "+message);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
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

        jButton1_Exit = new javax.swing.JButton();
        jButton2_Edit = new javax.swing.JButton();
        jButton3_Delete = new javax.swing.JButton();
        jButton4_Insert = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1_ID = new javax.swing.JTextField();
        jTextField2_Name = new javax.swing.JTextField();
        jTextField3_Age = new javax.swing.JTextField();
        jTextField4_gpa = new javax.swing.JTextField();
        jTextField5_Section = new javax.swing.JTextField();
        jTextField6_Class = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1_display_students = new javax.swing.JTable();
        jButton_Search = new javax.swing.JButton();
        jButton2_below = new javax.swing.JButton();
        jTextField_threshold = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton3_above = new javax.swing.JButton();
        jButton_clear = new javax.swing.JButton();
        jTextField2_Name_Search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton_reset = new javax.swing.JButton();
        jButton_max = new javax.swing.JButton();
        jButton_min = new javax.swing.JButton();
        jButton_distinct = new javax.swing.JButton();
        jButton_avg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Data Entry");
        setPreferredSize(new java.awt.Dimension(900, 600));

        jButton1_Exit.setText("Exit");
        jButton1_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_ExitActionPerformed(evt);
            }
        });

        jButton2_Edit.setText("Update");
        jButton2_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_EditActionPerformed(evt);
            }
        });

        jButton3_Delete.setText("Delete");
        jButton3_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3_DeleteActionPerformed(evt);
            }
        });

        jButton4_Insert.setText("Insert");
        jButton4_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4_InsertActionPerformed(evt);
            }
        });

        jLabel1.setText("ID:");

        jLabel2.setText("Name:");

        jLabel3.setText("Age:");

        jLabel4.setText("GPA:");

        jLabel5.setText("Class:");

        jLabel6.setText("Section:");

        jTable1_display_students.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Age", "Section", "GPA", "Name", "Class"
            }
        ));
        jTable1_display_students.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1_display_studentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1_display_students);

        jButton_Search.setText("Search");
        jButton_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });

        jButton2_below.setText("Below Threshold");
        jButton2_below.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_belowActionPerformed(evt);
            }
        });

        jTextField_threshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_thresholdActionPerformed(evt);
            }
        });

        jLabel7.setText("Set Threshold:");

        jButton3_above.setText("Above Threshold");
        jButton3_above.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3_aboveActionPerformed(evt);
            }
        });

        jButton_clear.setText("Clear Threshold");
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearActionPerformed(evt);
            }
        });

        jLabel8.setText("Enter Student Name to Search:");

        jButton_reset.setText("Reset");
        jButton_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resetActionPerformed(evt);
            }
        });

        jButton_max.setText("Get Max GPA");
        jButton_max.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_maxActionPerformed(evt);
            }
        });

        jButton_min.setText("Get Min GPA");
        jButton_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_minActionPerformed(evt);
            }
        });

        jButton_distinct.setText("Distinct");
        jButton_distinct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_distinctActionPerformed(evt);
            }
        });

        jButton_avg.setText("Avg using aggregation");
        jButton_avg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_avgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField2_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5))
                                            .addComponent(jTextField3_Age, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4_gpa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField5_Section, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField6_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton3_Delete)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2_Name_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton4_Insert)
                                                .addGap(33, 33, 33)
                                                .addComponent(jButton2_Edit))
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton_reset)
                                            .addComponent(jButton_Search))
                                        .addGap(29, 29, 29)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField_threshold)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3_above)
                                            .addComponent(jButton2_below))))
                                .addGap(59, 59, 59))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jButton1_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_avg)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_max)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_min)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_distinct)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jTextField1_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6_Class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jTextField2_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5_Section, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3_Age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4_gpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3_Delete)
                            .addComponent(jButton2_Edit)
                            .addComponent(jButton4_Insert))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jButton_Search))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2_Name_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_reset))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton3_above))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2_below)
                            .addComponent(jTextField_threshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1_Exit))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_max)
                            .addComponent(jButton_min)
                            .addComponent(jButton_distinct))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_avg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_ExitActionPerformed
          // TODO add your handling code here:
          System.exit(0);
    }//GEN-LAST:event_jButton1_ExitActionPerformed

    private void jButton2_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_EditActionPerformed
        String query;
        query = "UPDATE student SET name='"+jTextField2_Name.getText()+"',age='"+Integer.parseInt(jTextField3_Age.getText())+"',class='"+jTextField6_Class.getText()+"',section='"+Integer.parseInt(jTextField5_Section.getText())+"',gpa='"+Float.parseFloat(jTextField4_gpa.getText())+"' WHERE id="+Integer.parseInt(jTextField1_ID.getText());
        executeSqlQuery(query, "Updated");
    }//GEN-LAST:event_jButton2_EditActionPerformed

    private void jButton3_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3_DeleteActionPerformed
        String query ="DELETE FROM `student` WHERE id='"+Integer.parseInt(jTextField1_ID.getText())+"'";
        executeSqlQuery(query, "Deleted");
    }//GEN-LAST:event_jButton3_DeleteActionPerformed

    private void jButton4_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4_InsertActionPerformed

        int id = Integer.parseInt(jTextField1_ID.getText());
        int age = Integer.parseInt(jTextField3_Age.getText());
        int section = Integer.parseInt(jTextField5_Section.getText());
        Float gpa = Float.parseFloat(jTextField4_gpa.getText());
        
        String query ="INSERT INTO `student` (`id`, `name`, `age`, `class`, `section`, `gpa`) VALUES ('"+id+"','"+jTextField2_Name.getText()+"','"+age+"','"+jTextField6_Class.getText()+"','"+section+"','"+gpa+"')";
        executeSqlQuery(query, "Inserted");
    }//GEN-LAST:event_jButton4_InsertActionPerformed

    private void jTable1_display_studentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1_display_studentsMouseClicked
        int i = jTable1_display_students.getSelectedRow();
        TableModel model = jTable1_display_students.getModel();
        jTextField1_ID.setText(model.getValueAt(i,0).toString());
        jTextField3_Age.setText(model.getValueAt(i,1).toString());
        jTextField5_Section.setText(model.getValueAt(i,2).toString());
        jTextField4_gpa.setText(model.getValueAt(i,3).toString());
        jTextField2_Name.setText(model.getValueAt(i,4).toString());
        jTextField6_Class.setText(model.getValueAt(i,5).toString());
    }//GEN-LAST:event_jTable1_display_studentsMouseClicked

    private void jTextField_thresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_thresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_thresholdActionPerformed

    private void jButton3_aboveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3_aboveActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList_above());        
    }//GEN-LAST:event_jButton3_aboveActionPerformed

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList());
        jTextField_threshold.setText("");
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jButton2_belowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_belowActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList_below()); 
    }//GEN-LAST:event_jButton2_belowActionPerformed

    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SearchActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList_searched());
    }//GEN-LAST:event_jButton_SearchActionPerformed

    private void jButton_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resetActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList());
        jTextField2_Name_Search.setText("");
    }//GEN-LAST:event_jButton_resetActionPerformed

    private void jButton_maxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_maxActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList_max());
    }//GEN-LAST:event_jButton_maxActionPerformed

    private void jButton_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_minActionPerformed
        DefaultTableModel mod = (DefaultTableModel) jTable1_display_students.getModel();
        mod.setRowCount(0);
        show_students_in_JTable(getStudentsList_min());
    }//GEN-LAST:event_jButton_minActionPerformed

    private void jButton_distinctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_distinctActionPerformed
        Connection connection = getConnection();
        String query = "select distinct class from student";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Student student;
            String[] sa = new String[20];
            int cnt =0;
            while(rs.next()) {
                String clas = rs.getString("class");
                 sa[cnt]=clas; 
                 cnt++;
            }
            StringBuffer sb = new StringBuffer();
            for(int l=0;l<cnt;l++) {
                sb.append(sa[l]);
                sb.append("\n");
            }
            JOptionPane.showMessageDialog(null,sb.toString());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_distinctActionPerformed

    private void jButton_avgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_avgActionPerformed
        Connection connection = getConnection();
        String query = "select avg(gpa) from student";
        Statement st;
        ResultSet rs;
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            float avg = 0;
            int cnt =0;
            while(rs.next()) {
                avg = rs.getFloat("avg(gpa)");
            }
            JOptionPane.showMessageDialog(null,"Average GPA: "+avg);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton_avgActionPerformed

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
            java.util.logging.Logger.getLogger(DBMS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DBMS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DBMS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DBMS_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DBMS_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1_Exit;
    private javax.swing.JButton jButton2_Edit;
    private javax.swing.JButton jButton2_below;
    private javax.swing.JButton jButton3_Delete;
    private javax.swing.JButton jButton3_above;
    private javax.swing.JButton jButton4_Insert;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JButton jButton_avg;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_distinct;
    private javax.swing.JButton jButton_max;
    private javax.swing.JButton jButton_min;
    private javax.swing.JButton jButton_reset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1_display_students;
    private javax.swing.JTextField jTextField1_ID;
    private javax.swing.JTextField jTextField2_Name;
    private javax.swing.JTextField jTextField2_Name_Search;
    private javax.swing.JTextField jTextField3_Age;
    private javax.swing.JTextField jTextField4_gpa;
    private javax.swing.JTextField jTextField5_Section;
    private javax.swing.JTextField jTextField6_Class;
    private javax.swing.JTextField jTextField_threshold;
    // End of variables declaration//GEN-END:variables
}
