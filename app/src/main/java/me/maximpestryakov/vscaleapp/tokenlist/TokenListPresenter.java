package me.maximpestryakov.vscaleapp.tokenlist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.realm.Realm;
import me.maximpestryakov.vscaleapp.api.model.Token;

@InjectViewState
public class TokenListPresenter extends MvpPresenter<TokenListView> {

    public TokenListPresenter() {
        loadTokens();
    }

    void loadTokens() {
        Realm realm = Realm.getDefaultInstance();
        List<Token> tokens = realm.copyFromRealm(realm.where(Token.class).findAll());
        realm.close();
        getViewState().setTokens(tokens);
    }

    void onClickShowButton() {
        getViewState().showAddForm();
    }

    void onClickAddButton(String tokenName, String tokenValue) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(new Token(tokenName, tokenValue));
        realm.commitTransaction();
        realm.close();
        onHideAddForm();
        loadTokens();
    }

    void onHideAddForm() {
        getViewState().hideAddForm();
    }
}
