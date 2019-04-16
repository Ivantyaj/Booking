

$(document).ready(function () {
    $('.header').height($(window).height());

    $(".navbar a").click(function () {
        $("body,html").animate({
            scrollTop: $("#" + $(this).data('value')).offset().top
        }, 1000)

    })

    $("#tabs").tabs();

    var colors = ['#007bff', '#28a745', '#333333', '#c3e6cb', '#dc3545', '#6c757d'];

    /* large line chart */
    var chLine = document.getElementById("chLine");
    var chartData = {
        labels: ["S", "M", "T", "W", "T", "F", "S"],
        datasets: [{
            data: [589, 445, 483, 503, 689, 692, 634],
            backgroundColor: 'transparent',
            borderColor: colors[0],
            borderWidth: 4,
            pointBackgroundColor: colors[0]
        },
            {
                data: [639, 465, 493, 478, 589, 632, 674],
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

    //РОЗА--------
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: "radar",
        data: {
            labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
            datasets: [{
                label: "My First Dataset",
                data: [65, 59, 90, 81, 56, 55, 40],
                fill: true,
                backgroundColor: "rgba(255, 99, 132, 0.2)",
                borderColor: "rgb(255, 99, 132)",
                pointBackgroundColor: "rgb(255, 99, 132)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgb(255, 99, 132)"
            }, {
                label: "My Second Dataset",
                data: [28, 48, 40, 19, 96, 27, 100],
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
    //РОЗА--------


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
                console.log("syccess");

            },
            error: function(data){
                console.log("errrrrrr" + data);
                $('#formFind').css("display","");
                $('#clientFindName').text("Вася Пупкин");
                $('#clientFindInf').text("Информация о васе пупкине");

            }})
    })

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