
$("#search_form").submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: "/search",
        type: "GET",
        data: {
            'input': $("#inputSearch").val(),
            'filter': $('option:selected').val()
        },
        dataType: "json",
        success: function (data) {
            let result = data['result'];
            $('#results').html('');
            result.forEach(function (product) {
                $('#results').append($(
                    '<div class="col-md-4">' +
                    '<div class="card services_card">' +
                    '<div class="card-body center">' +
                    '<div class="services_img">' +
                    '<img src="/views/assets/services/' + product['image'] + '" alt="service">' +
                    '</div>' +
                    '<div class="services_title">' +
                    product['name'] +
                    '</div>' +
                    '<div class="services_price">' +
                    product['price'] + 'Р' +
                    '</div>' +
                    '<div class="btn_pad">' +
                    '<button class="btn services_btn"' +
                    ' onclick="d(' + product['id'] + ')">' +
                    'Воспользоваться' +
                    '</button>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>'))
            });
        }
    });
});