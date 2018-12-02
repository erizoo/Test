package by.boiko.erizo.testdes.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.boiko.erizo.testdes.R;
import by.boiko.erizo.testdes.db.RealmModel;
import by.boiko.erizo.testdes.ui.base.BaseViewHolder;

public class RealmAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public Context context;
    private List<RealmModel> realmModelList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RealmAdapter.RealmViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_realm, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return realmModelList.size();
    }

    public void setItems(List<RealmModel> realmModelList) {
        this.realmModelList.clear();
        this.realmModelList.addAll(realmModelList);
        notifyDataSetChanged();
    }

    public class RealmViewHolder extends BaseViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.amount)
        TextView amount;

        public RealmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBind(int position) {
            title.setText(realmModelList.get(position).getTitle());
            amount.setText(realmModelList.get(position).getAmount() + " шт");
        }
    }
}