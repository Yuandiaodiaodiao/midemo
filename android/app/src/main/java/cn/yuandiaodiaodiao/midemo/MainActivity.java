package cn.yuandiaodiaodiao.midemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    public Button button1;
    public WebView webView1;
    public EditText edittext;
    //    public String url = "http://192.168.137.1:8888";
    public String url = "http://yuandiaodiaodiao.cn:8888";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);

        webView1 = (WebView) findViewById(R.id.webView);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.addJavascriptInterface(new JSNative(this, webView1), "JSnative");

        edittext = (EditText) findViewById(R.id.textView);
        edittext.setText(url);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //刷新网页
                url = edittext.getText().toString();
                webView1.loadUrl(url);
                Toast.makeText(getApplicationContext(), "reload", Toast.LENGTH_SHORT).show();
            }
        });

        webView1.loadUrl(url); //加载默认链接
    }

    @Override
    protected void onResume() {
        //恢复时重加载
        super.onResume();
        webView1.loadUrl(url);
    }


    public class JSNative {
        private WebView webView;

        private JSNative(Context context, WebView webView) {
            this.webView = webView;
        }

        @JavascriptInterface
        public void say(String content) {
            //收到say
            Toast.makeText(getApplicationContext(), "js say=" + content, Toast.LENGTH_SHORT).show();
            //返回调用js
            String call = "javascript:java2js(\' receive \')";
            new Handler(Looper.getMainLooper()).post(() -> webView.loadUrl(call));
        }

    }
}


