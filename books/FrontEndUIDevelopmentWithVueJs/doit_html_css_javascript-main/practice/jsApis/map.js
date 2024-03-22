'use strict';

import { numbers, students } from './data.js'

const result = numbers.map((number) => number * 2);
console.log(result);

console.log(
    "영어 점수",
    students.map((student) => student.english)
);

console.log(
    "학생 이름",
    students.map((student) => student.name)
);

