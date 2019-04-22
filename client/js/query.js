$('#idSearch').click(function(){

    var startDate = $('#start-date').val();
    var endDate = $('#end-date').val();
    var clients = $('#clientCount').val();

    console.log(startDate);
    console.log(endDate);
    console.log(clients);



    var url = "search.html?" + "startDate=" + startDate + "&endDate=" + endDate + "&clients=" + clients + "&price=";
    $(location).attr('href',url);
});

$('#btnSubscribe').click(function () {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = $('#inputSubEmail').val();
    if(reg.test(address) == false)
    {
        alert('Введите корректный e-mail');
        return false;
    } else {
        $.ajax({
            type: 'POST', // метод отправки
            url: 'http://localhost:8080/??????????', // Поменять !!!!
            data: {
                "email": address,
            },
            dataType: 'json',
            success: function(data){
                alert('Вы подписались на новости\nПроверьте вашу почту');
            },
            error: function(data){
                alert('Не возможно подписаться в данный момент');
                console.log("pishov vid seda");
            }})
    }
});