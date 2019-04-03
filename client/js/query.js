$('#idSearch').click(function(){

    var startDate = $('#start-date').val();
    var endDate = $('#end-date').val();
    var clients = $('#clientCount').val();

    console.log(startDate);
    console.log(endDate);
    console.log(clients);



    var url = "search.html?" + "startDate=" + startDate + "&endDate=" + endDate + "&clients=" + clients;
    $(location).attr('href',url);
});