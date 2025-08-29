import javax.swing.*;
import java.awt.*;
import java.sql.*;

class AddMember extends JFrame {
JTextField tfN, tfP, tfE; JButton b;
AddMember(){
setTitle("Add Member"); setSize(360,200); setLayout(new GridLayout(4,2,8,8));
add(new JLabel("Name")); tfN=new JTextField(); add(tfN);
add(new JLabel("Phone")); tfP=new JTextField(); add(tfP);
add(new JLabel("Email")); tfE=new JTextField(); add(tfE);
b=new JButton("Save"); b.addActionListener(e->save()); add(new JLabel()); add(b);
setLocationRelativeTo(null); setVisible(true);
}
void save(){
try(PreparedStatement ps=DB.get().prepareStatement("INSERT INTO members(name,phone,email) VALUES(?,?,?)")){
ps.setString(1, tfN.getText().trim());
ps.setString(2, tfP.getText().trim());
ps.setString(3, tfE.getText().trim());
ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Saved"); tfN.setText(""); tfP.setText(""); tfE.setText("");
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}