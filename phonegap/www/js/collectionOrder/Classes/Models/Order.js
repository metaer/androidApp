/**
 * Класс модели заказа
 * @type {*|void}
 */
var Order = Backbone.Model.extend({

    localStorage: new Backbone.LocalStorage('order'),

    initialize: function(count, order){

        if (order == undefined){
            this.attributes.id = count + 1;
            this.attributes.status = 'CREATED';
            this.attributes.items = new Data({});
        }
        else{
            this.attributes.items = new Data(order.items);
            this.attributes.idClient = order.idClient;
        }

    }
});