package com.charlieWoof.charlieBot.cache;

import com.charlieWoof.charlieBot.botapi.BotState;
import com.charlieWoof.charlieBot.botapi.handlers.fillingprofile.UserProfileData;


public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfileData userProfileData);

    UserInfoCache getUserInfoCache(int userId);

    void saveUserInfoCache(int userId, UserInfoCache userInfoCache);
}
