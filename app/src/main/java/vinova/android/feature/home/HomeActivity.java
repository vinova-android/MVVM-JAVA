package vinova.android.feature.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vinova.android.R;
import vinova.android.base.Lifecycle;
import vinova.android.base.activity.BaseActivity;
import vinova.android.base.viewmodel.BaseViewModel;
import vinova.android.databinding.ActivityHomeBinding;
import vinova.android.feature.latest.LatestFragment;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    HomeViewModel homeViewModel;
    ActivityHomeBinding activityHomeBinding;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(homeViewModel);

        if (savedInstanceState != null) {
            return;
        }

        LatestFragment latestViewFragment = new LatestFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, latestViewFragment)
                .commit();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return homeViewModel;
    }

    @Nullable
    @Override
    protected BaseViewModel createBaseViewModel(@Nullable BaseViewModel.State savedBaseViewModelState) {
        homeViewModel = new HomeViewModel(savedBaseViewModelState);
        return homeViewModel;
    }

    @Override
    public void hideLoading() {

    }
}
