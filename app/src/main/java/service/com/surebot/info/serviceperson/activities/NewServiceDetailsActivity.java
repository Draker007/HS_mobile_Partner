package service.com.surebot.info.serviceperson.activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.com.surebot.info.serviceperson.adapters.NewPaymentServiceAdapter;
import service.com.surebot.info.serviceperson.adapters.NewservicesAdapter;
import service.com.surebot.info.serviceperson.R;
import service.com.surebot.info.serviceperson.utils.Utils;

public class NewServiceDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.newservicesRecycler)
    RecyclerView gnewservicesRecycler;

    @BindView(R.id.servicePaymentRecycler)
    RecyclerView servicePaymentRecycler;
    @BindView(R.id.serviceCodeCL)
    ConstraintLayout serviceCodeCL;
    @BindView(R.id.okButton)
    Button okButton;
    @BindView(R.id.rescheduleEndServicesRL)
    RelativeLayout rescheduleEndServicesRL;
    @BindView(R.id.rescheduleTV)
    TextView rescheduleTV;
    @BindView(R.id.paymentReceivedTV)
    TextView paymentReceivedTV;
    @BindView(R.id.serviceRescheduledLL)
    LinearLayout serviceRescheduledLL;
    @BindView(R.id.constraintLayout28)
    ConstraintLayout constraintLayout28;
    @BindView(R.id.endServiceTV)
    TextView endServiceTV;
    @BindView(R.id.paymentServiceRL)
    RelativeLayout paymentServiceRL;
    @BindView(R.id.cancelStartServiceCL)
    ConstraintLayout cancelStartServiceCL;
    @BindView(R.id.paymentSuccesfullCL)
    ConstraintLayout paymentSuccesfullCL;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.successIV)
    ImageView successIV;
    @BindView(R.id.successIVAnim)
    ImageView successIVAnim;
    private NewServiceDetailsActivity context;
    private LinearLayout hourLL;
    private NestedScrollView hourScrollView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service_details);
        ButterKnife.bind(this);
        context = NewServiceDetailsActivity.this;

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gnewservicesRecycler.setLayoutManager(llm);

        NewservicesAdapter NewservicesAdapter = new NewservicesAdapter(this);
        gnewservicesRecycler.setAdapter(NewservicesAdapter);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.VERTICAL);

        servicePaymentRecycler.setLayoutManager(llm2);
        NewPaymentServiceAdapter NewPaymentServiceAdapter = new NewPaymentServiceAdapter(this);
        servicePaymentRecycler.setAdapter(NewPaymentServiceAdapter);

        button6.setOnClickListener(this);
        okButton.setOnClickListener(this);
        rescheduleTV.setOnClickListener(this);
        endServiceTV.setOnClickListener(this);
        paymentReceivedTV.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showAlertReschedulingDialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_rescheduling_service);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        hourLL = dialog.findViewById(R.id.hourLL);
        TextView submitTV = dialog.findViewById(R.id.submitTV);
        TextView cancelTV = dialog.findViewById(R.id.cancelTV);
        hourScrollView = dialog.findViewById(R.id.hourScrollView);
        String[] hours = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        for (int i = 0; i < 12; i++) {
            TextView tv = new TextView(this);
            tv.setTextSize(22);
            tv.setText(hours[i]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setTextColor(getResources().getColor(R.color.color_black));
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(layoutParams);
            tv.setPadding(0, 11, 0, 11);
            hourLL.addView(tv);
        }
        LinearLayout secondLL = dialog.findViewById(R.id.secondLL);
        String[] seconds = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20"};
        for (int i = 0; i < 19; i++) {
            TextView tv = new TextView(this);
            tv.setTextSize(22);
            tv.setText(seconds[i]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv.setPadding(0, 11, 0, 11);

            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setTextColor(getResources().getColor(R.color.color_black));
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(layoutParams);
            secondLL.addView(tv);
        }
        LinearLayout amPmLL = dialog.findViewById(R.id.amPmLL);

        String[] amPm = {"AM", "PM"};
        for (int i = 0; i < 2; i++) {
            TextView tv = new TextView(this);
            tv.setTextSize(12);
            tv.setText(amPm[i]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv.setPadding(0, 11, 0, 11);


            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setTextColor(getResources().getColor(R.color.color_black));
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(layoutParams);
            amPmLL.addView(tv);
        }
        dialog.show();
        getValueFromScrollView();

        submitTV.setOnClickListener(v -> {
            dialog.dismiss();
            Utils.showHideView(false, rescheduleEndServicesRL);
            Utils.showHideView(true, serviceRescheduledLL);
        });

        cancelTV.setOnClickListener(v -> {
            dialog.dismiss();
            Utils.showHideView(true, rescheduleEndServicesRL);
            Utils.showHideView(false, serviceRescheduledLL);

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getValueFromScrollView() {
        final Rect scrollBounds = new Rect();
        hourScrollView.getHitRect(scrollBounds);
        hourScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            for (int i = 0; i < hourLL.getChildCount(); i++) {
                View childView = hourLL.getChildAt(i);
                if (childView != null) {
                    if (childView.getLocalVisibleRect(scrollBounds)) {
                        //Here is the position of first visible view
                        Log.i("SelectedValue", " " + (i + 1));
                        break;
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6: {
                Utils.showHideView(true, serviceCodeCL);
                Utils.showHideView(false, cancelStartServiceCL);
            }
            break;
            case R.id.okButton: {
                Utils.showHideView(false, serviceCodeCL);
                Utils.showHideView(true, rescheduleEndServicesRL);
            }
            break;
            case R.id.rescheduleTV: {
                showAlertReschedulingDialog();
            }
            break;
            case R.id.endServiceTV: {
                Utils.showHideView(true, paymentServiceRL);
                Utils.showHideView(false, rescheduleEndServicesRL);
            }
            break;
            case R.id.paymentReceivedTV: {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.left_to_right_animation);
                constraintLayout28.startAnimation(animation);
                Utils.showHideView(true, paymentSuccesfullCL);
                Utils.showHideView(false, paymentServiceRL);

                Animation animationUp = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.down_anim);
                successIVAnim.startAnimation(animationUp);
                Utils.showHideView(true, successIVAnim);
                animationUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Utils.showHideView(false, successIVAnim);
                        Utils.showHideView(true, successIV);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            break;
        }
    }
}