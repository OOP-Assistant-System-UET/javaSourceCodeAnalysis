<!DOCTYPE html>
<html>
<body>

<div id="demo">
    <h1>The XMLHttpRequest Object</h1>
    <button type="button" onclick="loadDoc()">Change Content</button>
</div>

<script>
    function loadDoc() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var obj = JSON.parse(this.responseText);
                document.getElementById("demo").innerHTML =
                    obj.classes[5].name;

            }
        };
        xhttp.open("GET", "filename.txt", true);
        xhttp.send();
    }
</script>

</body>
</html>