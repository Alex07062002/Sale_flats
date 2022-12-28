package org.example.Model.User;


/**
 * Реализация Double checked Singleton
 * https://habr.com/ru/post/129494/
 * Выбор между static class/ method vs Singleton pattern
 * https://habr.com/ru/post/103681/
 * https://translated.turbopages.org/proxy_u/en-ru.ru.b471db1e-6368f9fb-22f5b7f0-74722d776562/https/stackoverflow.com/questions/7329788/static-methods-or-singleton-which-one-to-choose
 */

public class UserFactory {

    private static volatile UserFactory userFactory;

    private UserFactory(){}

    public static UserFactory getInstance(){
        if (userFactory == null){
            synchronized (UserFactory.class){
                if (userFactory == null){
                    userFactory = new UserFactory();
                }
            }
        }
        return userFactory;
    }

    public User createUser(UserType type, String name, String email, String login, String pswd) throws NullPointerException{ // TODO ReflectionAPI
    User user = null;
    try {
        switch (type) {
            case user -> {
                user = new User(name, email, login, pswd,UserType.user);
            }
            case admin -> {
                user = new User(name, email, login, pswd,UserType.admin);
            }
        }
    }catch (NullPointerException e){
        throw new NullPointerException("User wasn't created");
    }
        return user;
    }

}
