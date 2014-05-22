/**
 * Класс представления заказа
 * @type {*|void}
 */
var OrderView = Backbone.View.extend({
    tagName:'div',

    attributes:{
        style:"max-width: 300px; float: right; top: 5px; right: 15px; zIndex: 5"
    },

    render: function(){
        $(this.el).html(Mustache.render($('#orderTemplate').html()));
        this.$('#client').append(new ClientView(model.attributes.current.order.client).el);

        //Для каждой позиции текущего заказа выполненить добавление позиции на страницу (метод addItem)

        _.each(model.attributes.current.orderCollection.models, function(item){
            if (item.attributes.id){
                this.addItem(item);
            }
        },this);
    },

    initialize: function(){
        this.listenTo(model.attributes.current.orderCollection, 'add', this.addItem);
        this.render();
    },

    addItem: function(item){
        this.$('.tbody').append(new ItemOrderView({model: item}).el);
    }
});