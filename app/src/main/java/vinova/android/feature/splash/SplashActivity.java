package vinova.android.feature.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vinova.android.R;
import vinova.android.base.Lifecycle;
import vinova.android.base.activity.BaseActivity;
import vinova.android.base.viewmodel.BaseViewModel;
import vinova.android.databinding.ActivitySplashBinding;
import vinova.android.feature.home.HomeActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashViewModel splashViewModel;
    private ActivitySplashBinding activitySplashBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        activitySplashBinding.setViewModel(splashViewModel);
        splashViewModel.checkingInternet();
    }

    @Override
    public void launchHomeActivity() {
        startActivity(HomeActivity.getStartIntent(getApplicationContext()));
        finish();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return splashViewModel;
    }

    @Nullable
    @Override
    protected BaseViewModel createBaseViewModel(@Nullable BaseViewModel.State savedBaseViewModelState) {
        splashViewModel = new SplashViewModel(savedBaseViewModelState);
        return splashViewModel;
    }
}