package com.example.maboy.supportbuser;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, DirectionFinderListener {

    private static final int LOCATION_REQUEST = 500;
    private static final String TAG = "ERROR";

    private TextView tvTitle, tvAccount, tvHotline;
    private DrawerLayout drawerLayout;
    private ImageView ivMenu, ivAvatar, ivSearch;

    private ListView lvMenu;
    private ArrayList<ItemMenu> menus;
    private ItemMenuAdapter menuAdapter;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private ArrayList<Bus> buses;
    private ArrayList<BusStop> busStops;
    private ProgressDialog myProgress;

    // Vẽ đường đi
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initItemMenu();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fr_map);
        mapFragment.getMapAsync(this);
//        mapFragment.getView().setClickable(false);

        // Tạo Progress Bar
        myProgress = new ProgressDialog(this);
        myProgress.setTitle("Map Loading ...");
        myProgress.setMessage("Please wait...");
        myProgress.setCancelable(true);

        // Hiển thị Progress Bar
        myProgress.show();

        // list các bus stop
        addBusStop();
    }

    private void initItemMenu() {

        menus = new ArrayList<>();
        menus.add(new ItemMenu("Trang chủ", R.drawable.home));
        menus.add(new ItemMenu("Tìm kiếm", R.drawable.search2));
        menus.add(new ItemMenu("Tin buýt", R.drawable.news));
        menus.add(new ItemMenu("Vé tháng", R.drawable.ticket));
        menus.add(new ItemMenu("Ý kiến khách hàng", R.drawable.feedback));
        menus.add(new ItemMenu("Giới thiệu", R.drawable.help));

        menuAdapter = new ItemMenuAdapter(menus, this);
        lvMenu.setAdapter(menuAdapter);

    }

    private void initViews() {

        tvAccount = (TextView) findViewById(R.id.tv_account);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        tvHotline = (TextView) findViewById(R.id.tv_hotline);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        ivSearch = (ImageView) findViewById(R.id.action_search);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main_layout);
        lvMenu = (ListView) findViewById(R.id.lv_item_menu);

        ivAvatar.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        lvMenu.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.action_search:
//                startActivity(new Intent(this, ActionSearch.class));
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.iv_avatar:
                Toast.makeText(this, "avatar...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:  // Trang chủ
                drawerLayout.closeDrawers();
                break;

            case 1:  // Tìm kiếm
//                startActivity(new Intent(this, ActionSearch.class));
                startActivity(new Intent(this, SearchActivity.class));
                break;

            case 2:  // Tin buýt
                startActivity(new Intent(this, NewsActivity.class));
                break;

            case 3:  // Vé tháng
//                startActivity(new Intent(this, TicketActivity.class));
                startActivity(new Intent(this, VeThangActivity.class));
                break;

            case 4:  // Ý kiến khách hàng
                startActivity(new Intent(this, ReportActivity.class));
                break;

            case 5:  // Giới thiệu
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("SupportBuser");
                builder.setMessage("Phiên bản: 1.1.0" + "\nDeveloper: Duan Vu" + "\nEmail: vuduanuet@gmail.com");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            default:
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                myProgress.dismiss();  // Tắt progress bar sau khi load xong map
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);  // Vị trí hiện tại của mình

        mMap.setOnMarkerClickListener(this);

        // add bus đang đi tới
//        LatLng b1 = new LatLng(21.039901, 105.780911);
//        Marker bm = mMap.addMarker(new MarkerOptions()
//                .position(b1)
//                .title("Bus 13: 1p")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

//        addBusStop();  // List các bus stop
        showBusStop();
        showMyLocation();
        try {
            // Vẽ đường đi của tuyến bus
//            getIdBus();
            getIdBus2();

            // Vẽ đường đi
            sendRequest();

            // Hiển thị Bus Stop
//            showBusStop();

            // Hiển thị vị trí tìm kiếm theo Google
//            showAddress();


        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            showAddress();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            showDiemBanVe();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }

    private void addBusStop() {

        busStops = new ArrayList<>();

        // Khu tập thể Việt Hà
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
        busStops.add(new BusStop("Khu tập thể Việt Hà", buses, 21.060250, 105.773240, 1));

        // 328 Phan Bá Vành
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
        busStops.add(new BusStop("328 Phan Bá Vành", buses, 21.051342, 105.771140, 1));

        // 167-130 K3 Cầu Diễn
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
        busStops.add(new BusStop("167-130 K3 Cầu Diễn", buses, 21.044136, 105.766144, 1));

        // Huyện uỷ Từ Liêm
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
        buses.add(new Bus("Nhổn - Giáp Bát", 32));
        busStops.add(new BusStop("Huyện uỷ Nam Từ Liêm", buses, 21.039912, 105.765986, 1));

        // Đại học Thương Mại
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
        buses.add(new Bus("Nhổn - Giáp Bát", 32));
        buses.add(new Bus("Mai Động - SVĐ Quốc gia", 26));
        busStops.add(new BusStop("Đại học Thương Mại", buses, 21.037445, 105.774322, 1));

        // Đại học Sư phạm Hà Nội
        buses = new ArrayList<>();
        buses.add(new Bus("Nhổn - Giáp Bát", 32));
        buses.add(new Bus("Sân vận động quốc gia - Mai Động", 26));
        busStops.add(new BusStop("Đại học Sư Phạm HN", buses, 21.036550, 105.784227, 1));

        // Đại học Ngoại ngữ
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
//        buses.add(new Bus("BX Mỹ Đình - Đông Anh", 46));
        busStops.add(new BusStop("Đại học Ngoại ngữ", buses, 21.040643, 105.781047, 1));

        // Kí túc xá Đại học Sư phạm HN
        buses = new ArrayList<>();
        buses.add(new Bus("HV Cảnh sát - CV nước Hồ Tây", 13));
        buses.add(new Bus("BX Nam Thăng Long - BX Yên Nghĩa", 27));
        busStops.add(new BusStop("Kí túc xá Đại học Sư phạm HN", buses, 21.041673, 105.783629, 1));

        // Viện Y học cổ truyền
//        buses = new ArrayList<>();
//        buses.add(new Bus("BX Mỹ Đình - Đông Anh", 46));
//        busStops.add(new BusStop("Viện y học cổ truyền", buses, 21.034420, 105.779900, 1));

        // Bộ Công an
        buses = new ArrayList<>();
        buses.add(new Bus("BX Nam Thăng Long - BX Yên Nghĩa", 27));
//        buses.add(new Bus("BX Mỹ Đình - Đông Anh", 46));
        busStops.add(new BusStop("Bộ công an", buses, 21.051109, 105.781730, 1));
    }

    private void showBusStop() {
        // Xoá hết bus stop đã add trước đó
        mMap.clear();

        // add các bus stop với state = 1 vào map
        for (int i = 0; i < busStops.size(); i++) {
            String snippet = "";
            for (int j = 0; j < busStops.get(i).getBuses().size(); j++) {
                snippet = snippet + busStops.get(i).getBuses().get(j).getId() + ", ";
            }
            snippet = snippet.substring(0, snippet.length() - 2);
            String title = busStops.get(i).getName();
            LatLng bs = new LatLng(busStops.get(i).getLat(), busStops.get(i).getLng());
            Marker marker = mMap.addMarker(
                    new MarkerOptions().position(bs)
                            .title(title)
                            .snippet(snippet)
                            .anchor(0.5f, 0.5f)
                            .rotation(0.0f)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

            if (busStops.get(i).getState() == 1) {  // state = 1 thì mới hiển thị bus stop lên map
                marker.setVisible(true);
                Log.e("...", "showBusStop: ");
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bs, 15));
            } else {
                marker.remove();
                Log.e("...", "removeBusStop: ");
            }
        }
    }

    // filter bus stop
    private void filterBusStop(int key) {  // 13, 26, 27, 32, 46

        Log.e("...", "filterBusStop: ");
        for (int i = 0; i < busStops.size(); i++) {
            for (int j = 0; j < busStops.get(i).getBuses().size(); j++) {
                if (busStops.get(i).getBuses().get(j).getId() == key) {
                    busStops.get(i).setState(1);  // Chuyển sang trạng thái hiển thị
                    break;
                } else {
                    busStops.get(i).setState(0);  // Ẩn bus stop
                }
            }

        }
//        this.showBusStop();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    addBusStop();  // add các bus stop
                    this.showBusStop();
                    this.showMyLocation();  // show vị trí ngay lập tức
                }
                break;
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private void getIdBus() {
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ID");
        int id = bundle.getInt("BUS_ID");
        Log.e("...", "getIdBus: ");
        filterBusStop(id);  // Lọc điểm mà nét vẽ đi qua
        sendRequest();  // Vẽ
        showBusStop();
    }

    private void getIdBus2() {
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("ID");
        int id = bundle.getInt("BUS_ID");
        Log.e("...", "getIdBus2: ");
        filterBusStop(id);
//        sendRequest();
        showBusStop();
    }

    private void showAddress() {
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("LATLONG");
        double lat = bundle.getDouble("LAT");
        double lng = bundle.getDouble("LONG");

        // Hiển thị vị trí bên trên
        LatLng latLng = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        Log.e(TAG, "showAddress: " + lat + ", " + lng);
    }

    private void showDiemBanVe() {
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DBV");
        double lat = bundle.getDouble("LAT");
        double lng = bundle.getDouble("LNG");
        String title = bundle.getString("TITLE");
        String snippet = bundle.getString("SNIPPET");
        LatLng latLng = new LatLng(lat, lng);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        Log.e(TAG, "showDiemBanVe: ");
    }

    private void sendRequest() {

        String origin = "";
        String destination = "";

        int count = 0;
        for (int i = 0; i < busStops.size(); i++) {
            if (busStops.get(i).getState() == 1 && count == 0) {
                origin = busStops.get(i).getLat() + "," + busStops.get(i).getLng();
                count++;
            }
            if (busStops.get(i).getState() == 1 && count != 0) {
                destination = busStops.get(i).getLat() + "," + busStops.get(i).getLng();
                count++;
            }
        }

        Log.e("...", "sendRequest: " + origin);
        Log.e("...", "sendRequest: " + destination);

        if (origin.isEmpty()) {
            return;
        }
        if (destination.isEmpty()) {
            return;
        }

        try {
            Log.e("...", "sendRequest: " + " Vu Huu Duan");
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDirectionFinderStart() {

        Log.e("...", "onDirectionFinderStart: ");

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 15));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.RED).
                    width(10);

            Log.e("...", "onDirectionFinderSuccess: ");

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    private void showMyLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        String locationProvider = this.getEnabledLocationProvider();

        if (locationProvider == null) {
            return;
        }

        // Millisecond
        final long MIN_TIME_BW_UPDATES = 1000;
        // Met
        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

        Location myLocation = null;
        try {
            // Lấy ra vị trí.
            myLocation = locationManager
                    .getLastKnownLocation(locationProvider);
        }
        // Với Android API >= 23 phải catch SecurityException.
        catch (SecurityException e) {
            e.printStackTrace();
            return;
        }

        if (myLocation != null) {

            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)             // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_LONG).show();
        }
    }

    private String getEnabledLocationProvider() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        // Tiêu chí để tìm một nhà cung cấp vị trí.
        Criteria criteria = new Criteria();

        // Tìm một nhà cung vị trí hiện thời tốt nhất theo tiêu chí trên.
        // ==> "gps", "network",...
        String bestProvider = locationManager.getBestProvider(criteria, true);

        boolean enabled = locationManager.isProviderEnabled(bestProvider);

        if (!enabled) {
            Toast.makeText(this, "No location provider enabled!", Toast.LENGTH_LONG).show();
            return null;
        }
        return bestProvider;
    }
}
