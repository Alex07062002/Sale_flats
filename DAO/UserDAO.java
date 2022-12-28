package org.example.DAO;

import org.example.Model.User.User;
import org.example.Model.User.UserDTO;

import java.util.List;

public interface UserDAO{

    UserDTO get(int personId);
    List<UserDTO> getAll();

    UserDTO create(User user);

    void update(UserDTO user,List<?> params);

    void delete(UserDTO user);
    UserDTO getByLogin(String param);

    UserDTO authorization_form(String login, String pswd);
}