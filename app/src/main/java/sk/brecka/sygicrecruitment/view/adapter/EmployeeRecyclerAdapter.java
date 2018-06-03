package sk.brecka.sygicrecruitment.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import sk.brecka.sygicrecruitment.R;
import sk.brecka.sygicrecruitment.databinding.EmployeeItemBinding;
import sk.brecka.sygicrecruitment.model.business.Employee;

public class EmployeeRecyclerAdapter extends RecyclerView.Adapter<EmployeeRecyclerAdapter.EmployeeViewHolder> {

    public interface EmployeeCallback {
        void imageDownload(Employee employee, ImageView imageView);

        void onEmployeeClick(Employee employee, ImageView imageView);
    }

    //
    private LayoutInflater mLayoutInflater;
    private List<Employee> mEmployees;

    private EmployeeCallback mCallback;

    public EmployeeRecyclerAdapter(Context context, EmployeeCallback callback) {
        mLayoutInflater = LayoutInflater.from(context);
        mCallback = callback;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmployeeItemBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeeViewHolder holder, int position) {
        final Employee employee = mEmployees.get(position);

        holder.mBinding.setEmployee(employee);
        holder.mBinding.employeeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onEmployeeClick(mEmployees.get(holder.getAdapterPosition()), holder.mBinding.iconImageView);
            }
        });

        mCallback.imageDownload(employee, holder.mBinding.iconImageView);
    }

    @Override
    public int getItemCount() {
        return mEmployees != null ? mEmployees.size() : 0;
    }

    public void setEmployees(List<Employee> employees) {
        mEmployees = employees;
        notifyDataSetChanged();
    }

    public void clear() {
        if (mEmployees != null) {
            mEmployees.clear();
        }

        notifyDataSetChanged();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        EmployeeItemBinding mBinding;

        EmployeeViewHolder(EmployeeItemBinding employeeItemBinding) {
            super(employeeItemBinding.getRoot());
            mBinding = employeeItemBinding;
        }
    }
}
