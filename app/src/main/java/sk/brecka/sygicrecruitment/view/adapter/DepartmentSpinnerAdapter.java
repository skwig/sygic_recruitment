package sk.brecka.sygicrecruitment.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import sk.brecka.sygicrecruitment.model.business.Department;

public class DepartmentSpinnerAdapter extends ArrayAdapter<Department> {

    public DepartmentSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);

        final Department department = getItem(position);
        if (department != null) {
            textView.setText(department.getName());
        }

        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);

        final Department department = getItem(position);
        if (department != null) {
            textView.setText(department.getName());
        }

        return textView;
    }
}
