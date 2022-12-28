package org.example.DAO;

import org.example.Model.User.User;
import org.example.Model.User.UserDTO;
import org.example.Model.User.UserType;

import java.util.*;

public class UserService implements UserDAO {

    private final SQLQuery requester = new SQLQuery();

    private final ExecuteAround<User> extractor = rs -> {
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            final User user = new User(rs.getString("name_surname"),rs.getString("email"),
                    rs.getString("login"), rs.getString("password"),
                    UserType.valueOf(rs.getString("role")));
            user.setPersonId(rs.getInt("person_id"));
            list.add(user);
        }
        return list;
    };

    private final ExecuteAround<UserDTO> extractorDTO = rs -> {
        List<UserDTO> list = new ArrayList<>();
        while (rs.next()) {
            final UserDTO user = new UserDTO(rs.getString("name_surname"),rs.getString("email"),
                    rs.getString("login"), UserType.valueOf(rs.getString("role")));
            user.setPersonId(rs.getInt("person_id"));
            list.add(user);
        }
        return list;
    };

    @Override
    public UserDTO getByLogin(String login) {
        String query = "SELECT * FROM person WHERE login = ? RETURNING person_id, name_surname, email, login, role";
        StatementConsumer consumer = statement -> statement.setString(1, login);
        List<UserDTO> users = requester.executeQuery(query, consumer, extractorDTO);
        return users.size() == 1 ? users.get(0) : null;
    }

    @Override
    public void delete(UserDTO user) {
        String query = "DELETE FROM person WHERE login = ?";
        StatementConsumer consumer = statement -> statement.setString(1, user.getLogin());
        requester.executeUpdate(query,consumer);
    }


    @Override
    public UserDTO get(int personId) {
        String query = "SELECT * FROM person WHERE person_id = ? RETURNING person_id, name_surname, email, login, role";
        StatementConsumer consumer = statement -> statement.setInt(1,personId);
        List<UserDTO> users = requester.executeQuery(query, consumer, extractorDTO);
        return users.size() == 1 ? users.get(0) : null;
    }

    @Override
    public List<UserDTO> getAll() {
        String query = "SELECT * FROM person RETURNING person_id, name_surname, email, login, role";
        return requester.executeQuery(query,null, extractorDTO);
    }

    @Override
    public UserDTO authorization_form(String login, String pswd) {
        String query = "SELECT * FROM person WHERE login = ? AND password = ? RETURNING person_id, name_surname, email, login, role";
        StatementConsumer consumer = statement -> {
            statement.setString(1, login);
            statement.setString(2, pswd);
        };
        List<UserDTO> users = requester.executeQuery(query,consumer,extractorDTO);
        return users.size() == 1 ? users.get(0) : null;
    }

    /**
     * ConvertToDTO не понадобился, т.к. нужно вытащить id из database, поэтому нужна обратная связь с db
     */

    @Override
    public UserDTO create(User user) {
        String query = "INSERT INTO person (name_surname,email,login,password,role) values (?,?,?,?,?::person_role) RETURNING person_id,name_surname,email,login, role";
        StatementConsumer consumer = statement -> {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPswd());
            statement.setString(5, user.getType().toString());
        };
            return requester.executeQuery(query,consumer,extractorDTO).get(0);
        }

    @Override
    public void update(UserDTO user, List<?> params) {
        String query = "UPDATE person SET name_surname = ?, email = ? WHERE  login = ?";
        StatementConsumer consumer = statement -> {
            requester.updateFromList(statement,params);
            statement.setString(params.size()+1,user.getLogin());
        };
            requester.executeUpdate(query,consumer);
    }
}

