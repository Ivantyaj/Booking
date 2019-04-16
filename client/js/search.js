
$(document).ready(function() {
    console.log(document.location.search);
    console.log(window.location.protocol);

    console.log(getUrlParameter('startDate'));
    console.log(getUrlParameter('endDate'));

    var startDate = getUrlParameter('startDate');
    var endDate = getUrlParameter('endDate');
    var clients = getUrlParameter('clients');

    $.ajax({
        type: 'GET', // метод отправки
        url: 'http://localhost:8080/hotel/booking/', // путь к обработчику
        //data:'',
        data: {
            "startDate": startDate,
            "endDate": endDate,
            "clients": clients,
        },
        dataType: 'json',
        success: function(data){
            //console.log("syccess " + data);
            console.log("syccess");
            console.log(data);

            console.log(data['URL1']);
            console.log(data['URL3']);

            //$('#idRoom1').attr("height","234");
            $('#idRoom1').attr("src",data['URL1']);
            //$('#idRoom1').height(234);
            //$('#idRoom1').attr("width","350");


            $('#idRoom2').attr("src",data['URL2']);
            //$('#idRoom2').attr("width","350");
            $('#idRoom2').attr("height","234");

            $('#idRoom3').attr("src",data['URL3']);
            //$('#idRoom3').attr("width","350");
            $('#idRoom3').attr("height","234");

            //console.log(JSON.parse(data));
            // $.each(data, function(key, val){
            //     console.log(key);
            //     console.log(val);
            // });
            // for (let i=0; i<json.length; i++) {
            //     console.log(json[i].text);
            //
            // }
        },
        error: function(data){
            console.log("errrrrrr" + data)
            // $.each(data, function(key, val){
            //     console.log(key);
            //     console.log(val);
            // });
            // for (let i=0; i<json.length; i++) {
            //     console.log(json[i].text);
            // console.log(data);
            //}}
        }})
});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};