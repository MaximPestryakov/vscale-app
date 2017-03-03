package me.maximpestryakov.vscaleapp.tokenlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Token;


public class TokenListAdapter extends RecyclerView.Adapter<TokenListAdapter.TokenViewHolder> {

    private List<Token> tokens = null;

    @Override
    public TokenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.token_list_item, parent, false);
        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TokenViewHolder holder, int position) {
        if (tokens == null) {
            return;
        }
        Token token = tokens.get(position);
        holder.tokenName.setText(token.getName());
        holder.tokenValue.setText(token.getValue());
    }

    @Override
    public int getItemCount() {
        if (tokens == null) {
            return 0;
        }
        return tokens.size();
    }

    public void setToken(List<Token> tokens) {
        this.tokens = tokens;
        notifyDataSetChanged();
    }

    static class TokenViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tokenName)
        TextView tokenName;

        @BindView(R.id.tokenValue)
        TextView tokenValue;

        TokenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
