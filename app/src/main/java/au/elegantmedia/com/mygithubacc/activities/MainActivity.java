package au.elegantmedia.com.mygithubacc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import au.elegantmedia.com.mygithubacc.R;
import au.elegantmedia.com.mygithubacc.helpers.Constant;
import au.elegantmedia.com.mygithubacc.models.User;
import au.elegantmedia.com.mygithubacc.services.sync.UserSync;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.avatar)
    ImageView mImageView;
    @BindView(R.id.login_name)
    TextView mLogin;
    @BindView(R.id.tv_name)
    TextView mName;
    @BindView(R.id.tv_company)
    TextView mCompany;
    @BindView(R.id.tv_location)
    TextView mLocation;
    @BindView(R.id.tv_repo)
    TextView mRepo;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(Constant.USER_EXTRA);

        if (user != null) {
            mLogin.setText(user.login);
            Picasso.with(getApplication()).load(user.avatar_url).into(mImageView);
            mName.setText(user.name);
            mCompany.setText(user.company);
            mLocation.setText(user.location);
            mRepo.setText(user.public_repos);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int getItem = item.getItemId();

        if (getItem == R.id.user_select) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

            return true;
        }
        if (getItem == R.id.repo_select) {
            Intent intent = new Intent(MainActivity.this, ReposActivity.class);
            intent.putExtra("userName", user.login);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

