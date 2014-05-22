/**
 * Модель одной позиции
 * @type {*|void}
 */
var Model = Backbone.Model.extend({

    localStorage: new Backbone.LocalStorage("model"),

    loadSavedData: function(){
        if (this.mobileDevice()){

        }
        else{
            this.fetch();
        }
    },

    mobileDevice: function(){
        return (env == 'mobile');
    },

    hasSavedData: function(){
        if (this.mobileDevice()){
            return false; //TODO переделать, когда буду тестить на мобилах
        }
        else{
            return (localStorage['model'] != undefined);
        }
    },

    noData: function(){
        return (this.attributes.store == undefined);
    },

    currentOrderExists: function(){
        return this.attributes.current != undefined;
    },

    setGDataToModel: function(){
        this.set('clients',gData.clients);
        this.set('groups',gData.groups);
//        this.set('store',gData.store);
    },

    loadFromLocalStorage: function(){
        this.fetch();

        this.setGDataToModel();

        if (this.noData()){
            return false;
        }

        var current = {};

        current.order = new Order(this.attributes.orders.length,this.attributes.current.order);
        current.orderCollection = current.order.attributes.items;

        this.attributes.current = current;

        return true;

    },

    load: function(){

        var loaded = this.loadFromLocalStorage();

        if (!loaded){
            this.loadDefaults();
        }

        //Если текущего заказа нет, то создаем новый заказ.
        if (!this.currentOrderExists()){
            this.createNewOrder();
        }

    },

    createNewOrder: function(){
        this.attributes.current = {};
        this.attributes.current.order = new Order(this.attributes.orders.length);
        this.attributes.current.orderCollection = this.attributes.current.order.attributes.items;
    },

    /**
     * общая функция сохранения, независимо от того, где была запущена прога - в браузере иили на моб. устройстве
     */
    saveGen: function(){
        if (this.mobileDevice()){

        }
        else{
            this.saveInLocalStorage();
        }
    },
    getSavedData: function(){
        if (env == 'mobile'){
            return this.getFromLocalFileSystem();
        }
        else{
            return this.getFromLocalStorage();
        }
    },
    /**
     * Сохраняем в локальное хранилище, если пользуем программу через браузер
     */
    saveInLocalStorage: function(){
        this.save();
    },
    loadDefaults: function(){
        this.attributes.clients = gData.clients;
        this.attributes.store = gData.store;
        this.attributes.orders = new Orders(gData.orders);
        this.attributes.groups = gData.groups;
    },
    initialize: function(){
        this.load();
    }
});