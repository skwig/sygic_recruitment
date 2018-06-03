package sk.brecka.sygicrecruitment.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.experimental.Accessors;
import sk.brecka.sygicrecruitment.model.Repository;
import sk.brecka.sygicrecruitment.model.business.Department;
import sk.brecka.sygicrecruitment.model.business.Employee;

@Accessors(prefix = "m")
public class MainViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    public enum State {
        DISPLAYING, LOADING, ERROR
    }

    //
    @Getter
    private MutableLiveData<List<Department>> mDepartments = new MutableLiveData<>();

    @Getter
    private MutableLiveData<Department> mDepartment = new MutableLiveData<>();

    @Getter
    private MutableLiveData<State> mStatus = new MutableLiveData<>();

    //
    private Repository mRepository;
    private Picasso mPicasso;

    @Inject
    public MainViewModel(Repository repository, Picasso picasso) {
        mRepository = repository;
        mPicasso = picasso;

        mStatus.setValue(State.LOADING);

        loadDepartments();
    }

    public void onDepartmentSelected(int position) {
        Log.d(TAG, "onDepartmentSelected: " + position);

        // change the displayed department
        if (mDepartment.getValue() != null && mDepartments.getValue().size() > 0) {
            mDepartment.setValue(mDepartments.getValue().get(position));
        } else {
            mDepartment.setValue(null);
        }
    }

    public void imageDownload(Employee employee, ImageView imageView, Drawable placeholder) {
        Log.d(TAG, "imageDownload: " + employee);
        mPicasso.load(employee.getAvatarUrl())
                .placeholder(placeholder)
                .into(imageView);
    }

    public void loadDepartments() {
        Log.d(TAG, "loadDepartments:");
        new LoadDepartments().execute();
    }

    //
    private class LoadDepartments extends AsyncTask<Void, Void, List<Department>> {

        private boolean mThrewException = false;

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: ");
            mStatus.setValue(State.LOADING);
        }

        @Override
        protected List<Department> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: ");
            try {
                return mRepository.loadDepartments();
            } catch (IOException e) {
                mThrewException = true;
                Log.e(TAG, "doInBackground: ", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Department> listLiveData) {
            Log.d(TAG, "onPostExecute: ");
            mStatus.setValue(mThrewException ? State.ERROR : State.DISPLAYING);

            if (listLiveData != null && listLiveData.size() > 0) {
                mDepartment.setValue(listLiveData.get(0));
            } else {
                mDepartment.setValue(null);
            }

            mDepartments.setValue(listLiveData);
        }
    }
}
