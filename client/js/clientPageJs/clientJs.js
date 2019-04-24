$(document).ready(function () {
    $('.header').height($(window).height());

    $(".navbar a").click(function () {
        $("body,html").animate({
            scrollTop: $("#" + $(this).data('value')).offset().top
        }, 1000)

    });

    $("#myModalClient").modal('show');

    var login = $('#email').val();

    //запрос на данные клиента по емаил // GET
    //Перенести в функцию авторизации
    $.ajax({
        type: 'GET', // метод отправки
        url: 'http://localhost:8080/******?' + "email=" + email, // путь к обработчику
        dataType: 'json',
        success: function (data) {
            console.log("syccess get clients data ", data);

        },
        error: function (data) {
            console.log("errrrrrr get clients data" + data)
        }
    });

    $('#saveClientsData').click(function () {

        var name = $('#clientName').val();
        var email = $('#clientEmail').val();

        console.log("hello save clients data", name, email);
    });


});

var authorisationClient = function () {
    var login = $('#email').val();
    var password = $('#pwd').val();

    var jsonData = JSON.stringify({login: login, password: password});
    console.log(login);
    console.log(password);

    var obj = {};
    obj.login = login;
    obj.password = password;

    //DELETE удалить заглушку, сделать запрос
    if (password === "" && login === "") {
        $("#myModalClient").modal('hide');
    } else {
        $.ajax({
            type: 'POST', // метод отправки
            url: 'http://localhost:8080/auth/login',
            // data: {
            // "login": login,
            // "password": password,
            // },
            data: jsonData,
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                $("#myModalClient").modal('hide');
                console.log("auth  ", data)
            },
            error: function (data) {
                if (data.status == 200) {
                    $("#myModalClient").modal('hide');
                } else {
                    alert("Вы еще не наш клиент :( ");
                }
                console.log("", data);

            }
        })
    }


};