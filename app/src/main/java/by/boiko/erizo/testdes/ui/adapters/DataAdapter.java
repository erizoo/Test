package by.boiko.erizo.testdes.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.boiko.erizo.testdes.R;
import by.boiko.erizo.testdes.db.Employee;
import by.boiko.erizo.testdes.ui.base.BaseViewHolder;

public class DataAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public Context context;
    private List<Employee> employeeList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataAdapter.RealmViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_realm, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void setItems(List<Employee> employeeList) {
        this.employeeList.clear();
        this.employeeList.addAll(employeeList);
        notifyDataSetChanged();
    }

    public class RealmViewHolder extends BaseViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.image_view)
        ImageView imageView;

        public RealmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBind(int position) {
            Glide.with(itemView)
                    .load(employeeList.get(position).getImage())
                    .into(imageView);
            title.setText(employeeList.get(position).getTitle());
            type.setText(employeeList.get(position).getType());
            amount.setText(employeeList.get(position).getAmount() + " шт");
        }
    }
}