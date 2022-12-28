package org.example.Model.Console.Menu;


import org.example.DAO.UserService;
import org.example.Model.Contract.Contract;
import org.example.Model.Console.Interfaces.PersonUI;
import org.example.Model.Console.Interfaces.SiteUI;
import org.example.Model.User.User;
import org.example.Model.User.UserDTO;


public class AdminMenu extends AbstractMenu implements Menu{

    public AdminMenu(SiteUI ui, PersonUI perUi, UserDTO user, UserService userService) {
        super(ui,perUi,user,userService);
    }


    public String getInfoAboutCompany() {
        return "Заглушка";
    }

    @Override
    public User updateUser() {
        return null;
    }


    @Override
    public Contract createContract() {
        return null;
    } // TODO  в идеале у админа не должно быть прав на создание контракта


    public void menu_optionals(){
        getUi().messageToUser("Меню для администоров\n выберете пункт меню\n");
        getUi().messageToUser("1 - информация  о компании\n 2 - внести новые данные\n 3 - извлечь полный список\n4 - поиск по параметру\n 5 - удалить\n 6 - выход");
        int condition = getPerUI().answerNumFromUser();
        switch (condition){
            case (1) -> getUi().messageToUser(getInfoAboutCompany());
            case (2) -> {}
            case (3) -> {}
            case (4) -> {}
            case (5) -> {}
            case (6) -> setUser(null);
            default -> getUi().messageToUser("Выбрана несуществующая функция...");
        }
    }


/*
    public void menu_optionals(){
        getSiteUI().messageToUser("Меню для администоров\n выберете пункт меню\n");
        getSiteUI().messageToUser("1 - информация  о компании\n 2 - внести новые данные\n 3 - извлечь полный список\n4 - поиск по параметру\n 5 - удалить\n 6 - выход");
        int condition = getPerUi().answerNumFromUser();
        switch (condition){
            case (1) -> {
                getSiteUI().messageToUser(getInfoAboutCompany());
            }
            case (2) -> {
                ui.messageToUser("1 - создать новую запись\n 2 - обновить данные");
                condition = getPerUi().answerNumFromUser();
                ui.messageToUser("1 - город\n 2 - дом\n 3 - этаж\n 4 - квартира\n 5 - контракт");
                switch (condition){
                    case (1) -> {
                        condition = getPerUi().answerNumFromUser();
                        switch (condition){
                            case (1) -> {
                                ui.messageToUser("Введите название города");
                                String name = perUi.answerFromUser();
                                new CityService().create(new City(name));
                            }
                            case (2) ->{
                                ui.messageToUser("Введите city_id");
                                int city_id = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите адрес дома");
                                String address = getPerUi().answerFromUser();
                                ui.messageToUser("Введите название ЖК");
                                String name = getPerUi().answerFromUser();
                                ui.messageToUser("Введите начала строительства");
                                Date start = getPerUi().answerDateFromUser();
                                ui.messageToUser("Введите планируемое окончание строительства");
                                Date end = getPerUi().answerDateFromUser();
                                ui.messageToUser("Введите дату ввода в эксплуатацию");
                                Date commission = getPerUi().answerDateFromUser();
                                new HouseService().create(new House(city_id,address,name,start,end,commission));
                            }
                            case (3) -> {
                                ui.messageToUser("Введите floor_id");
                                int floor_id = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите house_id");
                                int house_id = getPerUi().answerNumFromUser();
                                new FloorService().create(new Floor(floor_id,house_id));
                            }
                            case (4) -> {
                                ui.messageToUser("Введите floor_id");
                                int floor_id = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите house_id");
                                int house_id = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите число комнат");
                                int number_of_rooms = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите подъезд");
                                int entrance = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите площадь");
                                double square = getPerUi().answerDoubleFromUser();
                                ui.messageToUser("Введите статус владения");
                                String answer = getPerUi().answerFromUser();
                                switch (answer){
                                    case ("free") -> new FlatService().create(new Flat(floor_id,house_id,number_of_rooms,entrance,square,OwnerType.free));
                                    case ("sold") -> new FlatService().create(new Flat(floor_id,house_id,number_of_rooms,entrance,square,OwnerType.sold));
                                    case ("reserved") -> new FlatService().create(new Flat(floor_id,house_id,number_of_rooms,entrance,square,OwnerType.reserved));
                                }
                            }
                            case (5) -> {
                                ui.messageToUser("Введите flat_id");
                                int flat_id = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите person_id");
                                int person_id = getPerUi().answerNumFromUser();
                                ui.messageToUser("Введите способ оплаты");
                                String answer = getPerUi().answerFromUser();
                                switch (answer){
                                    case ("cash") ->  answer = PayType.cash.toString();
                                    case ("cashless") ->  answer = PayType.cashless.toString();
                                }
                                ui.messageToUser("Введите статус контракта");

                            }
                        }
                    }

                }
            }
        }
    }
*/


}
