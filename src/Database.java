import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
    private Connection conn;
     /**
     * Connect to a sample database
     */
     public Database() {
         connect();
     }

    private void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:rsc/database/dict_hh.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String lookup(String s) throws SQLException {
        // connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT html FROM av WHERE word = \"" + s.trim().toLowerCase() + "\";");
        String html = rs.getString("html");
        rs.close();
        stmt.close();
        // conn.close();
        return html;
    }
    
    public String[] search(String s) throws SQLException {
        //vconnect();
        Statement stmt = conn.createStatement();
        int index = 0;
        ResultSet rs = stmt.executeQuery("SELECT word FROM av WHERE word LIKE \"" + s.trim().toLowerCase() + "%\";");
        String[] data = new String[getSize(s)];
        while (rs.next()) {
            data[index++] = rs.getString("word");
        }
        rs.close();
        stmt.close();
        // conn.close();
        return data;
    }
    
    public void insert(String word) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO av (word, html) VALUES ('" + word + "', '')");
        stmt.close();
    }
    
    public void delete(String s) throws SQLException {
        if (s == null) {
            return;
        }
        Statement stmt = conn.createStatement();
        stmt.executeQuery("DELETE FROM av WHERE word = \"" + s.trim().toLowerCase() + "\";");
        stmt.close();
    }
    
    public void update(String word) throws SQLException {
        java.lang.StringBuilder contentBuilder = new java.lang.StringBuilder();
        try {
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader("rsc/html/" + word + ".html"));
            String s;
            while ((s = in.readLine()) != null) {
                contentBuilder.append(s);
            }
            in.close();
        } catch (IOException e) {
            
        }
        String content = contentBuilder.toString();
        if (content == null) {
            return;
        } else if (content.contains("'")) {
            String[] data = content.split("'");
            content = new String(data[0]);
            for (int i = 1; i < data.length; ++i) {
                content += "''" + data[i];
            }
        }
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE av SET html = ('" + content.trim().toLowerCase() + "') WHERE word = \"" + word + "\";");
        stmt.close();
    }
    
    public int getSize(String s) throws SQLException {
        // connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count_dict FROM av WHERE word LIKE \"" + s.trim().toLowerCase() + "%\";");
        return rs.getInt("count_dict");
    }
    
    public void export(java.io.File file, String word) 
    throws FileNotSupportedException, SQLException {
        if (file.getName().contains(".html")) {
            String[] list = search(word);
            try {
                FileWriter writer = new FileWriter(file);
                for (int i = 0; i < list.length; ++i) {
                    writer.write(lookup(list[i]));
                }
                writer.close();
                
            }
            catch (IOException e) {}
        }  else {
            throw new FileNotSupportedException("Error:\n Support .html files only");
        }
    }
    
    public void close() throws SQLException {
        conn.close();
        System.out.println("Connection to SQLite has been closed.");
    }
}