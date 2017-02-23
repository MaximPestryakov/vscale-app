package me.maximpestryakov.vscaleapp.main;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maximpestryakov.vscaleapp.R;
import me.maximpestryakov.vscaleapp.api.model.Server;

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerViewHolder> {

    private Context context;
    private List<Server> servers;

    public ServerListAdapter(Context context, List<Server> servers) {
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
                case "ic_centos":
                    osLogo.setImageResource(R.drawable.ic_centos);
                    break;
                case "ic_debian":
                    osLogo.setImageResource(R.drawable.ic_debian);
                    break;
                case "ic_fedora":
                    osLogo.setImageResource(R.drawable.ic_fedora);
                    break;
                case "ic_opensuse":
                    osLogo.setImageResource(R.drawable.ic_opensuse);
                    break;
                case "ic_ubuntu":
                    osLogo.setImageResource(R.drawable.ic_ubuntu);
                    break;
            }
            ipAddress.setText(server.getIpAddress());
            switch (server.getStatus()) {
                case "ic_started":
                    status.setImageResource(R.drawable.ic_started);
                    break;
                case "ic_stopped":
                    status.setImageResource(R.drawable.ic_stopped);
                    break;
                case "ic_billing":
                    status.setImageResource(R.drawable.ic_billing);
                    break;
            }
            switch (server.getRplan()) {
                case "small":
                    info.setText("512 МБ  20 ГБ  1 CPU  200 \u20BD");
                    break;
                case "medium":
                    info.setText("1 ГБ  30 ГБ  1 CPU  400 \u20BD");
                    break;
                case "large":
                    info.setText("2 ГБ  40 ГБ  2 CPU  800 \u20BD");
                    break;
                case "huge":
                    info.setText("4 ГБ  60 ГБ  2 CPU  1 600 \u20BD");
                    break;
                case "monster":
                    info.setText("8 ГБ  80 ГБ  4 CPU  3 200 \u20BD");
                    break;
            }
        }
    }
}
