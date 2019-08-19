package service.com.surebot.info.serviceperson.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import service.com.surebot.info.serviceperson.Adapter.awardsAdapter;
import service.com.surebot.info.serviceperson.DataFiles.awardsData;
import service.com.surebot.info.serviceperson.R;

public class awardsActivity extends AppCompatActivity implements awardsAdapter.onAwardsListner {

    ImageView camera,gallery;
    RecyclerView r1;
    Button save;
    awardsAdapter adapter,adapter2;
    int i=0;
    List<awardsData> awardsDatas2 = new ArrayList<>();
    List<awardsData> awardsDatas = new ArrayList<>();
    List<String> Awards = new ArrayList<>();
    ArrayList<String>AwardsUP=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
      //  r2=findViewById(R.id.awardRecycler2);
        camera = findViewById(R.id.AwardCamera);
        gallery = findViewById(R.id.AwardGallery);
        r1=findViewById(R.id.awardRecycler1);
        save = findViewById(R.id.AwardSave);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(awardsActivity.this,2);
        r1.setLayoutManager(layoutManager);
        adapter = new awardsAdapter(awardsDatas,this);

      //  RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
//        r2.setLayoutManager(layoutManager1);
//        adapter2 = new awardsAdapter(awardsDatas2,this);
//        r2.setAdapter(adapter2);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });

      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              awardsDatas2 = adapter.retrieveData();
              for (int j = 0; j < Awards.size(); j++) {
                  Log.e("lol", "onClick: "+Awards.get(j) );
                  AwardsUP.add(Awards.get(j));
                  if (awardsDatas2.get(j).getText() == null) {
                      AwardsUP.add(" ");
                  } else AwardsUP.add(awardsDatas2.get(i).getText());
              }
              Log.e("lol", "onCreate: "+AwardsUP );
              //TODO have to clean Array List AwardUP after calling upload api
          }


      });
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //  TODO: HAve To fix thisss

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                try {

                    Uri tempUri = getImageUri(getApplicationContext(), thumbnail);
                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File f = new File(getRealPathFromURI(tempUri));
//                    if(i==0) {


                    Awards.add(f.toString());
                    awardsData a = new awardsData(thumbnail);
                        awardsDatas.add(a);
                        r1.setAdapter(adapter);
//                            i=1;
//                    }else{
//                        awardsData a = new awardsData(thumbnail);
//                        awardsDatas2.add(a);
//                        r2.setAdapter(adapter2);
//                        i=0;
//                    }

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);

                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Log.e("image", "onActivityResult: " );
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from ", picturePath + "");

//                if(i==0) {
                Awards.add(picturePath);
                awardsData a = new awardsData(thumbnail);
                awardsDatas.add(a);
                r1.setAdapter(adapter);
//                    i=1;
//                }else{
//                    awardsData a = new awardsData(thumbnail);
//                    awardsDatas2.add(a);
//                    r2.setAdapter(adapter2);
//                    i=0;
//                }


            }

            }
        }

    @Override
    public void onAwardsClick(int position) {

    }
}

