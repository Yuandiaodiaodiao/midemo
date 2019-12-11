function buttonCall(){

    // var button=document.getElementById("button1")
    // button.innerHTML=button.innerHTML+"1"
    var a=document.getElementById("input1").value;  
    
    var data=a;
    window.WebViewJavascriptBridge.callHandler(
        'submitFromWeb'
        ,data
        , function(responseData) {
            bridgeLog('来自Java的回传数据： ' + responseData);
        }
    );
    

}
