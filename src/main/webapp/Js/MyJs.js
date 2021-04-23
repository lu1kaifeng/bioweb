function turnback(obj) {
    var col = document.getElementById(obj);
    col.style.backgroundColor = "#FFFFFF";
}

function change(num) {
    var all = document.getElementsByClassName("AllDatabase");
    var bor = document.getElementsByClassName("navigation");
    for (i = 0; i < all.length; i++) {
        all[i].style.display = "none";
        bor[i].style.borderBottom = "1.5px solid #C9F2FF";
        if (i == num - 1) {
            all[i].style.display = "block";
            bor[i].style.borderBottom = "2px solid #09f";
        }
    }
}