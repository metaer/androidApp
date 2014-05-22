/**
 * Класс представления информации о клиенте
 * @type {*|void}
 */
var ClientView = Backbone.View.extend({

    events: {
        "change .select" : "afterChangeClient"
    },

    render: function(){

        var params = {};

        if (model.attributes.current.order.attributes.idClient != undefined){
            var idClient = model.attributes.current.order.attributes.idClient;
        }

        if (idClient != undefined){
            var client = _.findWhere(model.attributes.clients,{id: idClient});
            params.client = client;
        }

        params.clients = model.attributes.clients;

        params.clients = this.setSelectedAttrsForClients(params.clients,idClient);

        this.$el.html(Mustache.render($('#clientTemplate').html(),params));
        this.$('select.select').selectmenu();
        this.$('select.select').selectmenu('refresh',true);
    },

    setSelectedAttrsForClients: function(clients,idClient){
        _.each(clients, function(client) {
                client.selected = (client.id == idClient);
        });

        return clients;
    },

    initialize: function(){
        this.listenTo(model, 'change', this.render);
        this.render();
    },

    afterChangeClient: function(){
        var selectValue = this.$('select.select').val();

        model.attributes.current.order.attributes.idClient = (selectValue == "empty") ? undefined : selectValue*1;
        this.render();
        model.saveGen();
    }

});