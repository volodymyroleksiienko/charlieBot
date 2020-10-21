package com.charlieWoof.charlieBot.botapi;

/**Возможные состояния бота
 */

public enum BotState {
    ASK_DESTINY,
    ASK_NAME,
    ASK_AGE,
    ASK_GENDER,
    ASK_COLOR,
    ASK_NUMBER,
    ASK_MOVIE,
    ASK_SONG,
    SHOW_USER_PROFILE,
    FILLING_PROFILE,
    PROFILE_FILLED,
    SHOW_MAIN_MENU,
    SHOW_HELP_MENU,

    START,
    ASK_PRICE,
    ASK_WEBSITE,
    ASK_COURSES,
    ASK_SIGN_UP,
    SHOW_CATEGORY,
    SHOW_PRODUCTS_BY_CATEGORY,
    SHOW_BUCKET_INFO,
    SHOW_ORDERS;
}
