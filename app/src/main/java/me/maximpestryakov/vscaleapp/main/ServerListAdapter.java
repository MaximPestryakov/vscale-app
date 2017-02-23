package me.maximpestryakov.vscaleapp.main;

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

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerViewHolder> {

    private List<Server> servers;

    public ServerListAdapter(List<Server> servers) {
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
        holder.setServer(servers.get(position));
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

        public ServerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setServer(Server server) {
            this.server = server;

            name.setText(server.getName());
            switch (server.getOs()) {
                case "centos":
                    osLogo.setImageResource(R.drawable.ic_centos);
                    break;
                case "debian":
                    osLogo.setImageResource(R.drawable.ic_debian);
                    break;
                case "fedora":
                    osLogo.setImageResource(R.drawable.ic_fedora);
                    break;
                case "opensuse":
                    osLogo.setImageResource(R.drawable.ic_opensuse);
                    break;
                case "ubuntu":
                    osLogo.setImageResource(R.drawable.ic_ubuntu);
                    break;
            }
            ipAddress.setText(server.getIpAddress());
            switch (server.getStatus()) {
                case "started":
                    status.setImageResource(R.drawable.ic_started);
                    break;
                case "stopped":
                    status.setImageResource(R.drawable.ic_stopped);
                    break;
                case "billing":
                    status.setImageResource(R.drawable.ic_billing);
                    break;
            }
            switch (server.getRplan()) {
                case "small":
                    info.setText(App.string(R.string.rplan_info_small));
                    break;
                case "medium":
                    info.setText(App.string(R.string.rplan_info_medium));
                    break;
                case "large":
                    info.setText(App.string(R.string.rplan_info_large));
                    break;
                case "huge":
                    info.setText(App.string(R.string.rplan_info_huge));
                    break;
                case "monster":
                    info.setText(App.string(R.string.rplan_info_monster));
                    break;
            }
        }
    }
}
