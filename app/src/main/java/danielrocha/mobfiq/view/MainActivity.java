package danielrocha.mobfiq.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.databinding.ActivityMainBinding;
import danielrocha.mobfiq.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding mainActivityBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setupObserver(mainViewModel);
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(MainActivity.this);
        mainActivityBinding.setMainViewModel(mainViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }

    @Override public void update(Observable observable, Object data) {

    }
}
