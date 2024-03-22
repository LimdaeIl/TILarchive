'use strict';

import { fruits, numbers, students } from './data.js'

console.log(
    '숫자가 모두 8 이하 인가요?',
    numbers.every(number => number <= 8)
);

console.log(
    '학생들의 수학 점수가 모두 80점 이상인가요?',
    students.every(studnet => studnet.mathematics >= 80)
);

console.log(
    '학생들의 수학 점수가 모두 75점 이상인가요?',
    students.every(studnet => studnet.mathematics >= 75)
);

