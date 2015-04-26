package dwa.a1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrivateMessage {
    private int id;
    private String sender;
    private String receiver;
    private String message;


    public PrivateMessage (ResultSet rs) throws SQLException
    {
        this.id = rs.getInt("id");
        this.sender = rs.getString("sender");
        this.receiver = rs.getString("receiver");
        this.message = rs.getString("message");
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
