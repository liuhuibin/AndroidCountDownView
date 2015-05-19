# AndroidCountDownView
An android custome view which can count down time.

## Usage

* add widget in xml

```xml
<com.liuhb.androidcountdownview.view.CountDownView
        android:id="@+id/time"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:paddingTop="10dp"
        jeremy:mills="10000"/> <!--倒计时的时长(单位毫秒)-->
```

* add code below in Activity

```Java
mTime = (CountDownView) findViewById(R.id.time);
mTime.setOnFinishedListener(new CountDownView.OnFinishedListener() {
    @Override
    public void onFinished() {  //计时结束后执行
        Toast.makeText(MainActivity.this, "计时结束", Toast.LENGTH_SHORT).show();
    }
});
//设置计时的时长(单位毫秒)
mTime.setMills(0 * 24 * 60 * 60 * 1000 + 0 * 60 * 60 * 1000 + 0 * 60 * 1000 + 3 * 1000 + 200);
```

## End
