package ua.ldubgd.animalshelter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.ldubgd.animalshelter.keyboards.InlineKeyboard;
import ua.ldubgd.animalshelter.keyboards.ReplyKeyboard;
import ua.ldubgd.animalshelter.service.Animal;
import ua.ldubgd.animalshelter.service.Response;

@Component
public class AnimalShelterBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Shelter08Bot";
    }

    @Override
    public String getBotToken() {
        return "7187563813:AAHzbtWxMiLM2l4Xi3wpycpcp_AxJjuEfRE";
    }

    //ін'єкція залежностей
    private final ReplyKeyboard replyKeyboard;
    private final Response response;
    private final Animal animal;
    private final InlineKeyboard inlineKeyboard;
    @Autowired
    public AnimalShelterBot(ReplyKeyboard replyKeyboard, Response response, Animal animal, InlineKeyboard inlineKeyboard) {
        this.replyKeyboard = replyKeyboard;
        this.response = response;
        this.animal = animal;
        this.inlineKeyboard = inlineKeyboard;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            //отримання повідомлення
            Message message = update.getMessage();
            //виведення повідомлення в консоль
            System.out.println(message.getChatId() + " " + message.getText());
            //надсилання відповіді текстом
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setParseMode(ParseMode.HTML);

            String getText = message.getText();
            // реєстрація або пошук тварини
            if (animal.isRegisterMessage() || animal.isSearchMessage() || animal.isDeleteMessage()) registrySearch(sendMessage,getText);

            switch (getText){
                case "/start":
                    sendMessage.setText(response.getStartMessage());
                    sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                    //Автоматичне заповнення оперативної пам'яті записами про тварин
                    animal.fillAnimals();
                    break;
                case "Реєстрація":
                    sendMessage.setText(response.getRegisterAnimal());
                    //у наступному повідомленні очікую отримати дані для реєстрації
                    animal.setRegisterMessage(true);
                    animal.setSearchMessage(false);
                    animal.setDeleteMessage(false);
                    break;
                case "Пошук":
                    sendMessage.setText(response.getSearch());
                    //у наступному повідомленні очікую отримати клавіатуру в тексті
                    sendMessage.setReplyMarkup(inlineKeyboard.getInlineSearchKeyboard());
                    animal.setSearchMessage(false);
                    animal.setRegisterMessage(false);
                    animal.setDeleteMessage(false);
                    break;
                case "Видалити":
                    sendMessage.setText(response.getDelete());
                    //у наступному повідомленні очікую отримати дані для видалення
                    animal.setDeleteMessage(true);
                    animal.setSearchMessage(false);
                    animal.setRegisterMessage(false);
                    break;
            }
            try {
                execute(sendMessage); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message message = callbackQuery.getMessage();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            switch (callbackQuery.getData()){
                case "порядковий номер":
                case "вид":
                case "ім'я":
                case "стать":
                case "вік":
                case "порода":
                case "колір":
                    sendMessage.setText("Надішліть " + callbackQuery.getData());
                    animal.setSearchBy(callbackQuery.getData());
                    animal.setSearchMessage(true);
                    try {
                        execute(sendMessage); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "знайти всіх":
                    for (int j = 0; j < animal.animals.size(); j++) {
                        sendMessage.setText(response.getResponseSearch(j));
                        try {
                            execute(sendMessage); // Call method to send the message
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }
    private void registrySearch(SendMessage sendMessage, String getText){
        if (animal.isRegisterMessage()){
            String[] paragraphs = getText.split("\n");
            //якщо введено 10 рядків з абзацу
            if (paragraphs.length == 10){
                //зареєструвати тварину
                Animal newAnimal = new Animal(paragraphs[0],paragraphs[1],paragraphs[2],paragraphs[3],paragraphs[4] + " роки(ів)",
                        paragraphs[5],paragraphs[6],paragraphs[7],paragraphs[8],paragraphs[9]);
                //перевірити чи вже існує запис з таким порядковим номером
                for (int i = 0; i<animal.animals.size();i++){
                    if (!animal.animals.get(i).getId().equalsIgnoreCase(paragraphs[0])){
                        animal.animals.add(newAnimal);
                        sendMessage.setText(response.getRegistered());
                        sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                        animal.setRegisterMessage(false);
                        return;
                    }else {
                        sendMessage.setText(response.getIdExists());
                        sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                        return;
                    }
                }
            }else {
                sendMessage.setText(response.getRegisterAnimal());
            }
        }else if (animal.isSearchMessage()) {
            for (int i = 0; i < animal.animals.size(); i++) {
                Animal currentAnimal = animal.animals.get(i);
                switch (animal.getSearchBy()){
                    case "порядковий номер":
                        if (currentAnimal.getId().equalsIgnoreCase(getText)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                    case "ім'я":
                        if (currentAnimal.getName().equalsIgnoreCase(getText)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                    case "вид":
                        if (currentAnimal.getType().equalsIgnoreCase(getText)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                    case "порода":
                        if (currentAnimal.getBreed().equalsIgnoreCase(getText)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                    case "колір":
                        if (currentAnimal.getColor().equalsIgnoreCase(getText)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                    case "вік":
                        String s = getText + " роки(ів)";
                        if (currentAnimal.getAge().equalsIgnoreCase(s)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                    case "стать":
                        if (currentAnimal.getSex().equalsIgnoreCase(getText)){
                            //зберігаємо індекст елемента, з таким самим параметром
                            animal.matchingIndexes.add(i);
                            break;
                        }
                }

            }
            //якщо індекс збережено
            if (!animal.matchingIndexes.isEmpty()) {
                String text = "";
                //пройтись по всім записам
                for (int j = 0; j < animal.matchingIndexes.size(); j++) {
                    int index = animal.matchingIndexes.get(j); // Отримуємо індекс зі списку matchingIndexes
                    //отримати дані про тварину
                    text += response.getResponseSearch(index) + " \n\n";
                }
                sendMessage.setText(text);
                sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                animal.setSearchMessage(false);
                animal.matchingIndexes.clear();
            }else {
                sendMessage.setText(response.getNotFound());
                sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                animal.setSearchMessage(false);
            }
        } else if (animal.isDeleteMessage()) {
            int j = 0;
            //пройтись по всім тваринам та перевірити співпадіння порядкового номера
            for (int i=0; i<animal.animals.size();i++){
                if (animal.animals.get(i).getId().equalsIgnoreCase(getText)){
                    j=i+1;
                }
            }
            if (j==0){
                //порядкового номера не існує
                sendMessage.setText(response.getNotExists());
                sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                animal.setDeleteMessage(false);
            }else {
                //видалити запис про тварину
                animal.animals.remove(j-1);
                sendMessage.setText(response.getDeletedSuccessfully());
                sendMessage.setReplyMarkup(replyKeyboard.getMenuKeyboard());
                animal.setDeleteMessage(false);
            }
        }
    }
}
