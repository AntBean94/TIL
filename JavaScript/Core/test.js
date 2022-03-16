var partial = function () {
    var originalPartialArgs = arguments;
    var func = originalPartialArgs[0];
    if (typeof func !== 'function') {
        throw new Error('첫 번째 인자가 함수가 아닙니다.');
    }
    return function () {
        var partialArgs = Array.prototype.slice.call(originalPartialArgs, 1);
        var restArgs = Array.prototype.slice.call(arguments);
        console.log(originalPartialArgs, partialArgs);
        console.log(arguments, restArgs);
        return func.apply(this, partialArgs.concat(restArgs));
    };
};

var add = function () {
    var result = 0;
    for (var i = 0; i < arguments.length; i++) {
        result += arguments[i];
    }
    return result;
};

var addPartial = partial(add, 1, 2, 3, 4);
console.log(addPartial(5, 6, 7, 8, 9, 10))

var addPartial2 = add.bind(null, 1, 2, 3, 4, 5);
console.log(addPartial2(6, 7, 8, 9, 10));

var dog = {
    name: '강아지',
    greet: function() {
        var inner = function (prefix, suffix) {
            return prefix + this.name + suffix;
        }.bind(this, '왈왈, ')
        return inner
    }
}

// var bark = dog.greet()
// console.log(bark('입니다.'))
console.log(dog.greet()('입니다.'))

var dog2 = {
    name: '사모예드',
    greet: function (prefix, suffix) {
        return prefix + this.name + suffix;
    }
}

console.log(dog2.greet('왈왈, ', '입니다.'))
var partialGreet = dog2.greet.bind(dog2, '왈왈, ');
console.log(partialGreet('입니다.'))