'use strict';

import { fruits, numbers, students } from './data.js'

console.log(
    '1부터 7까지 짝수만 출력해주세요.',
    numbers.filter(number => number % 2 == 0)
);

console.log(
    '1부터 7까지 홀수만 출력해주세요.',
    numbers.filter(number => number % 2 == 1)
);

console.log(
    '학생들 중에서 영어 점수가 90점 이상인 학생들은?',
    students.filter(student => student.english >= 90)
);

console.log(
    '학생들 중에서 영어 점수가 80점 이상인 학생들은?',
    students.filter(student => student.english >= 80)
);

