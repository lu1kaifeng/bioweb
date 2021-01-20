window.onload = function () {
    var navbarFixed = document.getElementById("navbar-fixed");

    window.addEventListener('scroll', function () {
        if (document.documentElement.scrollTop || document.body.scrollTop >= 202) {
            navbarFixed.className = "navbar navbar-inverse navbar-fixed-top";

            var containerChange = document.getElementById("container-change");
                containerChange.style.marginTop = "72px";
        }
        else {
            navbarFixed.className = "navbar navbar-inverse";

            var containerChange = document.getElementById("container-change");
                containerChange.style.marginTop = "0";
        }
    });

    var in_width = window.screen.width;
    var in_height = window.screen.height;

    var num = 80;

    if (in_width > 1022) {
        num = 80;
    }
    else if (in_width > 780) {
        num = 65;
    }
    else if (in_width > 720) {
        num = 50;
    }
    else if (in_width > 450) {
        num = 35;
    }
    else {
        num = 30;
    }

    function Circle() {
        this.r = Math.random() * 3.7 + 3;

        this.color = 'rgba(202,202,202,0.85)';

        this.x = Math.random() * in_width;
        this.y = Math.random() * in_height;

        this._mx = Math.random() * 2.5 - 1.2;
        this._my = Math.random() * 1.5 - 0.7;
    }

    function drawCircle(con, _circle) {
        con.beginPath();
        con.arc(_circle.x, _circle.y, _circle.r, 0, 2 * Math.PI);
        con.closePath();

        con.fillStyle = _circle.color;
        con.fill();
    }

    function drawLine(con) {
        var _dx;
        var _dy;
        var dx;
        var dy;

        for (var m = 0; m < _array.length; m++) {
            _dx = _array[m].x;
            _dy = _array[m].y;

            for (var n = 0; n < _array.length; n++) {
                dx = _array[n].x;
                dy = _array[n].y;

                var _distance = Math.sqrt(Math.pow(_dx - dx, 2) + Math.pow(_dy - dy, 2));

                if (_distance < 150) {
                    con.beginPath();
                    con.moveTo(_array[m].x, _array[m].y);
                    con.lineTo(_array[n].x, _array[n].y);
                    con.closePath();

                    con.strokeStyle = 'rgba(202, 202, 202, 0.85)';
                    con.stroke();
                }
            }
        }
    }

    var canvas = document.getElementById('canvas');

    canvas.width = in_width;
    canvas.height = in_height;

    var con = canvas.getContext('2d');

    var _array = new Array(num);

    for (var i = 0; i < _array.length; i++) {
        _array[i] = new Circle();

        if (_array[i].x - _array[i].r <= 0) {
            _array[i].x = _array[i].r;
        }

        if (_array[i].x + _array[i].r >= in_width) {
            _array[i].x = in_width - _array[i].r;
        }

        if (_array[i].y - _array[i].r <= 0) {
            _array[i].y = _array[i].r;
        }

        if (_array[i].y + _array[i].r >= in_width) {
            _array[i].y = in_width - _array[i].r;
        }
    }

    function auto_change() {
        for (var i = 0; i < _array.length; i++) {
            _array[i].x = _array[i].x + _array[i]._mx;
            _array[i].y = _array[i].y + _array[i]._my;

            if (_array[i].x - _array[i].r <= 0) {
                _array[i]._mx = -_array[i]._mx;
            }
            if (_array[i].x + _array[i].r >= in_width) {
                _array[i]._mx = -_array[i]._mx;
            }
            if (_array[i].y - _array[i].r <= 0) {
                _array[i]._my = -_array[i]._my;
            }
            if (_array[i].y + _array[i].r >= in_height) {
                _array[i]._my = -_array[i]._my;
            }
        }
    }

    function _draw() {
        con.clearRect(0, 0, in_width, in_height);
        for (var i = 0; i < _array.length; i++) {
            drawCircle(con, _array[i]);
        }
        drawLine(con);
    }

    window.setInterval(function () {
        _draw();
        auto_change();
    }, 25);

    var containerChange = document.getElementById("container-change");
        containerChange.style.height = window.screen.height + "px";
    
    var boxChange = document.getElementById("box-change");
        boxChange.style.height = window.screen.height / 5 + "px";

    var buttonChange = document.getElementById("button-change");
        buttonChange.addEventListener('click', function () {
            var dis = document.getElementById("container-dis").getBoundingClientRect().top - 52;

            // for (var set = 0; set <= dis; set++) {
            //     window.document.body.scrollTop = set;
            //     window.document.documentElement.scrollTop = set;
            // }

            // var set = 0;

            // var timer = window.setInterval(function () {
            //     window.document.body.scrollTop = set;
            //     window.document.documentElement.scrollTop = set;

            //     set += 7;

            //     if (set > dis) {
            //         window.clearInterval(timer);
            //     }
            // }, 2);

            var set = 0;

            function timerFun() {
                var timer = window.setTimeout(function () {
                    window.document.body.scrollTop = set;
                    window.document.documentElement.scrollTop = set;

                    set += 7;

                    if (set > dis) {
                        window.clearTimeout(timer);
                    }
                    else {
                        timerFun();
                    }
                }, 2);
            };

            timerFun();
        });
};