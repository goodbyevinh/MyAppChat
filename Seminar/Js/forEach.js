var arr = [
    'element1',
    'element2',
    'element3',
    'element4',
    'element5',
    'element6'
];

arr.forEach(function(item, index, array) {
    console.log(item)
});

Array.prototype.forEachOther = function(callback) {
    
    for(var  i = 0; i < this.length; i++) {

        callback(this[i], i, this);
    }
};

arr.forEachOther(function(item, index, array) {
    console.log(item)
});

