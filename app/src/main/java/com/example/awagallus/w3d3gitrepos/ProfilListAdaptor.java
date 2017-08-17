package com.example.awagallus.w3d3gitrepos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gitRepos.IndividualProfil;

/**
 * Created by HP on 8/17/2017.
 */

public class ProfilListAdaptor extends RecyclerView.Adapter<ProfilListAdaptor.ViewHolder> {
    ArrayList<IndividualProfil> reposList = new ArrayList<>();
    Context context;

    public ProfilListAdaptor(ArrayList<IndividualProfil> repoList) {
        this.reposList = reposList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRepoName,tvRepoDescription,tvRepoURl,tvRepoCreated;
        public ViewHolder(View itemView) {
            super(itemView);
            tvRepoName = (TextView) itemView.findViewById(R.id.tvRepoName);
            tvRepoDescription = (TextView) itemView.findViewById(R.id.tvRepoDescription);
            tvRepoURl = (TextView) itemView.findViewById(R.id.tvRepoURl);
            tvRepoCreated = (TextView) itemView.findViewById(R.id.tvRepoCreated);

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final IndividualProfil repos = reposList.get(position);
        holder.tvRepoName.setText(repos.getName());
        holder.tvRepoDescription.setText((Integer) repos.getDescription());
        holder.tvRepoURl.setText(repos.getHtmlUrl());
        holder.tvRepoCreated.setText(repos.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

}

