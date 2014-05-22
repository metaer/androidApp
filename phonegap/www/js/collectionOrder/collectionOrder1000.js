//$('#save').on('click',function(){
//    model.saveGen();
//});

$(function(){
    $('.send-button').on('click',function(){
        var orderDataForServer = createDataForServer();
        sendData(orderDataForServer);
    });
});

function createDataForServer(){

    //Пока что пересылаем один заказ, дальше сразу все, которые в очереди стоят.

    var data = {};
    data.idClient = 5;
    data.comment = 'test comment';
    data.idOrder = 102; //TODO передавать реальный айдишник
    data.items = [
        {
            id: 47,
            groupId: 3,
            name: 'пиво Балтика 7',
            price: 500,
            quantity: 4
        },
        {
            id: 50,
            groupId: 3,
            name: 'пиво Efes',
            price: 500,
            quantity: 4
        },
        {
            id: 56,
            groupId: 3,
            name: 'пиво Старый мельник',
            price: 500,
            quantity: 4
        }
    ];

    return data;
}

function sendData(data){
    $.ajax({
        type: "POST",
        url: "http://stclient:1500",
        dataType: "json",
        cache:false,
        timeout: 10000,
        async: true,
        data: data,
        error: function(JqXhr, TextStatus, ErrorThrown)
        {
            alert(TextStatus);
        }

    }).done(function(result) {
        alert(result.res);
    });
}

var gData = {
    clients: [
        {
            id: 1,
            name: 'Пупкин В.В. ИП',
            address: 'Кормиловка,Павших Коммунаров,15',
            debt: 7000
        },
        {
            id: 2,
            name: 'Тимофеев А.А. ИП',
            address: 'Горки, ул. Ленина, 15',
            debt: 5300
        },
        {
            id: 3,
            name: 'Новый клиент',
            address: '',
            debt: 0
        }
    ],
    orders: [],
    store: [
        {id: 1, name: 'Святой Георгий', price: 40, quantity: 53, groupId: 2},
        {id: 2, name: 'Parlament', price: 50, quantity: 30, groupId: 2},
        {id: 3, name: 'Зеленая марка', price: 80, quantity: 20, groupId: 1},
        {id: 4, name: 'Очаковское', price: 60, quantity: 20, groupId: 1}
    ],
    groups: [
        {id: 2, groupName: 'Алкоголь'},
        {id: 1, groupName: 'Табачные изделия'}
    ]
};

for (var i = 3; i<=50; i++){
    gData.groups[i-1] = {id: i, groupName:'Тестовая группа'}
}

for (i = 5; i<=1000; i++){
    gData.store[i-1] = {id: i, name: 'тестовый товар', price: 4000, quantity: 100, groupId:(div(i,20) + 1)}
}

var model = new Model({id: 1});

var groups = new GroupsView();

$('#divgroups').append(groups.el);

orderView = new OrderView();
$('#main').append(orderView.el);

function div(val, by){
    return (val - val % by) / by;
}