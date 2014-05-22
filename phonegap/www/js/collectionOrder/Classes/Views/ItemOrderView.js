/**
 * Представление одной позиции заказа
 * @type {*|void}
 */
var ItemOrderView = Backbone.View.extend({
    tagName: "tr",
    className: "trOrder",

    render: function(){
        var dataToTemplate = this.model.toJSON();
        dataToTemplate.sum = dataToTemplate.price * dataToTemplate.quantity;
        $(this.el).html(Mustache.render($('#itemOrderTemplate').html(), dataToTemplate));
        return this;
    },

    rerender: function(){
        this.$('.quantity').html(this.model.attributes.quantity);
        this.$('.sum').html(this.model.attributes.quantity * this.model.attributes.price);
    },

    initialize: function(){
        this.listenTo(this.model, 'change', this.rerender);
        this.listenTo(model.attributes.current.orderCollection, 'remove', this.removeItem);
        this.render();
    },

    removeItem: function(item, options){

        if (item === this.model){
            this.remove();
            delete item;
            delete this;
        }
    }
});