/**
 * Представление группы номенклатурных позиций
 * @type {*|void}
 */
var GroupView = Backbone.View.extend({

    tagName: "div",

    render: function(){
        $(this.el).html(Mustache.render($('#groupTemplate').html(), this.model.toJSON()));
        t = this;
        data = _.filter(model.attributes.store, function(elem){
            return elem.groupId == t.model.attributes.id;
        });

        var elem = t.$('.group');

        elem.append(new DataView(data).el);
        return this;
    },

    initialize: function(){
        this.render();
    }
});