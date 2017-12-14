package com.telecorp.dashqueue.ui.profile

import activitystarter.MakeActivityStarter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.base.BaseActivity
import com.telecorp.dashqueue.ui.main.yourprofile.YourProfileFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.view_main_appbar.*
import javax.inject.Inject

/**
 * Created by Saran on 7/12/2560.
 */
@MakeActivityStarter
class ProfileActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val mProfileFragment: YourProfileFragment by lazy {
        YourProfileFragment.newInstance()
    }
    private val mToolbar: Toolbar by lazy {
        toolbar_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initToolbar()
        initContent()
    }

    private fun initContent() {
        if (!isFinishing) {

            supportFragmentManager.beginTransaction().replace(R.id.framelayout_profile_container, mProfileFragment).commit()
        }
    }

    override fun onBackPressed() {
        if (!mProfileFragment.onConsumeBackPressed()) {
            super.onBackPressed()
        }
    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.Profile)
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mDispatchingAndroidInjector
    }
}
