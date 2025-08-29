import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.*;

class IssueBook extends JFrame {
JTextField tfM, tfB, tfDays; JButton b;
final int finePerDay = 5; // simple flat fine rule
IssueBook(){
setTitle("Issue Book"); setSize(360,200); setLayout(new GridLayout(4,2,8,8));
add(new JLabel("Member ID")); tfM=new JTextField(); add(tfM);
add(new JLabel("Book ID")); tfB=new JTextField(); add(tfB);
add(new JLabel("Days")); tfDays=new JTextField("7"); add(tfDays);
b=new JButton("Issue"); b.addActionListener(e->issue()); add(new JLabel()); add(b);
setLocationRelativeTo(null); setVisible(true);
}
void issue(){
try{
int mid=Integer.parseInt(tfM.getText().trim());
int bid=Integer.parseInt(tfB.getText().trim());
int days=Integer.parseInt(tfDays.getText().trim());
Connection c=DB.get();
// check qty
int qty=0; try(PreparedStatement ps=c.prepareStatement("SELECT qty FROM books WHERE id=?")){
ps.setInt(1,bid); try(ResultSet rs=ps.executeQuery()){ if(rs.next()) qty=rs.getInt(1); }
}
if(qty<=0){ JOptionPane.showMessageDialog(this,"No stock for this book"); return; }
LocalDate now=LocalDate.now(); LocalDate due=now.plusDays(days);
try(PreparedStatement ps=c.prepareStatement("INSERT INTO txn(mem_id,book_id,issue_dt,due_dt,status) VALUES(?,?,?,?,?)")){
ps.setInt(1,mid); ps.setInt(2,bid); ps.setDate(3, Util.toSql(now)); ps.setDate(4, Util.toSql(due)); ps.setString(5,"ISSUED");
ps.executeUpdate();
}
try(PreparedStatement ps=c.prepareStatement("UPDATE books SET qty=qty-1 WHERE id=?")){
ps.setInt(1,bid); ps.executeUpdate();
}
JOptionPane.showMessageDialog(this,"Issued. Due: "+due);
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}
