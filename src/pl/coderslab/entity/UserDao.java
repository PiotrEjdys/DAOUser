package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DBUtil;
import pl.coderslab.User;

import java.sql.*;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final  String READ_USER_BY_ID ="SELECT * FROM users WHERE id =?";
    private static final String UPDATE_USER
            ="UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID ="DELETE FROM users WHERE id =?";

    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getUserName());
            preStmt.setString(2, user.getEmail());
            preStmt.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                System.out.println("Inserted ID: " + id);
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(READ_USER_BY_ID);
            preStmt.setInt(1,userId);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }


        }catch (SQLException ex){
         ex.printStackTrace();
        }
        return null;
    }
    public void update(User user) {
        try (Connection conn = DBUtil.getConnection()){
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_USER);
            preStmt.setString(1, user.getUserName());
            preStmt.setString(2,user.getEmail());
            preStmt.setString(3,BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
            preStmt.setInt(4,user.getId());
            preStmt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public void delete(int userId) {
        try(Connection conn = DBUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(DELETE_USER_BY_ID);
            preStmt.setInt(1,userId);
            preStmt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }




    }
