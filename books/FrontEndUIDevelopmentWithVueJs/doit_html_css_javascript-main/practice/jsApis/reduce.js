'use strict';

import {fruits, numbers, students} from './data.js'


numbers.reduce((acc, cur, idx, src) => {
    console.log("acc: ", acc, "cur: ", cur, "idx: ", idx);
}, 0);


numbers.reduce((acc, cur, idx, src) => {
    console.log("acc: ", acc, "cur: ", cur, "idx: ", idx);
    return acc;
}, 0);


// const result = numbers.reduce((acc, cur, idx, src) => {
//     console.log("acc: ", acc, "cur: ", cur, "idx: ", idx);
//     return acc;
// }, 1);
// console.log("result: ", result) // 1

const result = numbers.reduce((acc, cur, idx, src) => {
    console.log("acc: ", acc, "cur: ", cur, "idx: ", idx);
    return acc + cur;
}, 0);
console.log("result: ", result)

// reduce 활용하여 중복된 "딸기" 제거하기
console.log(fruits);

const result1 = fruits.reduce((arr, cur) => {
    console.log("arr: ", arr, "cur: ", cur);
    if (arr.includes(cur) === false) {
        arr.push(cur);
    }
    return arr;
}, []);

console.log(result1);