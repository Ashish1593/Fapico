package ashish1593.space.fapico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.vlk.multimager.activities.GalleryActivity;
import com.vlk.multimager.activities.MultiCameraActivity;
import com.vlk.multimager.utils.Constants;
import com.vlk.multimager.utils.Image;
import com.vlk.multimager.utils.Params;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonCapture;
    Button button_select;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCapture = findViewById(R.id.button_capture);
        button_select = findViewById(R.id.button_select);
        int numberOfColumns = 3;
        recyclerView = findViewById(R.id.rv_image_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultiCameraActivity.class);
                Params params = new Params();
                params.setCaptureLimit(10);
                intent.putExtra(Constants.KEY_PARAMS, params);
                startActivityForResult(intent, Constants.TYPE_MULTI_CAPTURE);
            }
        });

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                Params params = new Params();
                params.setCaptureLimit(10);
                params.setPickerLimit(10);
                intent.putExtra(Constants.KEY_PARAMS, params);
                startActivityForResult(intent, Constants.TYPE_MULTI_PICKER);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        ArrayList<Image> imagesList;
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.TYPE_MULTI_CAPTURE:
                imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                adapter = new MyRecyclerViewAdapter(this, imagesList);
                recyclerView.setAdapter(adapter);
                break;
            case Constants.TYPE_MULTI_PICKER:
                imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                adapter = new MyRecyclerViewAdapter(this, imagesList);
                recyclerView.setAdapter(adapter);
                break;
        }
    }
    }

