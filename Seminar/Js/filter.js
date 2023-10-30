var arr = [1, 9, 5, 2, 15];

var filterArr1 = arr.filter(function(num) {
    return num > 9;
});

console.log(filterArr1)

Array.prototype.filterOther = function(callback) {

    var arr = [];

    for(var  i = 0; i < this.length; i++) {
        
        if (callback(this[i], i)) {
            arr.push(this[i]);            
        }
    }

    return arr;
}

var filterArr2 = arr.filterOther(function(item) {
    return item > 5;
})

console.log(filterArr2)

