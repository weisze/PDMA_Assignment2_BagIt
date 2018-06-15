package sg.com.kaplan.pdma_assignment2_bagit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

class req_MyAdapter extends RecyclerView.Adapter<req_MyViewHolder> {

    ArrayList<req_MyData> mDataset;

    public req_MyAdapter(ArrayList<req_MyData> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public req_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_req_my_text_view, parent, false);
        req_MyViewHolder vh = new req_MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull req_MyViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
