import javax.swing.*;
import java.awt.*;
import java.sql.*;

class SearchBook extends JFrame {
JTextField tfQ; JTable tbl;
SearchBook(){
setTitle("Search"); setSize(640,320); setLayout(new BorderLayout());
JPanel top=new JPanel(new BorderLayout(8,8));
tfQ=new JTextField(); JButton b=new JButton("Go"); b.addActionListener(e->go());
top.add(new JLabel("Title/Author:"), BorderLayout.WEST); top.add(tfQ, BorderLayout.CENTER); top.add(b, BorderLayout.EAST);
add(top, BorderLayout.NORTH);
tbl=new JTable(); add(new JScrollPane(tbl), BorderLayout.CENTER);
setLocationRelativeTo(null); setVisible(true);
}
void go(){
String q = "%"+tfQ.getText().trim()+"%";
String sql = "SELECT b.id,b.title,b.author,b.qty, (SELECT COUNT(*) FROM txn t WHERE t.book_id=b.id AND t.status='ISSUED') AS out_now FROM books b WHERE b.title LIKE ? OR b.author LIKE ? ORDER BY b.title";
try(PreparedStatement ps=DB.get().prepareStatement(sql)){
ps.setString(1,q); ps.setString(2,q);
try(ResultSet rs=ps.executeQuery()){ tbl.setModel(ViewBooks.build(rs)); }
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}
