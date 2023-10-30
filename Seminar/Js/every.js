var arr = [4, 3, 5, 7, 15];

console.log(arr.every(function(item) {
    return item > 1;
}));

Array.prototype.everyOther = function(callback) {
    
    for(var  i = 0; i < this.length; i++) {

        if (!callback(this[i], i, this)) {

            return false;
        }
    }

    return true;
};

console.log(arr.everyOther(function(item) {
    return item > 4;
}));