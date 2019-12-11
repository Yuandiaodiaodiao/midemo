function jscalljava() {

    let a = document.getElementById("input1").value;
    JSnative.say(a);


    // var frameDiv = document.createElement("iframe");//创建一个标签
    // var message = {"text": a}
    // frameDiv.setAttribute('style','width: 0px; height: 0px;');
    // frameDiv.src = "xyz://" + JSON.stringify(message)
    // var bodyFa = document.getElementById("container1");
    // bodyFa.appendChild(frameDiv);
    // setTimeout(()=>{bodyFa.removeChild(frameDiv)},100)


}

function java2js(msg) {
    let b=document.getElementById("button1")
    b.innerHTML=msg
}
