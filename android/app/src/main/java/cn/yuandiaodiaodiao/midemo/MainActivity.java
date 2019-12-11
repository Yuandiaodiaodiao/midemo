package cn.yuandiaodiaodiao.midemo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class MainActivity extends AppCompatActivity {


    public Button button1;
    public WebView webView1;
    public EditText edittext;
//    public String url = "http://192.168.137.1:8888";
    public String url = "http://yuandiaodiaodiao.cn:8888";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button);

        webView1 = (WebView) findViewById(R.id.webView);
        webView1.setWebViewClient(new MyWebViewClient(webView1));


        edittext = (EditText) findViewById(R.id.textView);
        edittext.setText(url);
//使用jsbridge
//        webView1.registerHandler("submitFromWeb", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {
//                Toast.makeText(getApplicationContext(), "msg is " + data, Toast.LENGTH_SHORT).show();
//                function.onCallBack("submitFromWeb exe, response data from Java");
//            }
//        });
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

    @SuppressLint("SetJavaScriptEnabled")
    public class MyWebViewClient extends WebViewClient {

        public MyWebViewClient(WebView webView) {
//            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setJavaScriptEnabled(true);
//            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();

            try {
                url = URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (url.startsWith("xyz")) { // 如果是返回数据

                String[] payload = url.split("//");
                Toast.makeText(getApplicationContext(), "msg=" + payload[1], Toast.LENGTH_SHORT).show();
//                Gson json=new Gson();
//                json.toJson(payload[1]);
//                Toast.makeText(getApplicationContext(), "url=" + json.toString(), Toast.LENGTH_SHORT).show();

                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, request);
            }
        }

    }

}


