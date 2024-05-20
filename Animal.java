package ua.ldubgd.animalshelter.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component // автоматичне створення екземпляру класу
public class Animal {
    private String id;
    private String name;
    private String type;
    private String breed;
    private String age;
    private String sex;
    private String color;
    private String dateArrive;
    private String vaccinated;
    private String stateOfHealth;

    public ArrayList<Animal> animals = new ArrayList<>();
    public ArrayList<Integer> matchingIndexes = new ArrayList<>();// Список для збереження індексів елементів, які відповідають критерію

    private boolean registerMessage = false;
    private boolean searchMessage = false;
    private boolean deleteMessage = false;
    private String searchBy;

    private void addAnimal(Animal animal){
        animals.add(animal);
    }
    public void fillAnimals(){
        addAnimal(new Animal("1", "Белла", "Кіт", "Висловухий", "2 роки(ів)","Дівчинка", "Сірий", "24/02/2024","30/02/2024","Боїться гучних звуків"));
        addAnimal(new Animal("2", "Рекс", "Собака", "Лабрадор", "4 роки(ів)","Хлопчик", "Чорний", "15/01/2019","15/01/2020","Здоровий"));
        addAnimal(new Animal("3", "Мурзик", "Кіт", "Домашній", "3 роки(ів)","Хлопчик", "Рудий", "10/07/2020","10/07/2021","Здоровий"));
        addAnimal(new Animal("4", "Джек", "Пес", "Джек Рассел Тер'єр", "5 роки(ів)","Хлопчик", "Білий з чорними плямами", "05/03/2017","05/03/2018","Потребує лікування"));
        addAnimal(new Animal("5", "Міа", "Кішка", "Перс", "1 роки(ів)","Дівчинка", "Білий", "12/09/2022","12/09/2023","Здорова"));
        addAnimal(new Animal("6", "Барсик", "Кіт", "Сіамський", "2 роки(ів)","Хлопчик", "Коричневий", "20/11/2021","20/11/2022","Здоровий"));
        addAnimal(new Animal("7", "Макс", "Собака", "Німецька вівчарка", "3 роки(ів)","Хлопчик", "Чорно-білий", "08/04/2018","08/04/2019","Здоровий"));
        addAnimal(new Animal("8", "Сімба", "Кіт", "Мейн-кун", "4 роки(ів)","Хлопчик", "Плямистий", "14/06/2019","14/06/2020","Здоровий"));
        addAnimal(new Animal("9", "Лея", "Собака", "Бішон Фрізе", "1 роки(ів)","Дівчинка", "Білий", "30/10/2023","30/10/2024","Здорова"));
        addAnimal(new Animal("10", "Лука", "Кіт", "Британська короткошерста", "3 роки(ів)","Хлопчик", "Сірий з чорними смугами", "02/08/2020","02/08/2021","Здоровий"));
    }

    public Animal() {
    }
    //перевизначений конструктор, принцип поліморфізму
    public Animal(String id, String name, String type, String breed, String age, String sex, String color,
                  String dateArrive, String vaccinated, String stateOfHealth) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.dateArrive = dateArrive;
        this.vaccinated = vaccinated;
        this.stateOfHealth = stateOfHealth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(String vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getStateOfHealth() {
        return stateOfHealth;
    }

    public void setStateOfHealth(String stateOfHealth) {
        this.stateOfHealth = stateOfHealth;
    }

    public boolean isRegisterMessage() {
        return registerMessage;
    }

    public void setRegisterMessage(boolean expectedMessage) {
        this.registerMessage = expectedMessage;
    }

    public boolean isSearchMessage() {
        return searchMessage;
    }

    public void setSearchMessage(boolean searchMessage) {
        this.searchMessage = searchMessage;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public boolean isDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(boolean deleteMessage) {
        this.deleteMessage = deleteMessage;
    }
}
