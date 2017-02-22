package me.maximpestryakov.vscaleapp.main;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Server;

public interface MainView {

    void showServerList(List<Server> servers);

    void showTokenWarning(String titile, String message);

}
