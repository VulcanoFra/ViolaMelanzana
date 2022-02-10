package application.persistenza.dao.jdbc;

 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

 

public class IdBroker {

 

    public static int getId(Connection conn, String nome) throws SQLException {
                
        String query = "select nextval(?) as id";
        
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, nome);
        ResultSet rs = st.executeQuery();
        rs.next();
        return rs.getInt("id");
    }
}