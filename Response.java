package ua.ldubgd.animalshelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Response {
    private final Animal animal;
    @Autowired
    public Response(Animal animal) {
        this.animal = animal;
    }

    private String startMessage(){
        return "\uD83C\uDDFA\uD83C\uDDE6 Вас вітає система обліку тварин у притулку";
    }
    public String getStartMessage(){
        return startMessage();
    }

    private String registerAnimal(){
        return "\uD83D\uDCDD Надішліть дані тварини в такому форматі:\n\n" +
                "\uD83D\uDD38 Порядковий номер \n" +
                "\uD83D\uDD38 Ім'я \n" +
                "\uD83D\uDD38 Вид \n" +
                "\uD83D\uDD38 Породa\n" +
                "\uD83D\uDD38 Вік\n" +
                "\uD83D\uDD38 Стать\n" +
                "\uD83D\uDD38 Колір\n" +
                "\uD83D\uDD38 Дата прибуття (наприклад 24/02/2024)\n" +
                "\uD83D\uDD38 Дата вакцинації (- якщо не вакцинована)\n" +
                "\uD83D\uDD38 Стан здоров'я";
    }
    public String getRegisterAnimal(){
        return registerAnimal();
    }

    private String search(){
        return "\uD83D\uDCCC За яким критерієм ви хочете здійснити пошук? ";
    }
    public String getSearch(){
        return search();
    }

    private String responseSearch(int j){
        String text = animal.animals.get(j).getId()+ ", " +
                animal.animals.get(j).getName()+ ", " +
                animal.animals.get(j).getType()+ ", " +
                animal.animals.get(j).getBreed()+ ", " +
                animal.animals.get(j).getAge()+ ", " +
                animal.animals.get(j).getSex()+ ", " +
                animal.animals.get(j).getColor()+ ", " +
                animal.animals.get(j).getDateArrive()+ ", " +
                animal.animals.get(j).getVaccinated()+ ", " +
                animal.animals.get(j).getStateOfHealth();
        return text;
    }
    public String getResponseSearch(int j){
        return responseSearch(j);
    }
    private String delete(){
        return "Надішліть порядковий номер тварини, яку бажаєте видалити із системи";
    }
    public String getDelete(){
        return delete();
    }
    private String registered(){
        return "Реєстрацію успішно проведено. Скористайтесь командами чат-бот повторно";
    }
    public String getRegistered(){
        return registered();
    }
    private String idExists(){
        return "Помилка. Такий порядковий номер вже існує. Скористайтесь командами чат-бота повторно";
    }
    public String getIdExists(){
        return idExists();
    }
    private String notFound(){
        return "Відповідностей не знайдено.Скористайтесь командами чат-бот повторно";
    }
    public String getNotFound(){
        return notFound();
    }
    private String notExists(){
        return "Такого порядкового номера не існує. Скористайтесь командами чат-бот повторно";
    }
    public String getNotExists(){
        return notExists();
    }
    private String deletedSuccessfully(){
        return "Успішно видалено. Скористайтесь командами чат-бота повторно.";
    }
    public String getDeletedSuccessfully(){
        return deletedSuccessfully();
    }
}
