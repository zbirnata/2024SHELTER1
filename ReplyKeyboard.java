package ua.ldubgd.animalshelter.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
@Component // автоматичне створення екземпляру класу
public class ReplyKeyboard {
    private ReplyKeyboardMarkup menuKeyboard(){
        //створюємо клавіатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        //створюємо список рядків клавіатури
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        //створюємо перший рядок
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Реєстрація");
        keyboardFirstRow.add("Пошук");
        //створюємо другий рядок
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Видалити");
        //додаємо всі рядки в наш список
        keyboardRows.add(keyboardFirstRow);
        keyboardRows.add(keyboardSecondRow);
        //встановлюємо цей список нашій клавіатурі
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
    //метод для доступу до закритого методу, принцип інкапсуляції
    public ReplyKeyboardMarkup getMenuKeyboard(){
        return menuKeyboard();
    }

}
