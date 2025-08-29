import javax.swing.*;
import java.awt.*;
import java.sql.*;

class ViewMembers extends JFrame {
JTable tbl;
ViewMembers(){
setTitle("Members"); setSize(600,300);
tbl=new JTable(); add(new JScrollPane(tbl), BorderLayout.CENTER);
load(); setLocationRelativeTo(null); setVisible(true);
}
void load(){
try(Statement st=DB.get().createStatement(); ResultSet rs=st.executeQuery("SELECT id,name,phone,email FROM members ORDER BY id DESC")){
tbl.setModel(ViewBooks.build(rs));
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}