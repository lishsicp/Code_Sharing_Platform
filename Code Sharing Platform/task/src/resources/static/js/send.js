function send() {
    var time = parseInt(document.getElementById("time_restriction").value);
    if(document.getElementById("time_restriction").value.length == 0) {
        time = 0;
    }

    var views = parseInt(document.getElementById("views_restriction").value);
    if(document.getElementById("views_restriction").value.length == 0) {
        views = 0;
    }

    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": time,
        "views": views
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
      alert("Success!");
    }
}