package ua.ldubgd.animalshelter.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Component // автоматичне створення екземпляра класу
public class InlineKeyboard {
    //Створення клавіатури в тексті
    private InlineKeyboardMarkup inlineSearchKeyboard(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Arrays.asList(InlineKeyboardButton.builder().text("Порядковим номером").callbackData("порядковий номер").build(),
                InlineKeyboardButton.builder().text("Ім'ям").callbackData("ім'я").build()));
        keyboard.add(Arrays.asList(InlineKeyboardButton.builder().text("Видом").callbackData("вид").build(),
                InlineKeyboardButton.builder().text("Породою").callbackData("порода").build()));
        keyboard.add(Arrays.asList(InlineKeyboardButton.builder().text("Віком").callbackData("вік").build(),
                InlineKeyboardButton.builder().text("Статтю").callbackData("стать").build()));
        keyboard.add(Arrays.asList(InlineKeyboardButton.builder().text("Кольором").callbackData("колір").build(),
                InlineKeyboardButton.builder().text("Знайти всіх").callbackData("знайти всіх").build()));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
    //метод для доступу до закритого методу, принцип інкапсуляції
    public InlineKeyboardMarkup getInlineSearchKeyboard(){
        return inlineSearchKeyboard();
    }
}
