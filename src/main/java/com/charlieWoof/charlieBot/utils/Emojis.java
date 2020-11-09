package com.charlieWoof.charlieBot.utils;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;


/**Смайлики
 *
 * @author Sergei Viacheslaev
 */
@AllArgsConstructor
public enum Emojis {
    PAW(EmojiParser.parseToUnicode(":paw_prints:")),
    OK_HAND(EmojiParser.parseToUnicode(":ok_hand:")),
    BLACK_SMALL_SQUARE(EmojiParser.parseToUnicode(":black_small_square:")),
    BLUSH_FACE(EmojiParser.parseToUnicode(":blush:")),
    WINK_FACE(EmojiParser.parseToUnicode(":wink:")),
    DOG(EmojiParser.parseToUnicode(":dog2:")),
    CAT(EmojiParser.parseToUnicode(":cat2:")),
    POODLE(EmojiParser.parseToUnicode(":poodle:"));

    private String emojiName;

    @Override
    public String toString() {
        return emojiName;
    }
}
