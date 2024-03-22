'use strict';

import { fruits, numbers, students } from './data.js'

// console.log(
//     '과일 중에 배가 있나요?',
//     fruits.some((fruit) => {
//         return fruit == '배';
//     })
// );

console.log(
    '과일 중에 배가 있나요?',
    fruits.some((fruit) =>  fruit == '배'));


console.log(
    '숫자에 7 이상인 숫자가 있나요?',
    numbers.some((number) => number >= 7)
);

console.log(
    '수학 점수가 100점인 학생이 있나요?',
    students.some((student) => student.mathematics == 100)
);

console.log(
    '영어 점수가 50점 미만인 학생이 있나요?',
    students.some((student) => student.english < 50)
);

console.log(
    '과일 중에 배가 있나요?',
    fruits.some((fruit, index) => {  
        console.log('index:', index, "fruit: ", fruit)
        return fruit == '배'
    })
);