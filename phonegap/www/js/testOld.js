

var arrGroups = [

    {
    groupName: 'Табачные изделия',
    data:
        [
            {itemName:'Беломор канал',price: 30, balance: 5, toOrder: 20},
            {itemName:'Товар 4',price: 900, balance: 62, toOrder: 20}
        ]
    },
    
    {
        groupName: 'Алкоголь',
        data:
            [
                {itemName:'Товар 1',price: 430, balance: 3, toOrder: 20},
                {itemName:'Товар 2',price: 200, balance: 6, toOrder: 20}
            ]
    }
];

_.each(arrGroups,function(element){
    element.data[0].first = 1;
    element.count = element.data.length;
});

var Item = Backbone.Model.extend({});

var ItemView = Backbone.View.extend({
    tagName: "li",

    render: function(){
        var html = Mustache.render($('#item').html(), this.model.toJSON());
        $(this.el).html(html);
        return this;
    },

    initialize: function(){
        this.render();
    }

});

var Group = Backbone.Model.extend({});

var GroupView = Backbone.View.extend({

    tagName: "div",

    render: function(){
        $(this.el).html(Mustache.render($('#group').html(), this.model.toJSON()));
        argData = this.model.toJSON().data;
        $(this.el).append(new DataView(argData).el);
        return this;
    },

    initialize: function(){
        this.render();
    }
});

var Data = Backbone.Collection.extend({
    model: Item
});

var DataView = Backbone.View.extend({
    render: function(){
        $(this.el).html(Mustache.render($('#items').html()));
        var t = this;
        var ul = $($(t.el).children()[0]);

        _.each(this.collection.models,function(element){
            ul.append(new ItemView({model: element}).el);
        });

        return this;
    },

    initialize: function(data){
        this.collection = new Data(data);

        //Выбрать из всех объектов класса Item только те, в которых id группы равно id текущей группы

        return this.render().el;
    }
});

var Groups = Backbone.Collection.extend({
    model: Group
});

var GroupsView = Backbone.View.extend({
    tagName: 'div',

    render: function(){
        var t = this;

        $(t.el).html(Mustache.render($('#groups').html()));

        _.each(this.collection.models,function(element){
            $(t.el).append(new GroupView({model: element}).el);
        });

        return this;
    },

    initialize: function(){
        this.collection = new Groups(arrGroups);
        return this.render().el;
    }
});

var groups = new GroupsView();

$('#app').append(groups.el);