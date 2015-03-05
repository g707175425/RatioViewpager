# RatioViewpager
可以按比例显示上一页的一部分和下一页的一部分的Viewpager
Xml中的使用:

	<com.gys.viewpager.MyRatioPagerContainer
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#CCC">
        <com.gys.viewpager.MyRatioViewPager
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal" />
    	</com.gys.viewpager.MyRatioPagerContainer>
	
代码中:

		MyRatioPagerContainer mContainer = (MyRatioPagerContainer) findViewById(R.id.container);
        ViewPager pager = mContainer.getViewPager();
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
	


QQ:707175425