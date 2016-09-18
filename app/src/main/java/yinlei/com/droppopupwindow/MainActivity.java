package yinlei.com.droppopupwindow;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Map<String, String>> mMenuData1;
    private ArrayList<Map<String, String>> mMenuData2;
    private ArrayList<Map<String, String>> mMenuData3;

    private ListView mPopListview;
    private PopupWindow popupMenu;
    private SimpleAdapter mMenuAdapter1;
    private SimpleAdapter mMenuAdapter2;
    private SimpleAdapter mMenuAdapter3;
    private int menuIndex = 0;

    TextView mProductTv;
    LinearLayout mProduct;
    TextView mSortTv;
    LinearLayout mSort;
    TextView mCategoryTv;
    LinearLayout mCategory;
    TextView mSupplierListTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initPopMenu();
    }

    private void initView() {
        mProductTv = (TextView) findViewById(R.id.supplier_list_product_tv);
        mProduct = (LinearLayout) findViewById(R.id.supplier_list_product);
        mProduct.setOnClickListener(this);
        mSortTv = (TextView) findViewById(R.id.supplier_list_sort_tv);
        mSort = (LinearLayout) findViewById(R.id.supplier_list_sort);
        mSort.setOnClickListener(this);
        mCategoryTv = (TextView) findViewById(R.id.supplier_list_activity_tv);
        mCategory = (LinearLayout) findViewById(R.id.supplier_list_activity);
        mCategory.setOnClickListener(this);
        mSupplierListTitleTv = (TextView) findViewById(R.id.supplier_list_title_tv);
    }

    /***
     * 初始化popupwindow数据
     */
    private void initData() {

        mMenuData1 = new ArrayList<>();
        String[] menuStr1 = new String[]{"全部", "粮油", "衣服", "图书", "电子产品",
                "酒水饮料", "水果"};
        Map<String, String> map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<>();
            map1.put("name", menuStr1[i]);
            mMenuData1.add(map1);
        }

        mMenuData2 = new ArrayList<>();
        String[] menuStr2 = new String[]{"综合排序", "配送费最低"};
        Map<String, String> map2;
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<>();
            map2.put("name", menuStr2[i]);
            mMenuData2.add(map2);
        }

        mMenuData3 = new ArrayList<>();
        String[] menuStr3 = new String[]{"优惠活动", "特价活动", "免配送费",
                "可在线支付"};
        Map<String, String> map3;
        for (int i = 0, len = menuStr3.length; i < len; ++i) {
            map3 = new HashMap<>();
            map3.put("name", menuStr3[i]);
            mMenuData3.add(map3);
        }
    }


    /***
     * 初始化popupwindow
     */
    private void initPopMenu() {
        View popView = LayoutInflater.from(this).inflate(R.layout.popwin_supplier_list, null);
        mPopListview = (ListView) popView.findViewById(R.id.popwin_supplier_list_lv);
        popupMenu = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);
        popupMenu.setOutsideTouchable(true);
        popupMenu.setBackgroundDrawable(new BitmapDrawable());
        popupMenu.setFocusable(true);
        popupMenu.setAnimationStyle(R.style.popwin_anim_style);
        popupMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mProductTv.setTextColor(Color.parseColor("#5a5959"));
                mSortTv.setTextColor(Color.parseColor("#5a5959"));
                mCategoryTv.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupMenu.dismiss();
                    }
                });

        mMenuAdapter1 = new SimpleAdapter(this, mMenuData1, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter2 = new SimpleAdapter(this, mMenuData2, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mMenuAdapter3 = new SimpleAdapter(this, mMenuData3, R.layout.item_listview_popwin,
                new String[]{"name"}, new int[]{R.id.listview_popwind_tv});

        mPopListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupMenu.dismiss();
                switch (menuIndex) {
                    case 0:
                        String currentProduct = mMenuData1.get(i).get("name");
                        mSupplierListTitleTv.setText(currentProduct);
                        mProductTv.setText(currentProduct);
                        break;
                    case 1:
                        String currentSort = mMenuData2.get(i).get("name");
                        mSupplierListTitleTv.setText(currentSort);
                        mSortTv.setText(currentSort);
                        break;
                    case 2:
                        String currentAct = mMenuData3.get(i).get("name");
                        mSupplierListTitleTv.setText(currentAct);
                        mCategoryTv.setText(currentAct);
                        break;
                }
            }
        });


    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supplier_list_product:
                mProductTv.setTextColor(Color.parseColor("#39ac69"));
                mPopListview.setAdapter(mMenuAdapter1);
                popupMenu.showAsDropDown(mProduct, 0, 2);
                menuIndex = 0;
                break;
            case R.id.supplier_list_sort:
                mSortTv.setTextColor(Color.parseColor("#39ac69"));
                mPopListview.setAdapter(mMenuAdapter2);
                popupMenu.showAsDropDown(mSort, 0, 2);
                menuIndex = 1;
                break;
            case R.id.supplier_list_activity:
                mCategoryTv.setTextColor(Color.parseColor("#39ac69"));
                mPopListview.setAdapter(mMenuAdapter3);
                popupMenu.showAsDropDown(mCategory, 0, 2);
                menuIndex = 2;
                break;
        }
    }


}
