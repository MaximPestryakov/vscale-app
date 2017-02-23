package me.maximpestryakov.vscaleapp.main;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Server;

public interface MainView {

    class State {
        public static final int SHOW_TOKEN_WARNING = 0;
        public static final int SHOW_SERVER_LIST = 1;
    }

    void showServerList(List<Server> servers);

    void showTokenWarning(String titile, String message);

}
