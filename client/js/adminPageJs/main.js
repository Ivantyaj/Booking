

$(document).ready(function () {
    $('.header').height($(window).height());

    $(".navbar a").click(function () {
        $("body,html").animate({
            scrollTop: $("#" + $(this).data('value')).offset().top
        }, 1000)

    })

    $("#tabs").tabs();

    $("#myModal").modal('show');



    var startDate = "0";
    var endDate = "0";

    console.log(startDate + endDate);

    updateGraph(startDate, endDate);

    $("#statsBtn").click(function () {


        startDate = $("#inputStartDate").val();
        endDate = $("#inputEndDate").val();

        if(startDate === "" && endDate === ""){
            startDate = "0";
            endDate = "0";
        }

        console.log(startDate);
        console.log(endDate);
        updateGraph(startDate, endDate);
    });



    //Время статистика

    $("#dateStatFrom").datetimepicker();
    $("#dateStatTo").datetimepicker({
        useCurrent: false
    });
    $("#dateStatFrom").on("dp.change", function (e) {
        $('#dateStatTo').data("DateTimePicker").minDate(e.date);
    });
    $("#dateStatTo").on("dp.change", function (e) {
        $('#dateStatFrom').data("DateTimePicker").maxDate(e.date);
    });
    //Время статистика

    //Форма клиент
    $('#btnFindClients').click(function(event) {
        event.preventDefault();

        $.ajax({
            type: 'POST', // метод отправки
            url: 'http://localhost:8080/hotel/booking/', // путь к обработчику
            //data:'',
            data: {
                "name": $('#clientName').val(),
                "email": $('#clientEmail').val(),
            },
            dataType: 'json',
            success: function(data){
                //console.log("syccess " + data);
            },
            error: function(data){
                console.log("errrrrrr" + data);
                $('#formFind').css("display","");
                $('#clientFindName').text("Вася Пупкин");
                $('#clientFindInf').text("Информация о васе пупкине");

            }})
    });

    $('#btnSendClient').click(function(event) {
        event.preventDefault();
        $.ajax({
            type: 'POST', // метод отправки
            url: 'http://localhost:8080/hotel/booking/', // путь к обработчику
            //data:'',
            // data: {
            //     "name": $('#clientName').val(),
            //     "email": $('#clientEmail').val(),
            // },
            dataType: 'json',
            success: function(data){
                //console.log("syccess " + data);
                console.log("syccess");

            },
            error: function(data){
                console.log("errrrrrr" + data);

            }})
    })
    //Форма клиент

})

var updateGraph = function (startDate, endDate) {
    $.ajax({
        type: 'GET', // метод отправки
        url: 'http://localhost:8080//hotel/clients/statsClient?' + "startDate=" + startDate + "&endDate=" + endDate, // путь к обработчику
        dataType: 'json',
        success: function(data){
            console.log("syccess");

            let headers = data[0];
            let data1 = data[1];
            let data2 = data[2];

            createGraph(headers, data1, data2);
        },
        error: function(data){
            console.log("errrrrrr" + data)
        }});

    $.ajax({
        type: 'GET', // метод отправки
        url: 'http://localhost:8080//hotel/clients/statsClient2?' + "startDate=" + startDate + "&endDate=" + endDate, // путь к обработчику
        dataType: 'json',
        success: function(data){
            console.log("syccess");

            let headers = data[0];
            let data1 = data[1];
            let data2 = data[2];

            createGraphRose(headers, data1, data2);
        },
        error: function(data){
            console.log("errrrrrr" + data)
        }});


}

var createGraph = function (labels, data1, data2) {
    var colors = ['#007bff', '#28a745', '#333333', '#c3e6cb', '#dc3545', '#6c757d'];

    /* large line chart */
    var chLine = document.getElementById("chLine");

    var chartData = {
        //labels: ["S", "M", "T", "W", "T", "F", "S"],
        labels: labels,
        datasets: [{
            data: data1,
            backgroundColor: 'transparent',
            borderColor: colors[0],
            borderWidth: 4,
            pointBackgroundColor: colors[0]
        },
            {
                data: data2,
                backgroundColor: colors[3],
                borderColor: colors[1],
                borderWidth: 4,
                pointBackgroundColor: colors[1]
            }]
    };

    if (chLine) {
        new Chart(chLine, {
            type: 'line',
            data: chartData,
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: false
                        }
                    }]
                },
                legend: {
                    display: false
                }
            }
        });
    }

}

var createGraphRose = function (labels, data1, data2){
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: "radar",
        data: {
            labels: labels,
            datasets: [{
                label: "My First Dataset",
                data: data1,
                fill: true,
                backgroundColor: "rgba(255, 99, 132, 0.2)",
                borderColor: "rgb(255, 99, 132)",
                pointBackgroundColor: "rgb(255, 99, 132)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgb(255, 99, 132)"
            }, {
                label: "My Second Dataset",
                data: data2,
                fill: true,
                backgroundColor: "rgba(54, 162, 235, 0.2)",
                borderColor: "rgb(54, 162, 235)",
                pointBackgroundColor: "rgb(54, 162, 235)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgb(54, 162, 235)"
            }]
        },
        options: {elements: {line: {tension: 0, borderWidth: 3}}}
    });
}

var authorisation = function () {
    var email = $('#email').val();
    var pwd = $('#pwd').val();

    console.log(email);
    console.log(pwd);

    //DELETE удалить заглушку, сделать запрос
    if(pwd === "" && email === ""){
        $("#myModal").modal('hide');
    } else {
        $.ajax({
            type: 'POST', // метод отправки
            url: 'http://localhost:8080/??????????', // Поменять !!!!
            data: {
                "email": email,
                "pwd": pwd,
            },
            dataType: 'json',
            success: function(data){
                $("#myModal").modal('hide');
            },
            error: function(data){
                console.log("pishov vid seda")
            }})
    }



}

