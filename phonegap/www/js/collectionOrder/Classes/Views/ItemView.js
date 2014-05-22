/**
 * Представление модели Item (товарной позиции)
 * @type {*|void}
 */
var ItemView = Backbone.View.extend({
    tagName: "tr",

    events: {
        "change .nomQuantity" : "changeValue",
        "keyup .nomQuantity" : "changeValue",

        "click .increase" : "increaseValue",
        "click .decrease" : "decreaseValue"
    },

    render: function(){

        //Выцепить количество этого товара в текущем заказе.

        var idItem = this.model.get('id');

        //Позиция из текущего заказа
        var currentOrderItem = _.findWhere(model.attributes.current.orderCollection.models, {id: idItem});

        var params = this.model.toJSON();

        if (currentOrderItem != undefined){
            params.currentOrderQuantity = currentOrderItem.attributes.quantity;
        }

        $(this.el).html(Mustache.render($('#itemTemplate').html(), params));
        return this;
    },

    initialize: function(){
        this.render();
    },

    /**
     * Получаем товарную позицию из коллекции состава заказа
     * @returns {*}
     */
    getItemOrder: function(){
        var idCurrentModel = this.model.id;
        return _.find(model.attributes.current.orderCollection.models,function(model){
            return (model.attributes.id == idCurrentModel)
        });
    },

    /**
     * Метод вызывается при изменении поля количество в позиции товара в номенклатурной группе
     */
    changeValue: function(){

        //Если объекта с данной номенклатурой нет в коллекции заказа:
            //клонируем
            //изменяем кол-во
            //Добавляем в коллекцию
        //Иначе (если есть)
            //изменяем кол-во

        var itemOrder = this.getItemOrder();

        if (itemOrder === undefined){
            itemOrder = this.model.clone();
            itemOrder.attributes.quantity = this.$('.nomQuantity').val();
            if (itemOrder.attributes.quantity > 0){
                model.attributes.current.orderCollection.add(itemOrder);
            }
        }
        else{
            var nomQuantity = this.$('.nomQuantity').val();
            if (nomQuantity <= 0){
                //удалить модель из коллекции заказа
                model.attributes.current.orderCollection.remove(itemOrder);
                this.$('.nomQuantity').val('');
            }
            else{
                itemOrder.set({quantity: nomQuantity});
            }
        }

        model.saveGen();

    },

    increaseValue: function(){
        var val = this.$('.nomQuantity').val();
        val++;
        this.$('.nomQuantity').val(val);
        this.changeValue();
    },

    decreaseValue: function(){
        var val = this.$('.nomQuantity').val();
        if (val > 0){
            val--;
            this.$('.nomQuantity').val(val);
            this.changeValue();
        }
    }
});