package me.maximpestryakov.vscaleapp.main;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Server;

interface MainView {

    void showServerList(List<Server> servers);

    void showTokenWarning(String titile, String message);

    void showLoading();

    class State {
        static final int SHOW_TOKEN_WARNING = 0;
        static final int SHOW_SERVER_LIST = 1;
        static final int SHOW_LOADING = 2;

        private State() {
        }
    }
}
