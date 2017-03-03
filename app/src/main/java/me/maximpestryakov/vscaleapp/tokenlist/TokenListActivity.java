package me.maximpestryakov.vscaleapp.tokenlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Token;


public class TokenListActivity extends MvpAppCompatActivity implements TokenListView {

    @InjectPresenter
    TokenListPresenter presenter;

    @BindView(R.id.tokenList)
    RecyclerView tokenList;

    @BindView(R.id.addToken)
    FloatingActionButton addToken;

    private TokenListAdapter tokenListAdapter;

    private AlertDialog addForm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_list);

        ButterKnife.bind(this);


        tokenListAdapter = new TokenListAdapter();

        tokenList.setHasFixedSize(true);
        tokenList.setLayoutManager(new LinearLayoutManager(this));
        tokenList.setAdapter(tokenListAdapter);

        addToken.setOnClickListener(v -> presenter.onClickShowButton());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (addForm != null) {
            addForm.dismiss();
        }
    }

    @Override
    public void setTokens(List<Token> tokens) {
        tokenListAdapter.setToken(tokens);
    }

    @Override
    public void showAddForm() {
        TextView newTokenName = new EditText(this);

        addForm = new AlertDialog.Builder(this)
                .setTitle("Добавить новый токен")
                .setView(newTokenName)
                .setCancelable(true)
                .setPositiveButton("Добавить", (dialog, which) -> {
                    String tokenName = newTokenName.getText().toString();
                    presenter.onClickAddButton(tokenName, "b419*********7db");
                })
                .setNegativeButton("Отменить", (dialog, which) -> {
                    presenter.onHideAddForm();
                })
                .setOnCancelListener(dialog -> presenter.onHideAddForm())
                .show();
    }

    @Override
    public void hideAddForm() {
        if (addForm != null) {
            addForm.dismiss();
        }
    }
}
