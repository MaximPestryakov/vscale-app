package me.maximpestryakov.vscaleapp.tokenlist;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Token;

public interface TokenListView extends MvpView {

    void setTokens(List<Token> tokens);

    void showAddForm();

    void hideAddForm();
}
