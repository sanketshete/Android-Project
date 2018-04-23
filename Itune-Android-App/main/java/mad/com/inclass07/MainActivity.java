package mad.com.inclass07;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    static ListView listView;
    static public ArrayList<App> filterAppList;
    static public ArrayList<App> appList;
    String url;
    Switch aSwitch;
    AppAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DBDataManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filterAppList = new ArrayList<App>();
        listView = (ListView) findViewById(R.id.listView);
        aSwitch = (Switch) findViewById(R.id.switch1);
        databaseManager = new DBDataManager(this);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    aSwitch.setText("Ascending");
                    Collections.sort(appList, new PriceComparatorAsc());
                    adapter = new AppAdapter(MainActivity.this,R.layout.row_item,appList);
                    listView.setAdapter(adapter);
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            App app = appList.get(position);
                            Toast.makeText(MainActivity.this,"Added to filter",Toast.LENGTH_SHORT).show();

                            filterAppList.add(app);
                            appList.remove(app);
                            adapter.notifyDataSetChanged();
                            databaseManager.saveApp(app);
                            // specify an adapter (see also next example)
                            mAdapter = new AppRecyclerView(filterAppList, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                            return false;

                        }
                    });
                }
                else{
                    aSwitch.setText("Descending");
                    Collections.sort(appList, new PriceComparator());
                    AppAdapter adapter = new AppAdapter(MainActivity.this,R.layout.row_item,appList);
                    listView.setAdapter(adapter);
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            App app = appList.get(position);
                            Toast.makeText(MainActivity.this,"Added to filter",Toast.LENGTH_SHORT).show();
                            filterAppList.add(app);
                            // specify an adapter (see also next example)
                            mAdapter = new AppRecyclerView(filterAppList, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                            return false;

                        }
                    });
                }

            }
        });
        findViewById(R.id.imageViewRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAppAsyncTask(new GetAppAsyncTask.AsyncResponse() {
                    @Override
                    public void processFinish(ArrayList<App> apps) {
                        appList = apps;
                        Collections.sort(appList, new PriceComparator());
                        AppAdapter adapter = new AppAdapter(MainActivity.this,R.layout.row_item,apps);
                        listView.setAdapter(adapter);
                        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                App app = appList.get(position);
                                Toast.makeText(MainActivity.this,"Added to filter",Toast.LENGTH_SHORT).show();
                                filterAppList.add(app);

                                // specify an adapter (see also next example)
                                mAdapter = new AppRecyclerView(filterAppList, MainActivity.this);
                                mRecyclerView.setAdapter(mAdapter);
                                return false;

                            }
                        });
                    }
                }).execute(url);
            }
        });
        url = "https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json";
        if (isConnected()) {

            new GetAppAsyncTask(new GetAppAsyncTask.AsyncResponse() {
                @Override
                public void processFinish(ArrayList<App> apps) {
                    appList = apps;
                    Collections.sort(appList, new PriceComparator());
                    AppAdapter adapter = new AppAdapter(MainActivity.this,R.layout.row_item,apps);
                    listView.setAdapter(adapter);
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            App app = appList.get(position);
                            Toast.makeText(MainActivity.this,"Added to filter",Toast.LENGTH_SHORT).show();
                            filterAppList.add(app);

                            // specify an adapter (see also next example)
                            mAdapter = new AppRecyclerView(filterAppList, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                            return false;

                        }
                    });
                }
            }).execute(url);
        }else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true); //for efficiency purpose

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AppRecyclerView(filterAppList, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
    public class PriceComparator implements Comparator<App> {

        @Override
        public int compare(App app1, App app2) {
            double rating1 = Double.parseDouble(app1.getPrice());
            double rating2 = Double.parseDouble(app2.getPrice());
            if(rating1 < rating2){
                return 1;
            } else {
                return -1;
            }
        }

    }
    public class PriceComparatorAsc implements Comparator<App> {

        @Override
        public int compare(App app1, App app2) {
            double rating1 = Double.parseDouble(app1.getPrice());
            double rating2 = Double.parseDouble(app2.getPrice());
            if(rating1 > rating2){
                return 1;
            } else {
                return -1;
            }
        }

    }
    public void deleteFilter(View view){
        ImageView iv = (ImageView) view;
    }

    public static void addApp (App app, Context context){
        appList.add(app);
        AppAdapter adapter = new AppAdapter(context,R.layout.row_item,appList);
        listView.setAdapter(adapter);
    }
}
