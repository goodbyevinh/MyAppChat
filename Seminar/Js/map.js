var arr = [1,2,3,4];

var result1 = arr.map(function(item, index, array) {
    return item * 10;
});

Array.prototype.mapOther = function(callback) {
    
    var arr = [];

    for(var  i = 0; i < this.length; i++) {

        arr.push(callback(this[i], i, this));
    }

    return arr;
};

var result2 = arr.mapOther(function(item, index, array) {
    return item*10;
});

console.log(result2)
