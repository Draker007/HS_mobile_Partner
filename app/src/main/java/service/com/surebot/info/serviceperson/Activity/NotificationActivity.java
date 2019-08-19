package service.com.surebot.info.serviceperson.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.Adapter.NotificationAdapter;
import service.com.surebot.info.serviceperson.DataFiles.NotificationData;
import service.com.surebot.info.serviceperson.R;

public class NotificationActivity extends AppCompatActivity {
    RecyclerView r1;
    NotificationAdapter adapter;
    List<NotificationData> notificationDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        r1 = findViewById(R.id.notificationRecycler);
        adapter = new NotificationAdapter(notificationDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        r1.setLayoutManager(layoutManager);
        setdata();
    }

    private void setdata() {
            NotificationData a =new NotificationData("Draker","#73,durgambaasda asdasd asdass dasda sdassd asd asda ssdd asd asdd asd","123456789",
            "124","20-06-1995","07:30 AM");
            notificationDataList.add(a);
        notificationDataList.add(a);
        notificationDataList.add(a);
        notificationDataList.add(a);
        r1.setAdapter(adapter);
    }
}
