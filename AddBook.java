import javax.swing.*;
import java.awt.*;
import java.sql.*;

class AddBook extends JFrame {
JTextField tfT, tfA, tfQ; JButton b;
AddBook(){
setTitle("Add Book"); setSize(360,200); setLayout(new GridLayout(4,2,8,8));
add(new JLabel("Title")); tfT=new JTextField(); add(tfT);
add(new JLabel("Author")); tfA=new JTextField(); add(tfA);
add(new JLabel("Qty")); tfQ=new JTextField("1"); add(tfQ);
b=new JButton("Save"); b.addActionListener(e->save()); add(new JLabel()); add(b);
setLocationRelativeTo(null); setVisible(true);
}
void save(){
try(PreparedStatement ps=DB.get().prepareStatement("INSERT INTO books(title,author,qty) VALUES(?,?,?)")){
ps.setString(1, tfT.getText().trim());
ps.setString(2, tfA.getText().trim());
ps.setInt(3, Integer.parseInt(tfQ.getText().trim()));
ps.executeUpdate(); JOptionPane.showMessageDialog(this,"Saved"); tfT.setText(""); tfA.setText(""); tfQ.setText("1");
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}