package ashish1593.space.fapico;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
    int mNoOfColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCapture = findViewById(R.id.button_capture);
        button_select = findViewById(R.id.button_select);
//        mNoOfColumns = Utility.calculateNoOfColumns(this);
        recyclerView = findViewById(R.id.rv_image_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultiCameraActivity.class);
                Params params = new Params();
                params.setCaptureLimit(20);
                intent.putExtra(Constants.KEY_PARAMS, params);
                startActivityForResult(intent, Constants.TYPE_MULTI_CAPTURE);
            }
        });

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                Params params = new Params();
                params.setCaptureLimit(20);
                params.setPickerLimit(20);
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


//    public static class GridAutofitLayoutManager extends GridLayoutManager {
//        private int mColumnWidth;
//        private boolean mColumnWidthChanged = true;
//        public GridAutofitLayoutManager(Context context, int columnWidth) {
//            super(context, 3);
//            setColumnWidth(checkedColumnWidth(context, columnWidth));
//        }
//        public GridAutofitLayoutManager(Context context, int columnWidth, int orientation, boolean reverseLayout) { /* Initially set spanCount to 1, will be changed automatically later. */
//            super(context, 3, orientation, reverseLayout);
//            setColumnWidth(checkedColumnWidth(context, columnWidth));
//        }
//        private int checkedColumnWidth(Context context, int columnWidth) {
//            if (columnWidth <= 0) { /* Set default columnWidth value (48dp here). It is better to move this constant to static constant on top, but we need context to convert it to dp, so can't really do so. */
//                columnWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, context.getResources().getDisplayMetrics());
//            }
//            return columnWidth;
//        }
//        public void setColumnWidth(int newColumnWidth) {
//            if (newColumnWidth > 0 && newColumnWidth != mColumnWidth) {
//                mColumnWidth = newColumnWidth;
//                mColumnWidthChanged = true;
//            }
//        }
//        @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//            if (mColumnWidthChanged && mColumnWidth > 0) {
//                int totalSpace;
//                if (getOrientation() == VERTICAL) {
//                    totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
//                } else {
//                    totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
//                }
//                int spanCount = Math.max(1, totalSpace / mColumnWidth);
//                setSpanCount(spanCount);
//                mColumnWidthChanged = false;
//            }
//            super.onLayoutChildren(recycler, state);
//        }
//    }
}

