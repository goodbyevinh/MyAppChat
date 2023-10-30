const arr = [1,2,3,4,5];

const result1 = arr.reduce(function(total, number) {
    return total + number;
});

console.log(result1);

const result2 = arr.reduce(function(total, number) {
    return total + number;
}, 100);

console.log(result2);

Array.prototype.reduceOther = function(callback, result) {

    var x = 0;

    if (arguments.length < 2) {
        
        x = 1;
        result = this[0];
    }

    for(var  i = x; i < this.length; i++) {
        
        result = callback(result, this[i], i, this)
    }

    return result;
}

const result3 = arr.reduceOther(function(total, number) {
    return total + number;
}, 100)

console.log(result3);

