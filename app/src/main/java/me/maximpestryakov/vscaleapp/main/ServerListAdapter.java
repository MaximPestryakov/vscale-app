package me.maximpestryakov.vscaleapp.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.vscaleapp.App;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;
import me.maximpestryakov.vscaleapp.server.ServerActivity;

class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerViewHolder> {

    private Context context;
    private List<Server> servers;

    ServerListAdapter(Context context, List<Server> servers) {
        this.context = context;
        this.servers = servers;
    }

    @Override
    public ServerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.server_list_item, parent, false);
        return new ServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServerViewHolder holder, int position) {
        Server server = servers.get(position);
        holder.setServer(server);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ServerActivity.class);
            intent.putExtra("ctid", server.getCtid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return servers.size();
    }

    static class ServerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.osLogo)
        AppCompatImageView osLogo;
        @BindView(R.id.ipAddress)
        TextView ipAddress;
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.status)
        AppCompatImageView status;
        private Server server;

        ServerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setServer(Server server) {
            this.server = server;

            name.setText(server.getName());
            switch (server.getOs()) {
                case CENTOS:
                    osLogo.setImageResource(R.drawable.ic_centos);
                    break;
                case DEBIAN:
                    osLogo.setImageResource(R.drawable.ic_debian);
                    break;
                case FEDORA:
                    osLogo.setImageResource(R.drawable.ic_fedora);
                    break;
                case OPENSUSE:
                    osLogo.setImageResource(R.drawable.ic_opensuse);
                    break;
                case UBUNTU:
                    osLogo.setImageResource(R.drawable.ic_ubuntu);
                    break;
            }
            ipAddress.setText(server.getIpAddress());
            switch (server.getStatus()) {
                case STARTED:
                    status.setImageResource(R.drawable.ic_started);
                    break;
                case STOPPED:
                    status.setImageResource(R.drawable.ic_stopped);
                    break;
                case BILLING:
                    status.setImageResource(R.drawable.ic_billing);
                    break;
            }
            switch (server.getPlan()) {
                case SMALL:
                    info.setText(App.string(R.string.rplan_info_small));
                    break;
                case MEDIUM:
                    info.setText(App.string(R.string.rplan_info_medium));
                    break;
                case LARGE:
                    info.setText(App.string(R.string.rplan_info_large));
                    break;
                case HUGE:
                    info.setText(App.string(R.string.rplan_info_huge));
                    break;
                case MONSTER:
                    info.setText(App.string(R.string.rplan_info_monster));
                    break;
            }
        }
    }
}
