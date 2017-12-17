package vinova.android.feature.latest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vinova.android.R;
import vinova.android.base.Lifecycle;
import vinova.android.base.fragment.BaseFragment;
import vinova.android.base.viewmodel.BaseViewModel;
import vinova.android.data.network.DataRequestManager;
import vinova.android.databinding.FragmentLatestBinding;

public class LatestFragment extends BaseFragment implements LatestContract.View {
    private LatestViewModel latestViewModel;
    private FragmentLatestBinding fragmentLatestBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentLatestBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_latest, container, false);
        View rootView = fragmentLatestBinding.getRoot();
        fragmentLatestBinding.setViewModel(latestViewModel);
        fetchData();
        refreshData();
        return rootView;
    }

    @Override
    public void hideLoading() {
        if (fragmentLatestBinding.swipeRefresh != null) {
            fragmentLatestBinding.swipeRefresh.setRefreshing(false);
        }
    }

    @Nullable
    @Override
    protected BaseViewModel createBaseViewModel(@Nullable BaseViewModel.State savedBaseViewModelState) {
        DataRequestManager dataRequestManager = DataRequestManager.getInstance();
        latestViewModel = new LatestViewModel(getContext(), savedBaseViewModelState, dataRequestManager);
        return latestViewModel;
    }

    private void fetchData() {
        latestViewModel.fetchData();
    }

    private void refreshData() {
        fragmentLatestBinding.swipeRefresh.setOnRefreshListener(this::fetchData);
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return latestViewModel;
    }
}
