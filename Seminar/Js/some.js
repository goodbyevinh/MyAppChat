var arr = [1, 2, 5, 2, 15];

console.log(arr.some(function(item) {
    return item == 1;
}));

Array.prototype.someOther = function(callback) {
    
    for(var  i = 0; i < this.length; i++) {

        if (callback(this[i], i, this)) {

            return true;
        }
    }

    return false;
};

console.log(arr.someOther(function(item) {
    return item == 3;
}));
