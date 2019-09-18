package com.example.win.easy;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.win.easy.factory.ListenerFactory;
import com.example.win.easy.repository.db.data_object.SongDO;
import com.example.win.easy.repository.db.data_object.SongListDO;
import com.example.win.easy.repository.db.data_object.SongXSongListDO;
import com.example.win.easy.tool.SongList;
import com.example.win.easy.value_object.SongVO;
import com.example.win.easy.view.OnClickFunc;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static androidx.test.InstrumentationRegistry.getContext;

public class Dashboard extends QMUILinearLayout  {

    private QMUITabSegment tabSegment;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<View> pages=new ArrayList<>();
    private List<QMUITabSegment.OnTabSelectedListener> tabListeners =new ArrayList<>();
    private LiveData<List<SongDO>> allSongs;
    private LiveData<List<SongListDO>> allSongLists;
    private LiveData<List<SongXSongListDO>> allRelation;

    @Getter private int tabSegmentBackgroundColor;
    @Getter private int viewPagerBackgroundColor;
    @Getter private int tabSelectedTextColor;
    @Getter private int tabUnselectedTextColor;
    @Getter private boolean hasTabIndicator;
    @Accessors private int itemBackgroundColor;

    @Setter ListenerFactory listenerFactory;

    public Dashboard(Context context) {
        super(context);
        init(context,null,0);
    }

    public Dashboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public Dashboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    /**
     * 执行整个视图的初始化工作，包括初始化ViewPager，初始化TabSegment，初始化样式，通知渲染等等
     * @param context 视图上下文
     * @param attrs 属性集
     * @param defStyleAttr Style属性
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        //从Xml中提取视图
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.dash_board,this);

        //初始化ViewPager
        initViewPager();

        //初始化TabSegment
        initTabSegment();

        //初始化样式等属性
        initAttrs(context,attrs,defStyleAttr);

        //通知渲染
        invalidate();
    }

    /**
     * 初始化TabSegment
     */
    private void initTabSegment(){
        tabSegment=findViewById(R.id.tab_segment);
        tabSegment.setupWithViewPager(viewPager,false);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager(){
        viewPager=findViewById(R.id.view_pager);
        pagerAdapter=new GroupListViewAsPagePagerAdapter();
        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * 获取Xml中的属性，并将其应用的到视图上
     * @param context 视图上下文
     * @param attrs Xml中得到的属性集
     * @param defStyleAttr Style属性
     */
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.Dashboard,defStyleAttr,0);

        //获取并配置属性
        try {
            setTabSegmentBackgroundColor(typedArray.getColor(R.styleable.Dashboard_tab_segment_background_color,getResources().getColor(R.color.default_tab_segment_background_color)));
            setViewPagerBackgroundColor(typedArray.getColor(R.styleable.Dashboard_view_pager_background_color,getResources().getColor(R.color.default_view_pager_background_color)));
            setTabSelectedTextColor(typedArray.getColor(R.styleable.Dashboard_tab_selected_text_color,getResources().getColor(R.color.default_tab_selected_text_color)));
            setTabUnselectedTextColor(typedArray.getColor(R.styleable.Dashboard_tab_unselected_text_color,getResources().getColor(R.color.default_tab_unselected_text_color)));
            setHasTabIndicator(typedArray.getBoolean(R.styleable.Dashboard_has_tab_indicator,getResources().getBoolean(R.bool.default_has_tab_indicator)));
//            setItemBackgroundColor(typedArray.getColor(R.styleable.DashBoard_item_background_color,getResources().getColor(R.color.default_item_background_color)));
        }finally {
            //刷新TypeArray，供其他线程使用
            typedArray.recycle();
        }
    }

    public void setTabSegmentBackgroundColor(int tabSegmentBackgroundColor) {
        this.tabSegmentBackgroundColor = tabSegmentBackgroundColor;
        tabSegment.setBackgroundColor(tabSegmentBackgroundColor);
    }

    public void setViewPagerBackgroundColor(int viewPagerBackgroundColor){
        this.viewPagerBackgroundColor=viewPagerBackgroundColor;
        viewPager.setBackgroundColor(viewPagerBackgroundColor);
    }

    public void setTabSelectedTextColor(int tabSelectedTextColor){
        this.tabSelectedTextColor =tabSelectedTextColor;
        tabSegment.setDefaultSelectedColor(tabSelectedTextColor);
    }

    public void setTabUnselectedTextColor(int tabUnselectedTextColor){
        this.tabUnselectedTextColor=tabUnselectedTextColor;
        tabSegment.setDefaultNormalColor(tabUnselectedTextColor);
    }

    public void setHasTabIndicator(boolean hasTabIndicator){
        this.hasTabIndicator=hasTabIndicator;
        tabSegment.setHasIndicator(hasTabIndicator);
    }
    public void setItemBackgroundColor(int itemBackgroundColor){
        this.itemBackgroundColor=itemBackgroundColor;
    }

    /**
     * 根据传入的List<歌单>及指定的用途，设置整个视图，包括更新内容，重新设置监听等等
     * @see DashboardType
     * @param lists 传入的List<歌单>
     * @param dashboardType 用于指定{@code Dashboard}的用途，见{@link DashboardType}
     */
    public void setup(List<SongList> lists, DashboardType dashboardType){
        //清除
        clear();

        //为每个Song List生成一个Tab、一个GroupListView
        for(SongList songList:lists){
            //生成Tab
            tabSegment.addTab(
                    getTab(songList)
            );
            //设置Tab切换监听
            tabSegment.addOnTabSelectedListener(
                    getTabListener(lists,dashboardType)
            );
            //设置相应的GroupListView列表视图
            pages.add(
                    getGroupListView(songList,dashboardType)
            );
        }

        //同时内容更改，刷新内容
        notifyContentChange();
    }

    /**
     * 向DashBoard传递数据
     * @param allSongs
     * @param allSongLists
     * @param allRelation
     */
    public void setData(LiveData<List<SongDO>> allSongs, LiveData<List<SongListDO>> allSongLists, LiveData<List<SongXSongListDO>> allRelation){
        this.allSongs=allSongs;
        this.allSongLists=allSongLists;
        this.allRelation=allRelation;
    }

    /**
     * 清除现有所有内容
     */
    private void clear(){
        //移除监听器
        clearListeners();

        //重置Tab
        tabSegment.reset();

        //清除所有Page
        pages.clear();
    }

    /**
     * 将TabSegment上自行添加的Listener逐个清除
     */
    private void clearListeners(){
        //逐个移除
        for(QMUITabSegment.OnTabSelectedListener listener: tabListeners)
            tabSegment.removeOnTabSelectedListener(listener);
        //清空列表
        tabListeners.clear();
    }
    /**
     * 通知变更，重新渲染视图
     */
    private void notifyContentChange(){
        //通知页面变更
        pagerAdapter.notifyDataSetChanged();

        //通知Tab变更
        tabSegment.notifyDataChanged();
    }
    /**
     * 根据歌单生成一个Tab
     * @param songList 歌单
     * @return 生成的Tab
     */
    private QMUITabSegment.Tab getTab(SongList songList){
        //歌单的名字作为Tab显示的内容
        return new QMUITabSegment.Tab(songList.getName());
    }

    /**
     * 为一个歌单生成一个GroupListView，搜索和切换歌单时，监听器不同
     * @param songList 输入的歌单
     * @param dashBoardType 搜索还是切换歌单
     * @return 生成的GroupListView
     */
    private QMUIGroupListView getGroupListView(SongList songList, DashboardType dashBoardType){
        //获取这个歌单的歌曲列表
        List<SongDO> songDOs =songList.getSongDOs();

        //用于返回的GroupListView
        QMUIGroupListView groupListView=new QMUIGroupListView(getContext());
        QMUIGroupListView.Section section=QMUIGroupListView.newSection(getContext());

        //为这个歌单每一首歌构建一个Item，并把它们放入同一个Section中
        for(SongDO songDO : songDOs){
            //构建Item
            QMUICommonListItemView commonListItemView=groupListView.createItemView(
                    null,
                    songDO.getName(),
                    null,
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_NONE
            );
            commonListItemView.setBackgroundColor(getResources().getColor(R.color.app_color_blue_2));
            //将Item放入Section中
            section.addItemView(commonListItemView,getItemListener(songDO,dashBoardType));
        }

        //将这个Section放入GroupListView中（每个GroupListView只设置一个Section，虽然实际上允许放多个Section）
        section.addTo(groupListView);

        return groupListView;
    }

    /**
     * 根据搜索还是切换歌单，构建Tab监听，同时将监听器保存下来，用于后续清除
     * @param appearanceLists 切换歌单时，监听器需要这个参数，表示所有出现过某一首歌曲的歌单
     * @param dashBoardType 搜索还是切换歌单
     * @return 构建的Tab监听
     */
    private QMUITabSegment.OnTabSelectedListener getTabListener(List<SongList> appearanceLists, DashboardType dashBoardType){
        QMUITabSegment.OnTabSelectedListener listener;
        switch (dashBoardType){
            case SelectingSong: listener=listenerFactory.create();
                break;
            case SwitchSongList:listener=listenerFactory.create(appearanceLists);
                break;
            default: listener=null;
        }

        //保留监听器，用于后续清除
        tabListeners.add(listener);

        return listener;
    }

    /**
     * 根据搜索还是切换歌单，构建Item监听
     * @param songDO 这个Item所对应的歌曲
     * @param dashBoardType 搜索还是切换歌单
     * @return 构建的Item监听
     */
    private OnClickListener getItemListener(SongDO songDO, DashboardType dashBoardType){
        switch (dashBoardType){
            case SelectingSong: return listenerFactory.create(songDO,allSongs.getValue(),allSongLists.getValue(),allRelation.getValue());
            case SwitchSongList:return listenerFactory.create(songDO);
            default: return null;
        }
    }

    /**
     *
     * 用于表示{@link Dashboard}的不同用途，作为{@link #setup(List, DashboardType)}的参数暴露给外界
     */
    public enum DashboardType {
        SelectingSong,//选择歌曲
        SwitchSongList//切换歌单
    }

    class GroupListViewAsPagePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //获取相应视图
            View view=pages.get(position);

            //构造视图参数
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            //向容器中添加视图
            container.addView(view,params);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //从容器中移除视图
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            //强制每次刷新视图
            return POSITION_NONE;
        }

    }

}

class SearchBoard{

    void search(List<Character> sequence){

    }

}

class SwitchTab extends QMUILinearLayout{
    @BindView(R.id.tab_segment) QMUITabSegment tabSegment;
    @BindView(R.id.view_pager) ViewPager viewPager;
    private PagerAdapter pagerAdapter=new GroupListViewAsPagePagerAdapter();

    public SwitchTab(Context context) {
        super(context);

    }
}
class GroupListViewAsPagePagerAdapter extends PagerAdapter {

    private List<QMUIGroupListView> pages=new ArrayList<>();

    public void setPages(List<List<SongVO>> songListInPages,OnClickFunc<SongVO> songOnClickFunc){
        pages.clear();
        for (List<SongVO> songsInAPage:songListInPages)
            pages.add(toPage(songsInAPage,songOnClickFunc));
        notifyDataSetChanged();
    }

    private QMUIGroupListView toPage(List<SongVO> songsInPage,OnClickFunc<SongVO> songOnClickFunc){
        //用于返回的GroupListView
        QMUIGroupListView page=new QMUIGroupListView(getContext());
        setItemsIn(page,songsInPage,songOnClickFunc);
        return page;
    }

    private void setItemsIn(QMUIGroupListView page,List<SongVO> songsInPage,OnClickFunc<SongVO> songOnClickFunc){
        QMUIGroupListView.Section section=QMUIGroupListView.newSection(getContext());

        //为这个歌单每一首歌构建一个Item，并把它们放入同一个Section中
        for(SongVO song : songsInPage){
            //构建Item
            QMUICommonListItemView songItem=item(song,page);
            View.OnClickListener onClickListener=v-> songOnClickFunc.onclick(song,songItem);
            //将Item放入Section中
            section.addItemView(songItem,onClickListener);
        }

        //将这个Section放入GroupListView中（每个GroupListView只设置一个Section，虽然实际上允许放多个Section）
        section.addTo(page);
    }

    private QMUICommonListItemView item(SongVO song,QMUIGroupListView page){
        return page.createItemView(
                null,
                song.getName(),
                null,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE
        );
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //获取相应视图
        View view=pages.get(position);

        //构造视图参数
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //向容器中添加视图
        container.addView(view,params);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //从容器中移除视图
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //强制每次刷新视图
        return POSITION_NONE;
    }

}